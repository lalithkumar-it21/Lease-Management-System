package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Lease;

public interface LeaseRepository extends JpaRepository<Lease, Integer> {

}
