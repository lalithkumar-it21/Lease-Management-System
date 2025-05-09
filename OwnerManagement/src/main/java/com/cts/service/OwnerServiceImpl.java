package com.cts.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public String saveOwner(OwnerPropertyRequestDTO ownerProperty) {
		// Check if owner already exists
		Optional<Owner> existingOwner = repository.findById(ownerProperty.getOwner().getOwnerId());

		if (existingOwner.isPresent()) {
			return "Owner and Property already Saved !!!"; // Keeps your return statement consistent
		}

		repository.save(ownerProperty.getOwner());

//		// Check if property already exists
//		Optional<Property> existingProperty = propertyRepository.findById(ownerProperty.getProperty().getPropertyId());
//
//		if (existingProperty.isPresent()) {
//			return "Owner and Property already Saved !!!"; // Keeps your return statement consistent
//		}

		String response = propertyClient.saveProperty(ownerProperty.getProperty());

		if ("Property saved successfully".equals(response)) {
			return "Owner and Property Saved !!!"; // Consistent return statement
		} else {
			return "Something went wrong!!!"; // No change here
		}
	}

	@Override
	public Owner updateOwner(Owner owner) {
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

	@Override
	public List<Owner> getAllOwner() {
		return repository.findAll();
	}

	@Override
	public String deleteOwnerAndProperties(int ownerId) {
		List<Property> properties = propertyClient.getPropertiesByOwner(ownerId);
		for (Property property : properties) {
			propertyClient.deleteProperty(property.getPropertyId());
		}
		repository.deleteById(ownerId);
		return "Owner and all associated properties deleted!";
	}

	@Override
	public Owner getOwner(int ownerId) throws OwnerNotFoundException {
		Optional<Owner> optional = repository.findById(ownerId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new OwnerNotFoundException("Invalid Owner Id");
	}

}
