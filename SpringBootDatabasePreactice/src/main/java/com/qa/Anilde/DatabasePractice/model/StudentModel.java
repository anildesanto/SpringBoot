package com.qa.Anilde.DatabasePractice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentModel 
{
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String passportNumber;
}