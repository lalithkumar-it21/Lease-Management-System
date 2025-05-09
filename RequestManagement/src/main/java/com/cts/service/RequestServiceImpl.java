package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.RequestNotFound;

import com.cts.model.Request;

import com.cts.repository.RequestRepository;

@Service
public class RequestServiceImpl implements RequestService{
	@Autowired
	RequestRepository repository;
	
	
	@Override
	public String saveRequest(Request request) {
	    // Check if a request with the same request ID already exists
	    Optional<Request> existingRequest = repository.findById(request.getRequestId());

	    if (existingRequest.isPresent()) {
	        return "Request with this ID already exists"; // Prevent duplicate insertion
	    }

	    repository.save(request);
	    return "Request sent successfully";
	}


	@Override
	public Request getRequest(int requestId) throws RequestNotFound {
		Optional<Request> optional = repository.findById(requestId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new RequestNotFound("Invalid Request Id");
	}

	@Override
	public Request updateRequest(Request request) {
		return repository.save(request);
	}

	@Override
	public List<Request> getAllRequest() {
		return repository.findAll();
	}
	@Override
	public String deleteRequest(int requestId) {
		Optional<Request> request = repository.findById(requestId);
		repository.delete(request.get());
		return "Request Deleted";
	}
}
