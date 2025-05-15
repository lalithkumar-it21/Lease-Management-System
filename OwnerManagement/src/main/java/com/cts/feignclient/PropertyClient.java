package com.cts.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.cts.dto.Property;

@FeignClient(name = "PROPERTYLISTING", path = "/property")
public interface PropertyClient {


	// To get property list
	@GetMapping("/propertiesByOwner/{ownerId}") // get list of property with same owner id for deletion
	List<Property> getPropertiesByOwner(@PathVariable("ownerId") int ownerId);

	// To delete property
	@DeleteMapping("/delete/{propertyId}")
	String deleteProperty(@PathVariable("propertyId") int propertyId);

}
