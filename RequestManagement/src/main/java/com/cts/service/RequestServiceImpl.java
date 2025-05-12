package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.RequestNotFound;

import com.cts.model.Request;

import com.cts.repository.RequestRepository;

import jakarta.transaction.Transactional;

@Service
public class RequestServiceImpl implements RequestService {
	@Autowired
	RequestRepository repository;
	Logger log = LoggerFactory.getLogger(RequestServiceImpl.class);

	// To save request
	@Override
	public String saveRequest(Request request) {
		log.info("In RequestServiceImpl saverequest method...");
		// Check if a request with the same request ID already exists
		Optional<Request> existingRequest = repository.findById(request.getRequestId());

		if (existingRequest.isPresent()) {
			return "Request with this ID already exists"; // Prevent duplicate insertion
		}

		repository.save(request);
		return "Request sent successfully";
	}

	// To gete request
	@Override
	public Request getRequest(int requestId) throws RequestNotFound {
		log.info("In RequestServiceImpl getrequest method...");
		Optional<Request> optional = repository.findById(requestId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new RequestNotFound("Invalid Request Id");
	}

	// To update request
	@Override
	public Request updateRequest(Request request) {
		log.info("In RequestServiceImpl updaterequest method...");
		return repository.save(request);
	}

	// To get all request
	@Override
	public List<Request> getAllRequest() {
		log.info("In RequestServiceImpl getallrequest method...");
		return repository.findAll();
	}

	// To delete request
	@Transactional
	@Override
	public String deleteRequest(int requestId) {
		log.info("In RequestServiceImpl deleterequest method...");
		Optional<Request> request = repository.findById(requestId);
		repository.delete(request.get());
		return "Request Deleted";
	}
}
