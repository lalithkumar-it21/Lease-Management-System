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

import com.cts.exception.LeaseNotFoundException;
import com.cts.model.Lease;
import com.cts.repository.LeaseRepository;
import com.cts.service.LeaseServiceImpl;

class LeaseServiceTest {

    @Mock
    private LeaseRepository leaseRepository;

    @InjectMocks
    private LeaseServiceImpl leaseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLeases() {
        List<Lease> leases = Arrays.asList(
            new Lease(1, 101, 201, "2023-01-01", "2023-12-31", "Agreement A", 1200, "Active"),
            new Lease(2, 102, 202, "2023-02-01", "2023-12-31", "Agreement B", 1500, "Active")
        );

        when(leaseRepository.findAll()).thenReturn(leases);

        List<Lease> result = leaseService.getAllLease();
        assertEquals(2, result.size());
        verify(leaseRepository, times(1)).findAll();
    }

//    @Test
//    void testGetLeaseById_Success() throws LeaseNotFoundException {
//        Lease lease = new Lease(1, 101, 201, "2023-01-01", "2023-12-31", "Agreement A", 1200, "Active");
//        when(leaseRepository.findById(1)).thenReturn(Optional.of(lease));
//
//        Lease result = leaseService.getLease(1);
//        assertNotNull(result);
//        assertEquals(1200, result.getRentAmount());
//        verify(leaseRepository, times(1)).findById(1);
//    }

//    @Test
//    void testGetLeaseById_NotFound() {
//        when(leaseRepository.findById(1)).thenReturn(Optional.empty());
//
//        assertThrows(LeaseNotFoundException.class, () -> leaseService.getLease(1));
//    }

    @Test
    void testSaveLease() {
        Lease lease = new Lease(3, 103, 203, "2024-01-01", "2024-12-31", "Agreement C", 1800, "Active");
        when(leaseRepository.save(lease)).thenReturn(lease);

        String response = leaseService.saveLease(lease);
        assertEquals("Lease saved successfully", response);
        verify(leaseRepository, times(1)).save(lease);
    }

    @Test
    void testDeleteLease_Success() {
        Lease lease = new Lease(1, 101, 201, "2023-01-01", "2023-12-31", "Agreement A", 1200, "Active");
        when(leaseRepository.findById(1)).thenReturn(Optional.of(lease));

        String response = leaseService.deleteLease(1);
        assertEquals("Lease Deleted", response);
        verify(leaseRepository, times(1)).delete(lease);
    }

    @Test
    void testGetLeasesByTenantId() {
        List<Lease> leases = Arrays.asList(
            new Lease(1, 101, 201, "2023-01-01", "2023-12-31", "Agreement A", 1200, "Active"),
            new Lease(2, 102, 201, "2024-01-01", "2024-12-31", "Agreement B", 1400, "Active")
        );

        when(leaseRepository.findAll()).thenReturn(leases);

        List<Lease> result = leaseService.getLeaseByTenantId(201);
        assertEquals(2, result.size());
        verify(leaseRepository, times(1)).findAll();
    }
}
