package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

}
