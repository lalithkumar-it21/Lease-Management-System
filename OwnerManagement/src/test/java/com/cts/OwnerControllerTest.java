package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;


import com.cts.controller.OwnerController;
import com.cts.dto.OwnerPropertyRequestDTO;
import com.cts.model.Owner;
import com.cts.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController ownerController;

    @Test
    void testSaveOwner() {
        Owner owner = 
            new Owner(1, "John Doe", "9876543210", "123 Street, City");
        when(ownerService.saveOwner(owner)).thenReturn("Owner saved successfully");

        ResponseEntity<String> response = ownerController.saveOwner(owner);

        assertEquals("Owner saved successfully", response.getBody());
        verify(ownerService, times(1)).saveOwner(owner);
    }

    @Test
    void testUpdateOwner() {
        Owner owner = new Owner(1, "John Doe", "9876543210", "Updated Address");
        when(ownerService.updateOwner(owner)).thenReturn(owner);

        ResponseEntity<Owner> response = ownerController.updateOwner(owner);

        assertEquals("Updated Address", response.getBody().getOwnerAddress());
        verify(ownerService, times(1)).updateOwner(owner);
    }

    @Test
    void testGetOwner() {
        Owner owner = new Owner(1, "John Doe", "9876543210", "123 Street, City");
        when(ownerService.getOwner(1)).thenReturn(owner);

        Owner response = ownerController.getOwner(1);

        assertEquals("John Doe", response.getOwnerName());
        verify(ownerService, times(1)).getOwner(1);
    }

    @Test
    void testGetAllOwners() {
        Owner owner1 = new Owner(1, "John Doe", "9876543210", "123 Street, City");
        Owner owner2 = new Owner(2, "Jane Doe", "8765432109", "456 Avenue, City");
        List<Owner> owners = Arrays.asList(owner1, owner2);

        when(ownerService.getAllOwner()).thenReturn(owners);

        List<Owner> response = ownerController.getAllOwner();

        assertEquals(2, response.size());
        verify(ownerService, times(1)).getAllOwner();
    }

    @Test
    void testDeleteOwnerAndProperties() {
        when(ownerService.deleteOwnerAndProperties(1)).thenReturn("Owner and all associated properties deleted!");

        String response = ownerController.deleteOwnerAndProperties(1);

        assertEquals("Owner and all associated properties deleted!", response);
        verify(ownerService, times(1)).deleteOwnerAndProperties(1);
    }
}
