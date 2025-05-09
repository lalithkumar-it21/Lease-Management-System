package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer>{

}
