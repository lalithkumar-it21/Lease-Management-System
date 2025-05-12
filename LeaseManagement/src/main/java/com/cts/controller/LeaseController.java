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

import com.cts.dto.LeasePropertyResponseDTO;

import com.cts.model.Lease;

import com.cts.service.LeaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lease")
public class LeaseController {
	@Autowired
	LeaseService service;

//for saving lease detail after validation
	@PostMapping("/save")
	public ResponseEntity<String> saveLease(@Valid @RequestBody Lease leaseTenant) {
		return ResponseEntity.ok(service.saveLease(leaseTenant));
	}

	// for update lease detail after validation
	@PutMapping("/update")
	public ResponseEntity<Lease> updateLease(@Valid @RequestBody Lease lease) {
		return ResponseEntity.ok(service.updateLease(lease));
	}

	// for get lease by id detail after validation
	@GetMapping("/fetchById/{lid}")
	public LeasePropertyResponseDTO getLease(@PathVariable("lid") int leaseId) {
		return service.getLease(leaseId);
	}

	// for get all lease detail after validation
	@GetMapping("/fetchAll")
	public List<Lease> getAllLease() {
		return service.getAllLease();
	}

	// for deleting lease detail after validation
	@DeleteMapping("/delete/{leaseid}")
	public String deleteLease(@PathVariable("leaseid") int leaseId) {
		return service.deleteLease(leaseId);
	}

	// for getting lease using tenant id detail after validation
	@GetMapping("/leaseByTenant/{tenantId}")
	public List<Lease> getLeaseByTenant(@PathVariable("tenantId") int tenantId) {
		return service.getLeaseByTenantId(tenantId);
	}

}
