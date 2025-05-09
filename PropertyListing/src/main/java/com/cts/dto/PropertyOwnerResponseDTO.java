package com.cts.dto;

import com.cts.model.Property;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyOwnerResponseDTO {
	
	private Property property;
	private Owner owner;
	
}