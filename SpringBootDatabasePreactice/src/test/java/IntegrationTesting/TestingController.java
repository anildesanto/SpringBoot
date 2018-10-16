package IntegrationTesting;

import static org.junit.Assert.*;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.Anilde.DatabasePractice.DemoApplication;
import com.qa.Anilde.DatabasePractice.controller.PersonController;
import com.qa.Anilde.DatabasePractice.model.PersonModel;
import com.qa.Anilde.DatabasePractice.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestingController 
{
	@Autowired
	private PersonController dataController;
	@Autowired
	private PersonRepository myRepo;
	
	private String name;
	private String address;
	private Integer age;
	
	boolean check;
	@Before
	public void clearDB()
	{
		myRepo.deleteAll();
		check = true;
	}
	public void create(String name, String address, Integer age)
	{
		this.name = name;
		this.address = address;
		this.age = age;
		dataController.createPerson(new PersonModel(name, address, age));
	}
	@Ignore
	@Test
	public void testPersonCreation() 
	{
		create("lol","Laughs Street", 33);
		assertTrue(myRepo.findByName("Jalapopo").isPresent());
	}
	@Ignore
	@Test
	public void testDeleteByID() throws JSONException 
	{
		try
		{
			dataController.deletePerson(4l);
		}
		catch (Exception e)
		{
			check = false;
		}
		assertTrue(check);
	}
	@Ignore
	@Test
	public void testUpdateByID() throws JSONException 
	{
		create("Jalapopo","Popo Street", 45);
		try
		{
			assertNotNull(dataController.updatePerson(13l, new PersonModel (name, address,age)));
		}
		catch (Exception e)
		{
			check = false;
		}
		assertTrue(check);
	}
	

}
