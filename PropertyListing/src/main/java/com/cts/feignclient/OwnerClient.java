package com.cts.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.dto.Owner;
import com.cts.model.Property;
@FeignClient(name="OWNERMANAGEMENT",path="/owner")
public interface OwnerClient {
	@GetMapping("/fetchById/{ownerid}")
	public Owner getOwnerById(@PathVariable("ownerid") int ownerId);
//    @GetMapping("/propertiesByOwner/{ownerId}")//for properties with single owner
//	public List<Property> getPropertiesByOwner(@PathVariable("ownerId") int ownerId);
}
