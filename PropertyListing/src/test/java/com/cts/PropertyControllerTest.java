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

import com.cts.controller.PropertyController;
import com.cts.exception.PropertyNotFoundException;
import com.cts.model.Property;
import com.cts.service.PropertyService;

class PropertyControllerTest {

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProperties() {
        List<Property> properties = Arrays.asList(
            new Property(101, 1, "Street 1", 1200, "3 BHK", "Available"),
            new Property(102, 2, "Street 2", 1500, "2 BHK", "Occupied")
        );

        when(propertyService.getAllProperty()).thenReturn(properties);

        List<Property> result = propertyController.getAllProperty();
        assertEquals(2, result.size());
        verify(propertyService, times(1)).getAllProperty();
    }

    @Test
    void testGetPropertyById_Success() throws PropertyNotFoundException {
        Property property = new Property(101, 1, "Street 1", 1200, "3 BHK", "Available");

        when(propertyService.getProperty(101)).thenReturn(property);

        Property result = propertyController.getProperty(101);
        assertNotNull(result);
        assertEquals("Street 1", result.getAddress());
        verify(propertyService, times(1)).getProperty(101);
    }

    @Test
    void testGetPropertyById_NotFound() {
        when(propertyService.getProperty(999)).thenThrow(new PropertyNotFoundException("Invalid Property Id"));

        assertThrows(PropertyNotFoundException.class, () -> propertyController.getProperty(999));
    }

    @Test
    void testSaveProperty() {
        Property property = new Property(103, 3, "Street 3", 1800, "1 BHK", "Available");

        when(propertyService.saveProperty(property)).thenReturn("Property saved successfully");

        ResponseEntity<String> response = propertyController.saveProperty(property);
        assertEquals("Property saved successfully", response);
        verify(propertyService, times(1)).saveProperty(property);
    }

    @Test
    void testDeleteProperty_Success() {
        when(propertyService.deleteProperty(101)).thenReturn("Property Deleted");

        String response = propertyController.deleteProperty(101);
        assertEquals("Property Deleted", response);
        verify(propertyService, times(1)).deleteProperty(101);
    }
}
