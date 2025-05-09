package com.cts.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.dto.Tenant;
import com.cts.model.Lease;



@FeignClient(name="TENANTMANAGEMENT",path="/tenant")
public interface TenantClient {
//	@PostMapping("/save")
//	public String saveTenant(@RequestBody Tenant tenant);

//	@GetMapping("/fetchById/{tenantid}")
//	public Tenant getTenantById(@PathVariable("tenantid") int tenantId);
	 @GetMapping("/leaseIdsByTenant/{tenantId}")
	    List<Integer> getLeaseIdsByTenant(@PathVariable("tenantId") int tenantId);
}
