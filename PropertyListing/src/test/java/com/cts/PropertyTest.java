package com.cts;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cts.controller.PropertyController;
import com.cts.exception.PropertyNotFoundException;
import com.cts.model.Property;
import com.cts.repository.PropertyRepository;
import com.cts.service.PropertyServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PropertyTest {

    @Mock
    private PropertyRepository repository;

    @InjectMocks
    private PropertyServiceImpl service;

    @InjectMocks
    private PropertyController controller;

    private MockMvc mockMvc;
    private Property property;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        property = new Property(1, 1, "Sipcot, Chennai", 500000, "It is a good house", "AVAILABLE");
    }

    // Service Tests
    @Test
    void testSaveProperty() {
        when(repository.save(property)).thenReturn(property);
        String response = service.saveProperty(property);
        assertEquals("Property saved successfully", response);
        verify(repository, times(1)).save(property);
    }

    @Test
    void testGetPropertySuccess() {
        when(repository.findById(1)).thenReturn(Optional.of(property));
        Property foundProperty = service.getProperty(1);
        assertEquals(property, foundProperty);
    }

    @Test
    void testGetPropertyFailure() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(PropertyNotFoundException.class, () -> service.getProperty(2));
    }

    @Test
    void testDeleteProperty() {
        when(repository.findById(1)).thenReturn(Optional.of(property));
        String response = service.deleteProperty(1);
        assertEquals("Property Deleted", response);
        verify(repository, times(1)).delete(property);
    }

    @Test
    void testGetPropertiesByOwnerId() {
        when(repository.findAll()).thenReturn(Arrays.asList(property));
        List<Property> properties = service.getPropertiesByOwnerId(1);
        assertFalse(properties.isEmpty());
        assertEquals(1, properties.size());
    }

    // Controller Tests
    @Test
    void testSavePropertyController() throws Exception {
        when(service.saveProperty(any(Property.class))).thenReturn("Property saved successfully");

        mockMvc.perform(post("/property/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"propertyId\": 1, \"ownerId\": 1, \"address\": \"Sipcot, Chennai\", \"rentAmount\": 500000, \"propertyDetails\": \"It is a good house\", \"availabilityStatus\": \"AVAILABLE\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("Property saved successfully"));

        verify(service, times(1)).saveProperty(any(Property.class));
    }

    @Test
    void testGetPropertySuccessController() throws Exception {
        when(service.getProperty(1)).thenReturn(property);

        mockMvc.perform(get("/property/fetchById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("Sipcot, Chennai"));

        verify(service, times(1)).getProperty(1);
    }

    @Test
    void testGetPropertyFailureController() throws Exception {
        when(service.getProperty(2)).thenThrow(new RuntimeException("Invalid Property Id"));

        mockMvc.perform(get("/property/fetchById/2"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Invalid Property Id"));
    }
}
