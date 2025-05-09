package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cts.controller.PropertyController;
import com.cts.model.Property;
import com.cts.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    @Test
    void testSaveProperty() {
        Property property = new Property(101, 1, "123 Main Street", 5000, "2 BHK Apartment", "AVAILABLE");
        when(propertyService.saveProperty(property)).thenReturn("Property saved successfully");

        ResponseEntity<String> response = propertyController.saveProperty(property);

        assertEquals("Property saved successfully", response.getBody());
        verify(propertyService, times(1)).saveProperty(property);
    }

    @Test
    void testUpdateProperty() {
        Property property = new Property(101, 1, "123 Main Street", 5500, "Updated Apartment Details", "AVAILABLE");
        when(propertyService.updateProperty(property)).thenReturn(property);

        ResponseEntity<Property> response = propertyController.updateProperty(property);

        assertEquals(5500, response.getBody().getRentAmount());
        verify(propertyService, times(1)).updateProperty(property);
    }

    @Test
    void testGetProperty() {
        Property property = new Property(101, 1, "123 Main Street", 5000, "2 BHK Apartment", "AVAILABLE");
        when(propertyService.getProperty(101)).thenReturn(property);

        Property response = propertyController.getProperty(101);

        assertEquals("123 Main Street", response.getAddress());
        verify(propertyService, times(1)).getProperty(101);
    }

    @Test
    void testGetAllProperties() {
        Property property1 = new Property(101, 1, "123 Main Street", 5000, "2 BHK Apartment", "AVAILABLE");
        Property property2 = new Property(102, 2, "456 Side Lane", 6000, "3 BHK Apartment", "AVAILABLE");
        List<Property> propertyList = Arrays.asList(property1, property2);

        when(propertyService.getAllProperty()).thenReturn(propertyList);

        List<Property> response = propertyController.getAllProperty();

        assertEquals(2, response.size());
        verify(propertyService, times(1)).getAllProperty();
    }

    @Test
    void testGetPropertiesByOwner() {
        Property property1 = new Property(101, 1, "123 Main Street", 5000, "2 BHK Apartment", "AVAILABLE");
        Property property2 = new Property(102, 1, "789 Central Ave", 7000, "4 BHK Apartment", "AVAILABLE");
        List<Property> propertyList = Arrays.asList(property1, property2);

        when(propertyService.getPropertiesByOwnerId(1)).thenReturn(propertyList);

        List<Property> response = propertyController.getPropertiesByOwner(1);

        assertEquals(2, response.size());
        verify(propertyService, times(1)).getPropertiesByOwnerId(1);
    }

    @Test
    void testGetPropertiesByAddressSimilarity() {
        Property property1 = new Property(101, 1, "123 Main Street", 5000, "2 BHK Apartment", "AVAILABLE");
        Property property2 = new Property(102, 2, "456 Main Road", 6000, "3 BHK Apartment", "AVAILABLE");
        List<Property> propertyList = Arrays.asList(property1, property2);

        when(propertyService.getPropertiesByAddressSimilarity("Main")).thenReturn(propertyList);

        List<Property> response = propertyController.getPropertiesByAddressSimilarity("Main");

        assertEquals(2, response.size());
        verify(propertyService, times(1)).getPropertiesByAddressSimilarity("Main");
    }

    @Test
    void testGetPropertiesByRentRange() {
        Property property1 = new Property(101, 1, "123 Main Street", 5000, "2 BHK Apartment", "AVAILABLE");
        Property property2 = new Property(102, 2, "789 Central Ave", 7000, "4 BHK Apartment", "AVAILABLE");
        List<Property> propertyList = Arrays.asList(property1, property2);

        when(propertyService.getPropertiesByRentRange(4000, 8000)).thenReturn(propertyList);

        List<Property> response = propertyController.getPropertiesByRentRange(4000, 8000);

        assertEquals(2, response.size());
        verify(propertyService, times(1)).getPropertiesByRentRange(4000, 8000);
    }

    @Test
    void testDeleteProperty() {
        when(propertyService.deleteProperty(101)).thenReturn("Property Deleted");

        String response = propertyController.deleteProperty(101);

        assertEquals("Property Deleted", response);
        verify(propertyService, times(1)).deleteProperty(101);
    }
}
