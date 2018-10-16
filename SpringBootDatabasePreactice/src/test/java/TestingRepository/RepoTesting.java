package TestingRepository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.Anilde.DatabasePractice.DemoApplication;
import com.qa.Anilde.DatabasePractice.model.PersonModel;
import com.qa.Anilde.DatabasePractice.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@DataJpaTest
public class RepoTesting 
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private PersonRepository myRepo;
	
	@Test
	public void retrieveByID() 
	{
		PersonModel  model1 = new PersonModel("Bob", "Space",50);
		entityManager.persist(model1);
		entityManager.flush();
		assertTrue(myRepo.findById(model1.getId()).isPresent());
	}
	@Test
	public void retrieveByName() 
	{
		PersonModel  model2 = new PersonModel("Jordan", "Lol World",50);
		entityManager.persist(model2);
		entityManager.flush();
		assertTrue(myRepo.findByName(model2.getName()).isPresent());
	}

}
