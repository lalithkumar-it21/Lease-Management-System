package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {

}
