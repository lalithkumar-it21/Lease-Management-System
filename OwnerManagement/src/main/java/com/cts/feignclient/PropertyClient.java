package com.cts.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.dto.Property;
import com.cts.model.Owner;



@FeignClient(name="PROPERTYLISTING",path="/property")
public interface PropertyClient {
	@PostMapping("/save")
	public String saveProperty(@RequestBody Property property);
	
	@GetMapping("/propertiesByOwner/{ownerId}")//get list of property with same owner id for deletion
    List<Property> getPropertiesByOwner(@PathVariable("ownerId") int ownerId);
	
	@DeleteMapping("/delete/{propertyId}")
    String deleteProperty(@PathVariable("propertyId") int propertyId);
	
	
//	@GetMapping("/fetchById/{propertyid}")
//	public Property getPropertyById(@PathVariable("propertyid") int propertyId);

}
