package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
	private int tenantId;

	private String tenantName;

	private String tenantContact;

	private String tenantAddress;

}
