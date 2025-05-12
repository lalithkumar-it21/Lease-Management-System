package com.cts.feignclient;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.dto.Owner;


@FeignClient(name = "OWNERMANAGEMENT", path = "/owner")
public interface OwnerClient {
	//to get owner
	@GetMapping("/fetchById/{ownerid}")
	public Owner getOwnerById(@PathVariable("ownerid") int ownerId);

}
