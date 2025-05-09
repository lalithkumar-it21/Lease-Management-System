package com.cts.dto;

import com.cts.model.Lease;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeasePropertyResponseDTO {
   private Lease lease;
	
	private Property property;
}
