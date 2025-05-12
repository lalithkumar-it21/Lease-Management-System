package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.PropertyNotFoundException;

import com.cts.model.Property;

import com.cts.service.PropertyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/property")
public class PropertyController {
	@Autowired
	PropertyService service;

	// To save property
	@PostMapping("/save")
	public ResponseEntity<String> saveProperty(@Valid @RequestBody Property property) {
		return ResponseEntity.ok(service.saveProperty(property));
	}

	// To update property
	@PutMapping("/update")
	public ResponseEntity<Property> updateProperty(@Valid @RequestBody Property property) {
		return ResponseEntity.ok(service.updateProperty(property));
	}

	// To get property
	@GetMapping("/fetchById/{propertyid}")
	public Property getProperty(@PathVariable("propertyid") int propertyId) throws PropertyNotFoundException {
		return service.getProperty(propertyId);
	}

	// To get all property
	@GetMapping("/fetchAll")
	public List<Property> getAllProperty() {
		return service.getAllProperty();
	}

	// To sort property by owner
	@GetMapping("/propertiesByOwner/{ownerId}")
	public List<Property> getPropertiesByOwner(@PathVariable("ownerId") int ownerId) {
		return service.getPropertiesByOwnerId(ownerId);
	}

	// To sort property by address
	@GetMapping("/propertiesByAddress/{address}")
	public List<Property> getPropertiesByAddressSimilarity(@PathVariable("address") String address) {
		return service.getPropertiesByAddressSimilarity(address);
	}

	// To sort property by rent range
	@GetMapping("/propertiesByRentRange/{minRent}/{maxRent}")
	public List<Property> getPropertiesByRentRange(@PathVariable("minRent") int minRent,
			@PathVariable("maxRent") int maxRent) {
		return service.getPropertiesByRentRange(minRent, maxRent);
	}

	// To delete property
	@DeleteMapping("/delete/{propertyid}")
	public String deleteProperty(@PathVariable("propertyid") int propertyId) {
		return service.deleteProperty(propertyId);
	}

}
