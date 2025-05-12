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
        OwnerPropertyRequestDTO ownerProperty = new OwnerPropertyRequestDTO(
            new Owner(1, "John Doe", "9876543210", "123 Street, City", 101),
            null // Assuming property data is managed separately
        );
        when(ownerService.saveOwner(ownerProperty)).thenReturn("Owner and Property Saved !!!");

        ResponseEntity<String> response = ownerController.saveOwner(ownerProperty);

        assertEquals("Owner and Property Saved !!!", response.getBody());
        verify(ownerService, times(1)).saveOwner(ownerProperty);
    }

    @Test
    void testUpdateOwner() {
        Owner owner = new Owner(1, "John Doe", "9876543210", "Updated Address", 101);
        when(ownerService.updateOwner(owner)).thenReturn(owner);

        ResponseEntity<Owner> response = ownerController.updateOwner(owner);

        assertEquals("Updated Address", response.getBody().getOwnerAddress());
        verify(ownerService, times(1)).updateOwner(owner);
    }

    @Test
    void testGetOwner() {
        Owner owner = new Owner(1, "John Doe", "9876543210", "123 Street, City", 101);
        when(ownerService.getOwner(1)).thenReturn(owner);

        Owner response = ownerController.getOwner(1);

        assertEquals("John Doe", response.getOwnerName());
        verify(ownerService, times(1)).getOwner(1);
    }

    @Test
    void testGetAllOwners() {
        Owner owner1 = new Owner(1, "John Doe", "9876543210", "123 Street, City", 101);
        Owner owner2 = new Owner(2, "Jane Doe", "8765432109", "456 Avenue, City", 102);
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
