package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.OwnerPropertyRequestDTO;
import com.cts.dto.OwnerPropertyResponseDTO;
import com.cts.exception.OwnerNotFoundException;

import com.cts.model.Owner;

import com.cts.service.OwnerService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/owner")
public class OwnerController {
	@Autowired
	OwnerService service;

	@PostMapping("/save")
	public ResponseEntity<String> saveOwner(@Valid @RequestBody OwnerPropertyRequestDTO ownerProperty) {
		return ResponseEntity.ok(service.saveOwner(ownerProperty));
	}
@PutMapping("/update")
	public ResponseEntity<Owner> updateOwner(@Valid @RequestBody Owner owner) {
		return ResponseEntity.ok(service.updateOwner(owner));
	}
@GetMapping("/fetchById/{ownerid}")

public Owner getOwner(@PathVariable("ownerid") int ownerId) throws OwnerNotFoundException{
	return service.getOwner(ownerId);

	}

@DeleteMapping("/deleteOwnerAndProperties/{ownerId}")
public String deleteOwnerAndProperties(@PathVariable("ownerId") int ownerId) {
    return service.deleteOwnerAndProperties(ownerId);
}


@GetMapping("/fetchAll")
	public List<Owner> getAllOwner() {
		return service.getAllOwner();
	}


	
	
	
	
//	@PostMapping("/saveOnly")
//	public String saveOwnerOnly(@RequestBody Owner owner) {
//		return service.saveOwnerOnly(owner);
//	}
	
	
//	@DeleteMapping("/delete/{ownerid}")
//	public String deleteOwner(@PathVariable("ownerid") int ownerId) {
//		return service.deleteOwner(ownerId);
//	}
	
}
