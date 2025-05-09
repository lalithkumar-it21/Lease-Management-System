package com.cts.service;

import java.util.List;

import com.cts.exception.TenantNotFound;
import com.cts.model.Tenant;



public interface TenantService {

	public abstract String saveTenant(Tenant tenant);
	public abstract Tenant getTenant(int tenantId)throws TenantNotFound;
	public abstract Tenant updateTenant(Tenant tenant);
	public abstract List<Tenant> getAllTenant();
	
	public abstract String deleteTenant(int tenantId);
	 
	
	
	
	
	
}
