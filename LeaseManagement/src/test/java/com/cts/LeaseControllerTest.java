package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;


import com.cts.controller.LeaseController;
import com.cts.dto.LeasePropertyResponseDTO;
import com.cts.model.Lease;
import com.cts.service.LeaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class LeaseControllerTest {

    @Mock
    private LeaseService leaseService;

    @InjectMocks
    private LeaseController leaseController;

    @Test
    void testSaveLease() {
        Lease lease = new Lease(1, 101, 201, "10/10/2025", "10/10/2026", "Agreement Terms", 5000, "ACTIVE");
        when(leaseService.saveLease(lease)).thenReturn("Lease saved successfully");

        ResponseEntity<String> response = leaseController.saveLease(lease);

        assertEquals("Lease saved successfully", response.getBody());
        verify(leaseService, times(1)).saveLease(lease);
    }

    @Test
    void testUpdateLease() {
        Lease lease = new Lease(1, 101, 201, "10/10/2025", "10/10/2026", "Updated Agreement", 5500, "ACTIVE");
        when(leaseService.updateLease(lease)).thenReturn(lease);

        ResponseEntity<Lease> response = leaseController.updateLease(lease);

        assertEquals(5500, response.getBody().getRentAmount());
        verify(leaseService, times(1)).updateLease(lease);
    }

    @Test
    void testGetLease() {
        Lease lease = new Lease(1, 101, 201, "10/10/2025", "10/10/2026", "Agreement Terms", 5000, "ACTIVE");
        LeasePropertyResponseDTO responseDTO = new LeasePropertyResponseDTO(lease, null); // Mock response

        when(leaseService.getLease(1)).thenReturn(responseDTO);

        LeasePropertyResponseDTO response = leaseController.getLease(1);

        assertEquals(5000, response.getLease().getRentAmount());
        verify(leaseService, times(1)).getLease(1);
    }

    @Test
    void testGetAllLeases() {
        Lease lease1 = new Lease(1, 101, 201, "10/10/2025", "10/10/2026", "Agreement Terms", 5000, "ACTIVE");
        Lease lease2 = new Lease(2, 102, 202, "11/11/2025", "11/11/2026", "Agreement Terms", 6000, "ACTIVE");
        List<Lease> leaseList = Arrays.asList(lease1, lease2);

        when(leaseService.getAllLease()).thenReturn(leaseList);

        List<Lease> response = leaseController.getAllLease();

        assertEquals(2, response.size());
        verify(leaseService, times(1)).getAllLease();
    }

    @Test
    void testDeleteLease() {
        when(leaseService.deleteLease(1)).thenReturn("Lease Deleted");

        String response = leaseController.deleteLease(1);

        assertEquals("Lease Deleted", response);
        verify(leaseService, times(1)).deleteLease(1);
    }

    @Test
    void testGetLeaseByTenant() {
        Lease lease1 = new Lease(1, 101, 201, "10/10/2025", "10/10/2026", "Agreement Terms", 5000, "ACTIVE");
        Lease lease2 = new Lease(2, 101, 202, "11/11/2025", "11/11/2026", "Agreement Terms", 6000, "ACTIVE");
        List<Lease> leaseList = Arrays.asList(lease1, lease2);

        when(leaseService.getLeaseByTenantId(101)).thenReturn(leaseList);

        List<Lease> response = leaseController.getLeaseByTenant(101);

        assertEquals(2, response.size());
        verify(leaseService, times(1)).getLeaseByTenantId(101);
    }
}
