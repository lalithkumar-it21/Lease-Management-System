package com.cts.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.LeasePropertyResponseDTO;
import com.cts.dto.LeaseTenantRequestDTO;
import com.cts.dto.LeaseTenantResponseDTO;
import com.cts.dto.Property;
import com.cts.dto.Tenant;
import com.cts.exception.LeaseNotFoundException;

import com.cts.model.Lease;

import com.cts.repository.LeaseRepository;
import com.cts.feignclient.PropertyClient;
import com.cts.feignclient.TenantClient;

@Service
public class LeaseServiceImpl implements LeaseService {
	@Autowired
	LeaseRepository repository;
	@Autowired
	TenantClient tenantClient;
	@Autowired
	PropertyClient propertyClient;
	@Override
	public String saveLease(Lease lease) {
	    // Check if lease with same leaseId already exists
	    Optional<Lease> existingLease = repository.findById(lease.getLeaseId());

	    if (existingLease.isPresent()) {
	        return "Lease already exists"; // Prevent duplicate insertion
	    }

	    repository.save(lease);
	    return "Lease saved successfully";
	}


	@Override
	public Lease updateLease(Lease lease) {
		return repository.save(lease);
	}

	@Override
	public LeasePropertyResponseDTO getLease(int leaseId) throws LeaseNotFoundException {
		Lease lease=repository.findById(leaseId).get();
		int propertyno=lease.getPropertyId();
		Property property=propertyClient.getPropertyById(propertyno);
		LeasePropertyResponseDTO responseDTO=new LeasePropertyResponseDTO(lease, property);
		return responseDTO;
	}

	@Override
	public List<Lease> getAllLease() {
		return repository.findAll();
	}

	@Override
	public String deleteLease(int leaseId) {
		Optional<Lease> lease = repository.findById(leaseId);
		repository.delete(lease.get());
		return "Lease Deleted";
	}

//	@Override
//	public String deleteByTenantId(int tenantId) {
//		repository.deleteById(tenantId);
//		return "tenant record is deleted";
//	}



	 @Override
	    public List<Lease> getLeaseByTenantId(int tenantId) {
	        return repository.findAll().stream()
	                         .filter(lease -> lease.getTenantId() == tenantId)
	                         .collect(Collectors.toList());
	    }


}
