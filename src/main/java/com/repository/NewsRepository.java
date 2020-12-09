package com.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Category;
import com.model.New;


@Repository
public interface NewsRepository extends JpaRepository<New, Long>{

	Optional<New> findById(Long id);
	Optional<New> findByTitle(String title);
	
	List<New> findByCategoryId(Long id);
	
	
	
} 
