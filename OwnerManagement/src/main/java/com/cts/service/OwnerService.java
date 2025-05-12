package com.cts.service;

import java.util.List;

import com.cts.dto.OwnerPropertyRequestDTO;
import com.cts.dto.OwnerPropertyResponseDTO;
import com.cts.exception.OwnerNotFoundException;

import com.cts.model.Owner;

public interface OwnerService {

	public abstract String saveOwner(OwnerPropertyRequestDTO ownerProperty);

	public abstract Owner updateOwner(Owner owner);

	public abstract Owner getOwner(int ownerId) throws OwnerNotFoundException;

	// public abstract OwnerPropertyResponseDTO getOwner(int ownerId);
	public abstract String deleteOwnerAndProperties(int ownerId);

	public abstract List<Owner> getAllOwner();

//	public abstract String deleteOwner(int ownerId);
//	 
//	public abstract String deleteByPropertyId(int propertyId);

}
