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
//
//import com.cts.dto.PropertyOwnerRequestDTO;
//import com.cts.dto.PropertyOwnerResponseDTO;

import com.cts.exception.PropertyNotFoundException;

import com.cts.model.Property;

import com.cts.service.PropertyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/property")
public class PropertyController {
	@Autowired
	PropertyService service;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveProperty(@Valid @RequestBody Property property) {
		return ResponseEntity.ok(service.saveProperty(property));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Property> updateProperty(@Valid @RequestBody Property property) {
		return ResponseEntity.ok(service.updateProperty(property));
	}

	@GetMapping("/fetchById/{propertyid}")
	public Property getProperty(@PathVariable("propertyid") int propertyId) throws PropertyNotFoundException{
		return service.getProperty(propertyId);
	}

	@GetMapping("/fetchAll")
	public List<Property> getAllProperty() {
		return service.getAllProperty();
	}
	
	@GetMapping("/propertiesByOwner/{ownerId}")
	public List<Property> getPropertiesByOwner(@PathVariable("ownerId") int ownerId) {
	    return service.getPropertiesByOwnerId(ownerId);
	}
	@GetMapping("/propertiesByAddress/{address}")
	public List<Property> getPropertiesByAddressSimilarity(@PathVariable("address") String address) {
	    return service.getPropertiesByAddressSimilarity(address);
	}


	@GetMapping("/propertiesByRentRange/{minRent}/{maxRent}")
	public List<Property> getPropertiesByRentRange(@PathVariable("minRent") int minRent, @PathVariable("maxRent") int maxRent) {
	    return service.getPropertiesByRentRange(minRent, maxRent);
	}



	
	@DeleteMapping("/delete/{propertyid}")
	public String deleteProperty(@PathVariable("propertyid") int propertyId) {
		return service.deleteProperty(propertyId);
	}
	

	 


}
