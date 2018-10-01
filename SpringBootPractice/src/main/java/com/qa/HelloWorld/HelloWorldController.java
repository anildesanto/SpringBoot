package com.qa.HelloWorld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController 
{
	@RequestMapping("/")
	public String index()
	{
		return "This is Spring Boot*99";
	}

}
