package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.RequestNotFound;
import com.cts.model.Request;
import com.cts.service.RequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/request")
public class RequestController {
	
	@Autowired
	RequestService service;
	  
	@PostMapping("/save")
	public ResponseEntity<String> saveRequest(@Valid @RequestBody Request request) {
		return ResponseEntity.ok(service.saveRequest(request));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Request> updateRequest(@RequestBody Request request) {
		return ResponseEntity.ok(service.updateRequest(request));
	}

	@GetMapping("/fetchById/{requestid}")
	public Request getRequest(@PathVariable("requestid") int requestId) throws RequestNotFound {
		return service.getRequest(requestId);
	}

	@GetMapping("/fetchAll")
	public List<Request> getAllRequest() {
		return service.getAllRequest();
	}
	
	@DeleteMapping("/delete/{requestid}")
	public String deleteRequest(@PathVariable("requestid") int requestId) {
		return service.deleteRequest(requestId);
	}
}