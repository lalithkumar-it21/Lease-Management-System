package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;


import com.cts.controller.RequestController;
import com.cts.exception.RequestNotFound;
import com.cts.model.Request;
import com.cts.service.RequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class RequestControllerTest {

    @Mock
    private RequestService requestService;

    @InjectMocks
    private RequestController requestController;

    @Test
    void testSaveRequest() {
        Request request = new Request(1, 101, 201, 301, "PENDING");
        when(requestService.saveRequest(request)).thenReturn("Request sent successfully");

        ResponseEntity<String> response = requestController.saveRequest(request);

        assertEquals("Request sent successfully", response.getBody());
        verify(requestService, times(1)).saveRequest(request);
    }

    @Test
    void testUpdateRequest() {
        Request request = new Request(1, 101, 201, 301, "APPROVED");
        when(requestService.updateRequest(request)).thenReturn(request);

        ResponseEntity<Request> response = requestController.updateRequest(request);

        assertEquals("APPROVED", response.getBody().getRequestStatus());
        verify(requestService, times(1)).updateRequest(request);
    }

    @Test
    void testGetRequest() throws RequestNotFound {
        Request request = new Request(1, 101, 201, 301, "PENDING");
        when(requestService.getRequest(1)).thenReturn(request);

        Request response = requestController.getRequest(1);

        assertEquals(101, response.getTenantId());
        verify(requestService, times(1)).getRequest(1);
    }

    @Test
    void testGetAllRequest() {
        Request req1 = new Request(1, 101, 201, 301, "PENDING");
        Request req2 = new Request(2, 102, 202, 302, "APPROVED");
        List<Request> requestList = Arrays.asList(req1, req2);

        when(requestService.getAllRequest()).thenReturn(requestList);

        List<Request> response = requestController.getAllRequest();

        assertEquals(2, response.size());
        verify(requestService, times(1)).getAllRequest();
    }

    @Test
    void testDeleteRequest() {
        when(requestService.deleteRequest(1)).thenReturn("Request Deleted");

        String response = requestController.deleteRequest(1);

        assertEquals("Request Deleted", response);
        verify(requestService, times(1)).deleteRequest(1);
    }
}
