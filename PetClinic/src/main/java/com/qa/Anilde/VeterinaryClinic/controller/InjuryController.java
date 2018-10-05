package com.qa.Anilde.VeterinaryClinic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.Anilde.VeterinaryClinic.exception.ResourceNotFoundException;
import com.qa.Anilde.VeterinaryClinic.model.*;
import com.qa.Anilde.VeterinaryClinic.repository.*;

@RestController
@RequestMapping("/api")
public class InjuryController 
{
	@Autowired
	InjuryRepository injuryRepo;
	
	@Autowired
	PetRepository petRepo;
	
	private final String path = "/pet/{petId}/injurys";
	private final String pathID = path+"/{id}";
	private final String iD = "id";
	private final String injuryID = "injury_id";
	private final String pet = "Pet";
	private final String petID = "petId";
	
	@GetMapping(path)
	public Page<InjuryModel> getAllOrdersByPersonId(@PathVariable(value = "petId")Long petId, Pageable pageable)
	{
		return injuryRepo.findByPetId(petId, pageable);
	}
	
	
	@PostMapping(path)
	public InjuryModel createComment(@PathVariable (value = petID) Long petId
			, @Valid @RequestBody InjuryModel order)
	{
		return petRepo.findById(petId).map(
				petModel -> 
				{
					order.setPet(petModel);
					return injuryRepo.save(order);
				}).orElseThrow(() -> new ResourceNotFoundException(pet,iD, order)
		);
	}
	
	@PutMapping(pathID)
	public  InjuryModel updateOrder(@PathVariable (value = petID) Long petId
			, @PathVariable (value = injuryID) Long injuryId
			, @Valid @RequestBody InjuryModel injuryRequest)
	{
		if(!petRepo.existsById(petId))
		{
			throw new ResourceNotFoundException(pet, iD,injuryRequest);
		}
		
		return injuryRepo.findById(injuryId).map(order -> 
		{
			order.setName(injuryRequest.getName());
			return injuryRepo.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException(injuryID, iD,injuryRequest));
	}
	
	@DeleteMapping(pathID)
	public ResponseEntity<?> deleteComment(@PathVariable (value = petID) Long petId
			, @PathVariable (value = injuryID) Long injuryId)
	{
		if(!petRepo.existsById(petId))
		{
			throw new ResourceNotFoundException(pet, iD,petId);
		}
		return injuryRepo.findById(injuryId).map(order -> 
		{
			injuryRepo.delete(order);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(injuryID, injuryId.toString(),null));
	}
	
}
