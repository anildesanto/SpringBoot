package com.qa.Anilde.VeterinaryClinic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.qa.Anilde.VeterinaryClinic.exception.ResourceNotFoundException;
import com.qa.Anilde.VeterinaryClinic.model.*;
import com.qa.Anilde.VeterinaryClinic.repository.PetRepository;
import com.qa.Anilde.VeterinaryClinic.repository.OwnerRepository;

@RestController
@RequestMapping("/api")
public class PetController 
{
	@Autowired
	PetRepository petRepo;
	
	@Autowired
	OwnerRepository ownerRepo;
	
//	private final String path = "/pet";
//	private final String pathID = path+"/{id}";
//	private final String pathName = path+"/{name}";
//	
//	private final String iD = "id";
//	private final String pet = "Pet";
	
	private final String path = "/owner/{ownerId}/pet";
	private final String pathID = path+"/{id}";
	private final String pathName = path+"/{name}";
	private final String iD = "id";
	private final String injuryID = "owner_id";
	private final String pet = "Owner";
	private final String petID = "ownerId";
	
	
	@GetMapping(path)
	public Page<PetModel> getAllPetsByOwnerId(@PathVariable(value = "ownerId")Long ownerId, Pageable pageable)
	{
		return petRepo.findByOwnerId(ownerId, pageable);
	}
	
	@PostMapping(path)
	public PetModel createComment(@PathVariable (value = petID) Long petId
			, @Valid @RequestBody PetModel order)
	{
		return ownerRepo.findById(petId).map(
				petModel -> 
				{
					order.setOwners(petModel);
					return petRepo.save(order);
				}).orElseThrow(() -> new ResourceNotFoundException(pet,iD, order)
		);
	}
	
	@PutMapping(pathID)
	public  PetModel updateOrder(@PathVariable (value = petID) Long petId
			, @PathVariable (value = injuryID) Long injuryId
			, @Valid @RequestBody PetModel injuryRequest)
	{
		if(!ownerRepo.existsById(petId))
		{
			throw new ResourceNotFoundException(pet, iD,injuryRequest);
		}
		
		return petRepo.findById(injuryId).map(order -> 
		{
			order.setName(injuryRequest.getName());
			return petRepo.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException(injuryID, iD,injuryRequest));
	}
	
	@DeleteMapping(pathID)
	public ResponseEntity<?> deleteComment(@PathVariable (value = petID) Long petId
			, @PathVariable (value = injuryID) Long injuryId)
	{
		if(!ownerRepo.existsById(petId))
		{
			throw new ResourceNotFoundException(pet, iD,petId);
		}
		return petRepo.findById(injuryId).map(order -> 
		{
			petRepo.delete(order);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(injuryID, injuryId.toString(),null));
	}
	

}
