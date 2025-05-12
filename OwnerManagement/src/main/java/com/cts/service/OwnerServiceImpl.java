package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.OwnerPropertyRequestDTO;
import com.cts.dto.OwnerPropertyResponseDTO;

import com.cts.exception.OwnerNotFoundException;

import com.cts.feignclient.PropertyClient;
import com.cts.dto.Property;
import com.cts.model.Owner;

import com.cts.repository.OwnerRepository;

import jakarta.transaction.Transactional;

@Service
public class OwnerServiceImpl implements OwnerService {
	@Autowired
	OwnerRepository repository;
	@Autowired
	PropertyClient propertyClient;
	Logger log = LoggerFactory.getLogger(OwnerServiceImpl.class);

	// To save Owner
	@Override
	public String saveOwner(OwnerPropertyRequestDTO ownerProperty) {
		log.info("In ownerServiceImpl saveownerproperty method...");
		// Check if owner already exists
		Optional<Owner> existingOwner = repository.findById(ownerProperty.getOwner().getOwnerId());

		if (existingOwner.isPresent()) {
			return "Owner and Property already Saved !!!"; // Keeps your return statement consistent
		}

		repository.save(ownerProperty.getOwner());

		String response = propertyClient.saveProperty(ownerProperty.getProperty());

		if ("Property saved successfully".equals(response)) {
			return "Owner and Property Saved !!!"; // Consistent return statement
		} else {
			return "Something went wrong!!!"; // No change here
		}
	}

	// To update Owner
	@Override
	public Owner updateOwner(Owner owner) {
		log.info("In ownerServiceImpl updateowner method...");
		return repository.save(owner);
	}
//	@Override
//	public OwnerPropertyResponseDTO getOwner(int ownerId) {
//		Owner owner = repository.findById(ownerId).get();
//		int propertyid = owner.getPropertyId();
//		Property property = propertyClient.getPropertyById(propertyid);
//		OwnerPropertyResponseDTO responseDTO = new OwnerPropertyResponseDTO(owner,property);
//		return responseDTO;
//	}

	// To get all Owner
	@Override
	public List<Owner> getAllOwner() {
		log.info("In ownerServiceImpl getallproperty method...");
		return repository.findAll();
	}

	// To delete Owner and property records
	@Transactional
	@Override
	public String deleteOwnerAndProperties(int ownerId) {
		log.info("In ownerServiceImpl deleteOwnerAndProperties method...");
		List<Property> properties = propertyClient.getPropertiesByOwner(ownerId);
		for (Property property : properties) {
			propertyClient.deleteProperty(property.getPropertyId());
		}
		repository.deleteById(ownerId);
		return "Owner and all associated properties deleted!";
	}

	// To get Owner
	@Override
	public Owner getOwner(int ownerId) throws OwnerNotFoundException {
		log.info("In ownerServiceImpl getOwner method...");
		Optional<Owner> optional = repository.findById(ownerId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new OwnerNotFoundException("Invalid Owner Id");
	}

}
