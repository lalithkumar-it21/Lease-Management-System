package com.cts.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Property_info")
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    @Id
    @Min(value = 1, message = "Property ID must be greater than 0")
    private int propertyId;

    @Min(value = 1, message = "Owner ID must be greater than 0")
    private int ownerId; 

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @Min(value = 1, message = "Rent amount must be greater than 0")
    private int rentAmount;

    @Min(value = 1, message = "period must be greater than 0 and in months")
    private int period;
    
    @NotBlank(message = "Property details cannot be blank")
    private String propertyDetails;

    @NotBlank(message = "Availability status cannot be blank")
    @Pattern(regexp = "AVAILABLE|OCCUPIED|UNDER_MAINTENANCE", message = "Status must be AVAILABLE, OCCUPIED, or UNDER_MAINTENANCE")
    private String availabilityStatus;
}
