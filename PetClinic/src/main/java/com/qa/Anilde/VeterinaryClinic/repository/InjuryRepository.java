package com.qa.Anilde.VeterinaryClinic.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.Anilde.VeterinaryClinic.model.*;

@Repository
public interface InjuryRepository extends JpaRepository<InjuryModel, Long>
{
	Page<InjuryModel> findByPetId(Long personId, Pageable pageable);
	Optional<InjuryModel> findByName(String name);
}
