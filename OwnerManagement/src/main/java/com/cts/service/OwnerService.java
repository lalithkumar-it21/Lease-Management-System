package com.cts.service;

import java.util.List;

import com.cts.exception.OwnerNotFoundException;

import com.cts.model.Owner;

public interface OwnerService {

	public abstract String saveOwner(Owner owner);

	public abstract Owner updateOwner(Owner owner);

	public abstract Owner getOwner(int ownerId) throws OwnerNotFoundException;

	public abstract String deleteOwnerAndProperties(int ownerId);

	public abstract List<Owner> getAllOwner();



}
