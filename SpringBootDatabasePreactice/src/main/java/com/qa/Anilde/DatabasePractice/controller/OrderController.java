package com.qa.Anilde.DatabasePractice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.qa.Anilde.DatabasePractice.repository.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public class OrderController 
{
	@Autowired
	PersonRepository personRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@GetMapping("/person/{personId}/orders")
	public Page<Order> getAll
}
