package com.cts.dto;

import com.cts.model.Lease;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseTenantRequestDTO {

	private Lease lease;

	private Tenant tenant;

}
