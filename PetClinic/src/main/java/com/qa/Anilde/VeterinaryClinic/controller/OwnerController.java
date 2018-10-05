package com.qa.Anilde.VeterinaryClinic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.qa.Anilde.VeterinaryClinic.exception.ResourceNotFoundException;
import com.qa.Anilde.VeterinaryClinic.model.*;
import com.qa.Anilde.VeterinaryClinic.repository.OwnerRepository;

@RestController
@RequestMapping("/api")
public class OwnerController 
{
	@Autowired
	OwnerRepository ownerRepo;
	
	private final String path = "/owner";
	private final String pathID = path+"/{id}";
	private final String pathName = path+"/{name}";
	
	private final String iD = "id";
	private final String person = "Person";
	//create person
	@PostMapping(path)
	public OwnerModel createPerson(@Valid @RequestBody OwnerModel ownerModel)
	{
		return ownerRepo.save(ownerModel);
	}
	//get a person
	@GetMapping(pathID)
	public OwnerModel getPersonbyID(@PathVariable( value = iD) Long personID)
	{
		return ownerRepo.findById(personID).orElseThrow(() -> new ResourceNotFoundException(person, iD, personID));	
	}
	//get all people
	@GetMapping(path)
	public List<OwnerModel> getAllPeople()
	{
		return ownerRepo.findAll();
	}
	//update/put person
	@PutMapping(pathID)
	public OwnerModel updatePerson(@PathVariable (value = iD) Long personID, @Valid @RequestBody OwnerModel personDetails)
	{
		OwnerModel ownerModel = ownerRepo.findById(personID).orElseThrow(() -> new ResourceNotFoundException(person, iD, personID));
		ownerModel.setName(personDetails.getName());
		ownerModel.setAddress(personDetails.getAddress());
		ownerModel.setAge(personDetails.getAge());
		
		OwnerModel updateData = ownerRepo.save(ownerModel);
		return updateData;
	}
	
	//remove person
	@DeleteMapping(pathID)
	public ResponseEntity<?> deletePerson (@PathVariable (value = iD) Long personID)
	{
		OwnerModel ownerModel = ownerRepo.findById(personID).orElseThrow(() -> new ResourceNotFoundException(person, iD, personID));
		ownerRepo.delete(ownerModel);
		return ResponseEntity.ok().build();
	}
	
}
