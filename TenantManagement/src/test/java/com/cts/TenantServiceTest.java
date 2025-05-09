package com.cts;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.exception.TenantNotFound;
import com.cts.model.Tenant;
import com.cts.repository.TenantRepository;
import com.cts.service.TenantServiceImpl;

class TenantServiceTest {

    @Mock
    private TenantRepository tenantRepository;

    @InjectMocks
    private TenantServiceImpl tenantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTenants() {
        List<Tenant> tenants = Arrays.asList(
            new Tenant(1, "John Doe", "1234567890", "Address 1"),
            new Tenant(2, "Jane Doe", "9876543210", "Address 2")
        );

        when(tenantRepository.findAll()).thenReturn(tenants);

        List<Tenant> result = tenantService.getAllTenant();
        assertEquals(2, result.size());
        verify(tenantRepository, times(1)).findAll();
    }

    @Test
    void testGetTenantById_Success() throws TenantNotFound {
        Tenant tenant = new Tenant(1, "John Doe", "1234567890", "Address 1");
        when(tenantRepository.findById(1)).thenReturn(Optional.of(tenant));

        Tenant result = tenantService.getTenant(1);
        assertNotNull(result);
        assertEquals("John Doe", result.getTenantName());
        verify(tenantRepository, times(1)).findById(1);
    }

    @Test
    void testGetTenantById_NotFound() {
        when(tenantRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TenantNotFound.class, () -> tenantService.getTenant(1));
    }

    @Test
    void testSaveTenant() {
        Tenant tenant = new Tenant(3, "Alice Doe", "5555555555", "Address 3");
        when(tenantRepository.save(tenant)).thenReturn(tenant);

        String response = tenantService.saveTenant(tenant);
        assertEquals("Tenant saved successfully", response);
        verify(tenantRepository, times(1)).save(tenant);
    }

    @Test
    void testDeleteTenant_Success() {
        Tenant tenant = new Tenant(1, "John Doe", "1234567890", "Address 1");
        when(tenantRepository.findById(1)).thenReturn(Optional.of(tenant));

        String response = tenantService.deleteTenant(1);
        assertEquals("Tenant Deleted", response);
        verify(tenantRepository, times(1)).delete(tenant);
    }
}
