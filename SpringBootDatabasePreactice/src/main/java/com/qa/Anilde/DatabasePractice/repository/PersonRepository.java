package com.qa.Anilde.DatabasePractice.repository;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.Anilde.DatabasePractice.model.*;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long>
{
	Optional<PersonModel> findByName(String name);
}
