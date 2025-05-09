package com.cts.service;

import java.util.List;

import com.cts.exception.RequestNotFound;
import com.cts.model.Request;



public interface RequestService {
	
	public abstract String saveRequest(Request request);
	public abstract Request getRequest(int requestId)throws RequestNotFound;
	public abstract Request updateRequest(Request request);
	public abstract List<Request> getAllRequest();
	public abstract String deleteRequest(int requestId);
	

}
