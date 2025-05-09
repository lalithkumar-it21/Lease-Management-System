package com.cts.service;

import java.util.List;

//import com.cts.dto.PropertyOwnerRequestDTO;
//import com.cts.dto.PropertyOwnerResponseDTO;
import com.cts.exception.PropertyNotFoundException;
import com.cts.model.Property;


public interface PropertyService {
	// public abstract String saveProperty(PropertyOwnerRequestDTO propertyOwner);
	 //public abstract PropertyOwnerResponseDTO getProperty(int propertyId);
	public abstract String saveProperty(Property property);
	 public abstract Property getProperty(int propertyId) throws PropertyNotFoundException;
	 
		public abstract Property updateProperty(Property property);
		public abstract List<Property> getAllProperty();
		
		public abstract String deleteProperty(int propertyId);
		 
		//public abstract String deleteByOwnerId(int ownerId);
		public abstract List<Property> getPropertiesByOwnerId(int ownerId);
		public abstract List<Property> getPropertiesByAddressSimilarity(String address);

		public abstract List<Property> getPropertiesByRentRange(int minRent, int maxRent);


}
