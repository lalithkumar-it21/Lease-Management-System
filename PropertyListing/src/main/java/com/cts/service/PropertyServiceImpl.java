package com.cts.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.repository.PropertyRepository;

import com.cts.exception.PropertyNotFoundException;

import com.cts.model.Property;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	PropertyRepository repository;
	Logger log = LoggerFactory.getLogger(PropertyServiceImpl.class);

	// To save property
	@Override
	public String saveProperty(Property property) {
		// Check if a property with the same ID already exists
		log.info("In PropertyServiceImpl saveproperty method...");
		Optional<Property> existingProperty = repository.findById(property.getPropertyId());

		if (existingProperty.isPresent()) {
			return "Property with this ID already exists"; // Prevent duplicate insertion
		}

		repository.save(property);
		return "Property saved successfully";
	}

	// To get property
	@Override
	public Property getProperty(int propertyId) throws PropertyNotFoundException {
		log.info("In PropertyServiceImpl getproperty method...");
		Optional<Property> optional = repository.findById(propertyId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new PropertyNotFoundException("Invalid Property Id");
	}

	// To update property
	@Override
	public Property updateProperty(Property property) {
		log.info("In PropertyServiceImpl uproperty method...");
		return repository.save(property);
	}

	// To get all property
	@Override
	public List<Property> getAllProperty() {
		log.info("In PropertyServiceImpl getAllProperty method...");
		return repository.findAll();
	}

	// To sort property by owner
	@Override
	public List<Property> getPropertiesByOwnerId(int ownerId) {
		log.info("In PropertyServiceImpl getPropertiesByOwnerId method...");
		List<Property> allProperties = repository.findAll();
		return allProperties.stream().filter(property -> property.getOwnerId() == ownerId).collect(Collectors.toList());
	}

	// To sort property by address
	@Override
	public List<Property> getPropertiesByAddressSimilarity(String address) {
		log.info("In PropertyServiceImpl getPropertiesByaddress method...");
		List<Property> allProperties = repository.findAll();
		return allProperties.stream()
				.filter(property -> property.getAddress().toLowerCase().contains(address.toLowerCase()))
				.collect(Collectors.toList());
	}

	// To sort property by rent range
	@Override
	public List<Property> getPropertiesByRentRange(int minRent, int maxRent) {
		log.info("In PropertyServiceImpl getPropertiesByrentrange method...");
		List<Property> allProperties = repository.findAll();
		return allProperties.stream()
				.filter(property -> property.getRentAmount() >= minRent && property.getRentAmount() <= maxRent)
				.collect(Collectors.toList());
	}

	// To delete property
	@Override
	public String deleteProperty(int propertyId) {
		log.info("In PropertyServiceImpl delete property method...");
		Optional<Property> property = repository.findById(propertyId);

		repository.delete(property.get());
		return "Property Deleted";
	}

}
