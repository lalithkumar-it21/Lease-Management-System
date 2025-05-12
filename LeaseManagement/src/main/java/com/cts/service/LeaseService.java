package com.cts.service;

import java.util.List;

import com.cts.dto.LeasePropertyResponseDTO;

import com.cts.model.Lease;

public interface LeaseService {
	

	public abstract String saveLease(Lease lease);

	public abstract Lease updateLease(Lease lease);

	public abstract LeasePropertyResponseDTO getLease(int leaseId);

	public abstract List<Lease> getAllLease();

	public abstract String deleteLease(int leaseId);

	public abstract List<Lease> getLeaseByTenantId(int tenantId);
	
}
