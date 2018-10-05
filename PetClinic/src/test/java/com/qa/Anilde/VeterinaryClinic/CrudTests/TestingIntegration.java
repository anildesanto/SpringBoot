package com.qa.Anilde.VeterinaryClinic.CrudTests;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qa.Anilde.VeterinaryClinic.PetClinicApplication;
import com.qa.Anilde.VeterinaryClinic.model.OwnerModel;
import com.qa.Anilde.VeterinaryClinic.repository.OwnerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PetClinicApplication.class)
@AutoConfigureMockMvc
public class TestingIntegration
{

	@Autowired
	private OwnerRepository personRepo;
	
	@Autowired 
	private MockMvc mvc;
	
	JSONObject jason = new JSONObject();
	@Before
	public void clearDB() throws JSONException
	{
		personRepo.deleteAll();
		jason.put("name", "Matt");
		jason.put("address", "Damon");
		jason.put("age", 22);
	}
	@Test
	@Ignore
	public void findAndRetrievePerson() throws Exception 
	{
		
		personRepo.save(new OwnerModel(jason.getString("name"),jason.getString("address"), jason.getInt("age")));
		mvc.perform(get("/api/owner")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name", is(jason.getString("name")))
				);
	}
	@Test
	@Ignore
	public void testPostPerson() throws Exception 
	{
		personRepo.save(new OwnerModel(jason.getString("name"),jason.getString("address"), jason.getInt("age")));
		mvc.perform(MockMvcRequestBuilders.post("/api/owner")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jason.toString())).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is(jason.getString("name")))
				);
	}
	
	@Test
	public void testPut() throws Exception 
	{
		personRepo.save(new OwnerModel(jason.getString("name"),jason.getString("address"), jason.getInt("age")));
		mvc.perform(MockMvcRequestBuilders.put("/api/owner/16")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jason.toString())).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is(jason.getString("name")))
				);
	}
	@Test
	public void testDelete() throws Exception 
	{
		personRepo.save(new OwnerModel(jason.getString("name"),jason.getString("address"), jason.getInt("age")));
		mvc.perform(MockMvcRequestBuilders.delete("/api/owner/16")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jason.toString())).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is(jason.getString("name")))
				);
	}

}
