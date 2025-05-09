package com.example.Apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.example.Apigateway.util.JwtUtil;
import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil util;

    public static class Config {
    }

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return handleUnauthorized(exchange.getResponse(), "Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    String role = util.extractRolesFromToken(authHeader);
                    String requestedPath = exchange.getRequest().getPath().toString();
                    String method = exchange.getRequest().getMethod().name();

                    if (!isAuthorized(role, requestedPath, method)) {
                        return handleUnauthorized(exchange.getResponse(), "Unauthorized access");
                    }

                } catch (Exception e) {
                    return handleUnauthorized(exchange.getResponse(), "Invalid token");
                }
            }
            return chain.filter(exchange);
        };
    }

    private boolean isAuthorized(String role, String path, String method) {
    	if ("ADMIN".equalsIgnoreCase(role)) {
    	    return path.startsWith("/owner") || path.startsWith("/property") || path.startsWith("/lease") || path.startsWith("/tenant") || path.startsWith("/request");
    	} 

    	else if ("TENANT".equalsIgnoreCase(role)) {
    	    return path.startsWith("/tenant")  // Tenant can perform CRUD on tenant details
    	           || (path.startsWith("/property") && method.equalsIgnoreCase("GET")) // Tenant can view properties
    	           || (path.startsWith("/lease") && method.equalsIgnoreCase("GET")) // Tenant can view leases
    	           || (path.startsWith("/owner") && method.equalsIgnoreCase("GET"))// Tenant can view owners
    	           || (path.startsWith("/request") && (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("GET") ||method.equalsIgnoreCase("DELETE"))); // Tenant can add get delete request
    	}

    	else if ("OWNER".equalsIgnoreCase(role)) {
    	    return path.startsWith("/owner")  // Owner can perform CRUD on owner details
    	           || path.startsWith("/lease") // Owner can perform CRUD on leases
    	           || path.startsWith("/property") // Owner can perform CRUD on properties
    	           || (path.startsWith("/tenant") && method.equalsIgnoreCase("GET")) // Owner can view tenants
    	           || (path.startsWith("/request") && (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("PUT"))); // Owner can view and update requests
    	}

    	return false;



    }

    private Mono<Void> handleUnauthorized(ServerHttpResponse response, String message) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }
}
