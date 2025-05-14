package com.cts.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "lease_info")
public class Lease {

	@Id
	@Min(value = 1, message = "Lease ID must be greater than 0")
	private int leaseId;

	@NotNull(message = "Property ID cannot be null")
	@Min(value = 1, message = "Property ID must be greater than 0")
	private int propertyId;

	@NotNull(message = "Tenant ID cannot be null")
	@Min(value = 1, message = "Tenant ID must be greater than 0")
	private int tenantId;

	@NotBlank(message = "Start date is required")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Start date must be in format DD-MM-YYYY")
	private String startDate;

	@NotBlank(message = "End date is required")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "End date must be in format DD-MM-YYYY")
	private String endDate;

	@NotBlank(message = "Agreement details cannot be empty")
	@Size(min = 5, message = "Agreement details must be at least 10 characters long")
	private String agreementDetails;

	@Min(value = 1, message = "Rent amount must be greater than 0")
	private int rentAmount;

	@NotBlank(message = "Status cannot be empty")
	@Pattern(regexp = "ACTIVE|EXPIRED|TERMINATED", message = "Status must be either ACTIVE, EXPIRED, or TERMINATED")
	private String status;

}
