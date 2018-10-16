package com.qa.Anilde.VeterinaryClinic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController 
{
	@RequestMapping("/")
	public String index()
	{
		return "This is The Pet Clinic";
	}

}