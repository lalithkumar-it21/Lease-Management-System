package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.TenantNotFound;

import com.cts.model.Tenant;
import com.cts.repository.TenantRepository;

import jakarta.transaction.Transactional;

@Service
public class TenantServiceImpl implements TenantService {
	@Autowired
	TenantRepository repository;
	Logger log = LoggerFactory.getLogger(TenantServiceImpl.class);

	// To save tenant
	@Override
	public String saveTenant(Tenant tenant) {
		log.info("In TenantServiceImpl savetenant method...");
		Optional<Tenant> existingTenant = repository.findById(tenant.getTenantId());

		if (existingTenant.isPresent()) {
			return "Record already exists"; // Prevent duplicate insertion
		}

		repository.save(tenant);
		return "Tenant saved successfully";
	}

	// To get tenant
	@Override
	public Tenant getTenant(int tenantId) throws TenantNotFound {
		log.info("In TenantServiceImpl gettenant method...");
		Optional<Tenant> optional = repository.findById(tenantId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new TenantNotFound("Invalid Tenant Id");
	}

	// To update tenant
	@Override
	public Tenant updateTenant(Tenant tenant) {
		log.info("In TenantServiceImpl updatetenant method...");
		return repository.save(tenant);
	}

	// To get all tenant
	@Override
	public List<Tenant> getAllTenant() {
		log.info("In TenantServiceImpl getalltenant method...");
		return repository.findAll();
	}

	// To delete tenant
	@Transactional
	@Override
	public String deleteTenant(int tenantId) {
		log.info("In TenantServiceImpl deletetenant method...");
		Optional<Tenant> tenant = repository.findById(tenantId);
		repository.delete(tenant.get());
		return "Tenant Deleted";
	}
}