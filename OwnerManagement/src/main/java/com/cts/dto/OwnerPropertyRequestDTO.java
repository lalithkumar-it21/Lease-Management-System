package com.cts.dto;

import com.cts.model.Owner;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPropertyRequestDTO {
	@Valid
	@NotNull(message = "Owner information cannot be null")
	private Owner owner;

	@Valid
	@NotNull(message = "Property information cannot be null")
	private Property property;

}
