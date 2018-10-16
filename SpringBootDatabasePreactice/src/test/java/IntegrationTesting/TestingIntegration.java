package IntegrationTesting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qa.Anilde.DatabasePractice.DemoApplication;
import com.qa.Anilde.DatabasePractice.controller.PersonController;
import com.qa.Anilde.DatabasePractice.model.PersonModel;
import com.qa.Anilde.DatabasePractice.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class TestingIntegration
{
	@Autowired
	private PersonController dataController;
	@Autowired
	private PersonRepository myRepo;
	
	@Autowired 
	private MockMvc mvc;
	

	@Before
	public void clearDB()
	{
		myRepo.deleteAll();
	}
	@Test
	public void findAndRetrievePerson() throws Exception 
	{
		
		String name = "Karun";
		myRepo.save(new PersonModel (name, "Kerala",2));
		mvc.perform(get("/api/person")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name", is(name))
				);
	}
	@Test
	public void testPostPerson() throws Exception 
	{
		
		System.out.println("What?");
		String name = "Ron";
		JSONObject jason = new JSONObject();
		jason.put("name", name);
		jason.put("address", "Roadington");
		jason.put("age", 22);
		
		mvc.perform(MockMvcRequestBuilders.post("/api/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jason.toString())).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Ron"))
				);
	}

}
