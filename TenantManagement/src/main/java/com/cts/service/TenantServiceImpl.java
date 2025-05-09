package com.cts.service;

import java.util.List;
import java.util.Optional;

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

@Override
public String saveTenant(Tenant tenant) {
	repository.save(tenant);

	return "Tenant saved successfully";
}

@Override
public Tenant getTenant(int tenantId) throws TenantNotFound {
	Optional<Tenant> optional = repository.findById(tenantId);
	if (optional.isPresent())
		return optional.get();
	else
		throw new TenantNotFound("Invalid Tenant Id");
}

@Override
public Tenant updateTenant(Tenant tenant) {
	return repository.save(tenant);
}

@Override
public List<Tenant> getAllTenant() {
	return repository.findAll();
}
@Override
public String deleteTenant(int tenantId) {
	Optional<Tenant> tenant = repository.findById(tenantId);
	repository.delete(tenant.get());
	return "Tenant Deleted";
}
}