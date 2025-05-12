package com.cts.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.dto.Property;

@FeignClient(name = "PROPERTYLISTING", path = "/property")
public interface PropertyClient {
	//To get property by id
	@GetMapping("/fetchById/{propertyid}")
	public Property getPropertyById(@PathVariable("propertyid") int propertyId);
}
