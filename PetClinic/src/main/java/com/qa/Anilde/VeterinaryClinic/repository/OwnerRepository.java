package com.qa.Anilde.VeterinaryClinic.repository;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.Anilde.VeterinaryClinic.model.*;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerModel, Long>
{
	Optional<OwnerModel> findByName(String name);
}
