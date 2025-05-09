package com.cts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "owner_info")
public class Owner {

    @Id
    @Min(value = 1, message = "Owner ID must be greater than 0")
    private int ownerId;

    @NotBlank(message = "Owner name cannot be blank")
    private String ownerName;

    @Size(min = 10, max = 10, message = "Owner contact must be exactly 10 digits")
    @Pattern(regexp = "\\d{10}", message = "Owner contact must contain only digits")
    private String ownerContact;

    @NotBlank(message = "Owner address cannot be blank")
    private String ownerAddress;

    @Min(value = 1, message = "Property ID must be greater than 0")
    private int propertyId; // Foreign Key reference

}
