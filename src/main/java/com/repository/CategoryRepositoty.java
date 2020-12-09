package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Category;

@Repository
public interface CategoryRepositoty extends JpaRepository<Category, Long>{

}
