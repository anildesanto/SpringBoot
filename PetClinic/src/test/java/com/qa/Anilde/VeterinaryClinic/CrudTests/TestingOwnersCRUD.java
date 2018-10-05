package com.qa.Anilde.VeterinaryClinic.CrudTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.Anilde.VeterinaryClinic.PetClinicApplication;
import com.qa.Anilde.VeterinaryClinic.model.*;
import com.qa.Anilde.VeterinaryClinic.repository.OwnerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PetClinicApplication.class)
@DataJpaTest
public class TestingOwnersCRUD 
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private OwnerRepository ownerRepo;

	@Test
	public void ownerCreation() 
	{
		OwnerModel  model1 = new OwnerModel("Much", "Appreciated",5);
		entityManager.persist(model1);
		entityManager.flush();
		assertTrue(ownerRepo.findById(model1.getId()).isPresent());
	}
	@Test
	public void retrieveByName() 
	{
		OwnerModel  model2 = new OwnerModel("Jordan", "Lol World",50);
		entityManager.persist(model2);
		entityManager.flush();
		assertTrue(ownerRepo.findByName(model2.getName()).isPresent());
	}
	@Test
	public void postOwner() 
	{
		ownerRepo.save(new OwnerModel("Monchalata", "Boring Street",35));
		assertTrue(ownerRepo.findByName("Monchalata").isPresent());
	}

}
