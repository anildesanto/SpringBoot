package com.qa.Anilde.DatabasePractice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.Anilde.DatabasePractice.exception.ResourceNotFoundException;
import com.qa.Anilde.DatabasePractice.model.OrderModel;
import com.qa.Anilde.DatabasePractice.repository.OrderRepository;
import com.qa.Anilde.DatabasePractice.repository.PersonRepository;

@RestController
@RequestMapping("/api")
public class OrderController 
{
	@Autowired
	PersonRepository personRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	private final String person = "Person";
	private final String personID = "personId";
	private final String orderID = "order_id";
	private final String iD = "id";
	private final String path = "/person/{personId}/orders";
	private final String pathID = path+"/{orderId}";
	
	@GetMapping(path)
	public Page<OrderModel> getAllOrdersByPersonId(@PathVariable(value = personID)Long personId, Pageable pageable)
	{
		return orderRepo.findByPersonId(personId, pageable);
	}
	
	@PostMapping(path)
	public OrderModel createComment(@PathVariable (value = personID) Long personId
			, @Valid @RequestBody OrderModel order)
	{
		return personRepo.findById(personId).map(
				personModel -> 
				{
					order.setPerson(personModel);
					return orderRepo.save(order);
				}).orElseThrow(() -> new ResourceNotFoundException(person,iD, order)
		);
	}
	
	@PutMapping(pathID)
	public  OrderModel updateOrder(@PathVariable (value = personID) Long personId
			, @PathVariable (value = orderID) Long orderId
			, @Valid @RequestBody OrderModel orderRequest)
	{
		if(!personRepo.existsById(personId))
		{
			throw new ResourceNotFoundException(person, iD,orderRequest);
		}
		
		return orderRepo.findById(orderId).map(order -> 
		{
			order.setTitle(orderRequest.getTitle());
			return orderRepo.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException(orderID, iD,orderRequest));
	}
	
	@DeleteMapping(pathID)
	public ResponseEntity<?> deleteComment(@PathVariable (value = personID) Long personId
			, @PathVariable (value = orderID) Long orderId)
	{
		if(!personRepo.existsById(personId))
		{
			throw new ResourceNotFoundException(person, iD,personId);
		}
		return orderRepo.findById(orderId).map(order -> 
		{
			orderRepo.delete(order);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(orderID, orderId.toString(),null));
	}
}
