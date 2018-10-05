package com.qa.Anilde.VeterinaryClinic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "injurys")
@EntityListeners(AuditingEntityListener.class)

public class InjuryModel implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="id_generator", sequenceName = "injury_seq", allocationSize=50)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "pet_id", nullable =  false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private PetModel pet;
	

	public PetModel getPet() {
		return pet;
	}
	public void setPet(PetModel pet) 
	{
		this.pet = pet;
	}
	public InjuryModel()
	{}
	public InjuryModel(String name,String description)
	{
		this.name = name;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	

}
