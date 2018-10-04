package com.qa.Anilde.DatabasePractice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "person")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate", "lastModified"}, allowGetters = true)


public class PersonModel implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="id_generator", sequenceName = "person_seq", allocationSize=50)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String address;
	
	private Integer age;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date creationDate;
	
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastModified;

	
	public PersonModel()
	{}
	public PersonModel(String name, String address, Integer age)
	{
		this.name = name;
		this.address = address;
		this.age = age;
	}
	public String getAddress()
	{
		return address;
	}
	public Integer getAge()
	{
		return age;
	}
	public Date getCreationDate()
	{
		return creationDate;
	}
	public Long getId()
	{
		return id;
	}
	public Date getLastModified() 
	{
		return lastModified;
	}
	public String getName() 
	{
		return name;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public void setAge(Integer age) 
	{
		this.age = age;
	}
	public void setCreationDate(Date creationDate) 
	{
		this.creationDate = creationDate;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public void setLastModified(Date lastModified) 
	{
		this.lastModified = lastModified;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	

}
