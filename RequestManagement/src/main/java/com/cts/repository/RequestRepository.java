package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

}
