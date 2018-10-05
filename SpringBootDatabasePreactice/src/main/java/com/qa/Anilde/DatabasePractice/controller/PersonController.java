package com.qa.Anilde.DatabasePractice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.qa.Anilde.DatabasePractice.exception.ResourceNotFoundException;
import com.qa.Anilde.DatabasePractice.model.*;
import com.qa.Anilde.DatabasePractice.repository.PersonRepository;

@RestController
@RequestMapping("/api")
public class PersonController 
{
	@Autowired
	PersonRepository myRepository;
	
	//create person
	@PostMapping("/person")
	public PersonModel createPerson(@Valid @RequestBody PersonModel mSDM)
	{
		return myRepository.save(mSDM);
	}
	
	//get a person
	@GetMapping("/person/{id}")
	public PersonModel getPersonbyID(@PathVariable( value = "id") Long personID)
	{
		return myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("MySpringBootDataModel", "id", personID));	
	}
//	//get a person
//	@GetMapping("/person/{name}")
//	public PersonModel getPersonbyName(@PathVariable( value = "name") String personName)
//	{
//		return myRepository.findByName(personName).orElseThrow(() -> new ResourceNotFoundException("MySpringBootDataModel", "name", personName));	
//	}
	
	//get all people
	@GetMapping("/person")
	public List<PersonModel> getAllPeople()
	{
		return myRepository.findAll();
	}
	//update/put person
	@PutMapping("/person/{id}")
	public PersonModel updatePerson(@PathVariable (value = "id") Long personID, @Valid @RequestBody PersonModel personDetails)
	{
		PersonModel mSDM = myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personID));
		mSDM.setName(personDetails.getName());
		mSDM.setAddress(personDetails.getAddress());
		mSDM.setAge(personDetails.getAge());
		
		PersonModel updateData = myRepository.save(mSDM);
		return updateData;
	}
	
	//remove person
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson (@PathVariable (value = "id") Long personID)
	{
		PersonModel mSDM = myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personID));
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}
	
}
