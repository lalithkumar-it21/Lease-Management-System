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
import com.cts.dto.LeaseTenantRequestDTO;
import com.cts.dto.LeaseTenantResponseDTO;
import com.cts.model.Lease;

import com.cts.service.LeaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lease")
public class LeaseController {
	@Autowired
	LeaseService service;

	@PostMapping("/save")
	public ResponseEntity<String> saveLease(@Valid @RequestBody Lease leaseTenant) {
		return ResponseEntity.ok(service.saveLease(leaseTenant));
	}
@PutMapping("/update")
	public ResponseEntity<Lease> updateLease(@Valid @RequestBody Lease lease) {
		return ResponseEntity.ok(service.updateLease(lease));
	}
@GetMapping("/fetchById/{lid}")
	public LeasePropertyResponseDTO getLease(@PathVariable("lid") int leaseId) {
		return service.getLease(leaseId);
	}
@GetMapping("/fetchAll")
	public List<Lease> getAllLease() {
		return service.getAllLease();
	}

@DeleteMapping("/delete/{leaseid}")
public String deleteLease(@PathVariable("leaseid") int leaseId) {
	return service.deleteLease(leaseId);
}
@GetMapping("/leaseByTenant/{tenantId}")
public List<Lease> getLeaseByTenant(@PathVariable("tenantId") int tenantId) {
    return service.getLeaseByTenantId(tenantId);
}


//
//@GetMapping("/leaseIdsByTenant/{tenantId}")
//public List<Lease> getLeaseIdsByTenant(@PathVariable("tenantId") int tenantId) {
//    return service.getLeaseIdsByTenant(tenantId);
//}



//@DeleteMapping("/deletebytenant/{tenantid}")
//public String deleteByTenant(@PathVariable("tenantid") int tenantId) {
//	return service.deleteByTenantId(tenantId);
//}


}
