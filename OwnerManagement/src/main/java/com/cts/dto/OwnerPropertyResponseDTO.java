package com.cts.dto;

import com.cts.model.Owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPropertyResponseDTO {
	private Owner owner;
	private Property property;
	
}
