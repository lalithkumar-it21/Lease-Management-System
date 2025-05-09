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

import com.cts.exception.TenantNotFound;
import com.cts.model.Tenant;
import com.cts.service.TenantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tenant")
public class TenantController {
	
	@Autowired
	TenantService service;
	  
	@PostMapping("/save")
	public ResponseEntity<String> saveTenant(@Valid @RequestBody Tenant tenant) {
		return ResponseEntity.ok(service.saveTenant(tenant));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Tenant> updateTenant(@RequestBody Tenant tenant) {
		return ResponseEntity.ok(service.updateTenant(tenant));
	}

	@GetMapping("/fetchById/{tenantid}")
	public Tenant getTenant(@PathVariable("tenantid") int tenantId) throws TenantNotFound {
		return service.getTenant(tenantId);
	}

	@GetMapping("/fetchAll")
	public List<Tenant> getAllTenant() {
		return service.getAllTenant();
	}
	
	@DeleteMapping("/delete/{tenantid}")
	public String deleteTenant(@PathVariable("tenantid") int tenantId) {
		return service.deleteTenant(tenantId);
	}

	

}
