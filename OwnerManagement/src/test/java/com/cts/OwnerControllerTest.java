package com.cts;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.controller.OwnerController;
import com.cts.model.Owner;
import com.cts.service.OwnerService;

class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController ownerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOwners() {
        List<Owner> owners = Arrays.asList(new Owner(1, "John Doe", "1234567890", "Address 1", 101),
                                           new Owner(2, "Jane Doe", "9876543210", "Address 2", 102));
        
        when(ownerService.getAllOwner()).thenReturn(owners);

        List<Owner> result = ownerController.getAllOwner();
        assertEquals(2, result.size());
        verify(ownerService, times(1)).getAllOwner();
    }

    @Test
    void testGetOwnerById_Success() {
        Owner owner = new Owner(1, "John Doe", "1234567890", "Address 1", 101);
        when(ownerService.getOwner(1)).thenReturn(owner);

        Owner result = ownerController.getOwner(1);
        assertNotNull(result);
        assertEquals("John Doe", result.getOwnerName());
        verify(ownerService, times(1)).getOwner(1);
    }

    @Test
    void testDeleteOwnerAndProperties() {
        int ownerId = 1;
        when(ownerService.deleteOwnerAndProperties(ownerId)).thenReturn("Owner and all associated properties deleted!");

        String response = ownerController.deleteOwnerAndProperties(ownerId);
        assertEquals("Owner and all associated properties deleted!", response);
        verify(ownerService, times(1)).deleteOwnerAndProperties(ownerId);
    }
}
