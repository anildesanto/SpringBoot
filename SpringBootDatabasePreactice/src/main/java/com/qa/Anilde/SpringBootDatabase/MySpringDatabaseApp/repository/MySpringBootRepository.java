package com.qa.Anilde.SpringBootDatabase.MySpringDatabaseApp.repository;


import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.Anilde.SpringBootDatabase.MySpringDatabaseApp.model.*;

@Repository
public interface MySpringBootRepository extends JpaRepository<MySpringBootDataModel, Long>
{

}
