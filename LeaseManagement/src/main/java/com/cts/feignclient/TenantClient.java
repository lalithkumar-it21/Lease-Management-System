package com.cts.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "TENANTMANAGEMENT", path = "/tenant")
public interface TenantClient {
	//To get all leases of single tenant
	@GetMapping("/leaseIdsByTenant/{tenantId}")
	List<Integer> getLeaseIdsByTenant(@PathVariable("tenantId") int tenantId);
}
