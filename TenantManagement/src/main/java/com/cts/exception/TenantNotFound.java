package com.cts.exception;

public class TenantNotFound extends RuntimeException {
	public TenantNotFound(String message) {
		super(message);
	}

}
