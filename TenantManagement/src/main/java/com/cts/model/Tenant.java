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
@Table(name = "tenant_info")
public class Tenant {

    @Id
    @Min(value = 1, message = "Tenant ID must be greater than 0")
    private int tenantId;

    @NotBlank(message = "Tenant name cannot be blank")
    private String tenantName;

    @Size(min = 10, max = 10, message = "Tenant contact must be exactly 10 digits")
    @Pattern(regexp = "\\d{10}", message = "Tenant contact must contain only digits")
    private String tenantContact;

    @NotBlank(message = "Tenant address cannot be blank")
    private String tenantAddress;

    
    // private String rentalHistory;
}
