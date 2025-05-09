package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
	private int ownerId;
	private String ownerName;
	private String ownerContact;
	private String ownerAddress;
	private int propertyId;

}
