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
@Table(name = "request_info")
public class Request {

    @Id
    @Min(value = 1, message = "Request ID must be greater than 0")
    private int requestId;

    @Min(value = 1, message = "Tenant ID must be greater than 0")
    private int tenantId;
    @Min(value = 1, message = "Owner ID must be greater than 0")
    private int ownerId;
    
    @Min(value = 1, message = "Property ID must be greater than 0")
    private int propertyId;

    @NotBlank(message = "Request status cannot be blank")
    @Pattern(regexp = "PENDING|APPROVED|REJECTED", message = "Status must be PENDING OR APPROVED OR REJECTED")
    private String requestStatus;
}
