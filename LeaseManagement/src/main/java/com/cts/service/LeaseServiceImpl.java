package com.cts.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.LeasePropertyResponseDTO;

import com.cts.dto.Property;

import com.cts.exception.LeaseNotFoundException;

import com.cts.model.Lease;

import com.cts.repository.LeaseRepository;

import jakarta.transaction.Transactional;

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
	Logger log = LoggerFactory.getLogger(LeaseServiceImpl.class);

	// To save Lease
	@Override
	public String saveLease(Lease lease) {
		log.info("In leaseServiceImpl savelease method...");
		// Check if lease with same leaseId already exists
		Optional<Lease> existingLease = repository.findById(lease.getLeaseId());

		if (existingLease.isPresent()) {
			return "Lease already exists"; // Prevent duplicate insertion
		}

		repository.save(lease);
		return "Lease saved successfully";
	}

	// To update Lease
	@Override
	public Lease updateLease(Lease lease) {
		log.info("In leaseServiceImpl updatelease method...");
		return repository.save(lease);
	}

	// To get Lease with property
	@Override
	public LeasePropertyResponseDTO getLease(int leaseId) throws LeaseNotFoundException {
		log.info("In leaseServiceImpl getlease method...");
		Lease lease = repository.findById(leaseId).get();
		int propertyno = lease.getPropertyId();
		Property property = propertyClient.getPropertyById(propertyno);
		LeasePropertyResponseDTO responseDTO = new LeasePropertyResponseDTO(lease, property);
		return responseDTO;
	}

	// To get all Lease
	@Override
	public List<Lease> getAllLease() {
		log.info("In leaseServiceImpl getalllease method...");
		return repository.findAll();
	}

    //To delete Lease
	@Transactional
	@Override
	public String deleteLease(int leaseId) {
		log.info("In leaseServiceImpl deletelease method...");
		Optional<Lease> lease = repository.findById(leaseId);
		repository.delete(lease.get());
		return "Lease Deleted";
	}

	// To get Leases of tenant
	@Override
	public List<Lease> getLeaseByTenantId(int tenantId) {
		log.info("In leaseServiceImpl getLeaseByTenantId method...");
		return repository.findAll().stream().filter(lease -> lease.getTenantId() == tenantId)
				.collect(Collectors.toList());
	}

}
