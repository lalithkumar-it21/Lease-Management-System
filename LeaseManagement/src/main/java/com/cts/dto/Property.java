package com.cts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Property {
	private int propertyId;
	private int ownerId;
	private String address;
	private int rentAmount;
	private String propertyDetails;
	private String availabilityStatus;	
}
