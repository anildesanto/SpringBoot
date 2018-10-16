package com.qa.Anilde.DatabasePractice.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.Anilde.DatabasePractice.model.*;

public interface OrderRepository extends JpaRepository<OrderModel, Long>
{
	Page<OrderModel> findByPersonId(Long personId, Pageable pageable);
}
