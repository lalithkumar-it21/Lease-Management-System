package com.cts.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//
//import com.cts.dto.PropertyOwnerRequestDTO;
//import com.cts.feignclient.OwnerClient;
import com.cts.repository.PropertyRepository;

import jakarta.transaction.Transactional;
//
//import com.cts.dto.Owner;
//import com.cts.dto.PropertyOwnerResponseDTO;

import com.cts.exception.PropertyNotFoundException;

import com.cts.model.Property;

import com.cts.repository.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	PropertyRepository repository;

	@Override
	public String saveProperty(Property property) {
	    // Check if a property with the same ID already exists
	    Optional<Property> existingProperty = repository.findById(property.getPropertyId());

	    if (existingProperty.isPresent()) {
	        return "Property with this ID already exists"; // Prevent duplicate insertion
	    }

	    repository.save(property);
	    return "Property saved successfully";
	}


	@Override
	public Property getProperty(int propertyId) throws PropertyNotFoundException {
		Optional<Property> optional = repository.findById(propertyId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new PropertyNotFoundException("Invalid Property Id");
	}

	@Override
	public Property updateProperty(Property property) {
		return repository.save(property);
	}

	@Override
	public List<Property> getAllProperty() {
		return repository.findAll();
	}

	@Override
	public List<Property> getPropertiesByOwnerId(int ownerId) {
		List<Property> allProperties = repository.findAll();
		return allProperties.stream().filter(property -> property.getOwnerId() == ownerId).collect(Collectors.toList());
	}
	
	@Override
	public List<Property> getPropertiesByAddressSimilarity(String address) {
	    List<Property> allProperties = repository.findAll();
	    return allProperties.stream()
	                        .filter(property -> property.getAddress().toLowerCase().contains(address.toLowerCase()))
	                        .collect(Collectors.toList());
	}


	@Override
	public List<Property> getPropertiesByRentRange(int minRent, int maxRent) {
	    List<Property> allProperties = repository.findAll();
	    return allProperties.stream()
	                        .filter(property -> property.getRentAmount() >= minRent && property.getRentAmount() <= maxRent)
	                        .collect(Collectors.toList());
	}


	@Override
	public String deleteProperty(int propertyId) {
		Optional<Property> property = repository.findById(propertyId);

		repository.delete(property.get());
		return "Property Deleted";
	}
//	@Override
//	public String deleteByOwnerId(int ownerId) {
//		repository.deleteById(ownerId);
//		return  "Owner record is deleted";
//	}

}
