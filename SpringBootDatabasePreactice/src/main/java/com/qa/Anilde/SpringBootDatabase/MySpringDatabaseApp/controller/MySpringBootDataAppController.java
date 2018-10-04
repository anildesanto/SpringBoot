package com.qa.Anilde.SpringBootDatabase.MySpringDatabaseApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.qa.Anilde.SpringBootDatabase.MySpringDatabaseApp.model.MySpringBootDataModel;
import com.qa.Anilde.SpringBootDatabase.MySpringDatabaseApp.repository.MySpringBootRepository;
import com.qa.Anilde.SpringBootDatabase.MySpringDatabaseApp.exception.ResourceNotFoundException;
import com.qa.Anilde.SpringBootDatabase.MySpringDatabaseApp.model.*;

@RestController
@RequestMapping("/api")
public class MySpringBootDataAppController 
{
	@Autowired
	MySpringBootRepository myRepository;
	
	//create person
	@PostMapping("/MyySpringDataModel")
	public MySpringBootDataModel createPerson(@Valid @RequestBody MySpringBootDataModel mSDM)
	{
		return myRepository.save(mSDM);
	}
	
	//get a person
	@GetMapping("person/id")
	public MySpringBootDataModel getPersonbyID(@PathVariable( value = "id") Long personID)
	{
		return myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("MySpringBootDataModel", "id", personID));	
	}
	
	//get all people
	@GetMapping("/person0")
	public List<MySpringBootDataModel> getAllPeople()
	{
		return myRepository.findAll();
	}
	//update/put person
	@PutMapping("/person/{}id")
	public MySpringBootDataModel upadatePerson(@PathVariable (value = "id") Long personID, @Valid @RequestBody MySpringBootDataModel personDetails)
	{
		MySpringBootDataModel mSDM = myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personID));
		mSDM.setName(personDetails.getName());
		mSDM.setAddress(personDetails.getAddress());
		mSDM.setAge(personDetails.getAge());
		
		MySpringBootDataModel updateData = myRepository.save(mSDM);
		return updateData;
	}
	
	//remove person
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson (@PathVariable (value = "id") Long personID)
	{
		MySpringBootDataModel mSDM = myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personID));
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}
	
	
	
}
