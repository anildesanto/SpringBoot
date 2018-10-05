package com.qa.Anilde.VeterinaryClinic.repository;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.Anilde.VeterinaryClinic.model.*;

@Repository
public interface PetRepository extends JpaRepository<PetModel, Long>
{
	Page<PetModel> findByOwnerId(Long ownerId, Pageable pageable);
	Optional<PetModel> findByName(String name);
}
