package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.CategoryDto;
import com.dto.CategoryResponse;
import com.dto.CommentsDto;
import com.dto.NewOutput;
import com.exeption.CustomException;
import com.mapper.CategoryMapper;
import com.model.Category;
import com.model.Comment;
import com.repository.CategoryRepositoty;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CategoryService {
	
	private CategoryRepositoty categoryRepository;

	public List<CategoryDto> findAllIndent() {
		List<Category> results = categoryRepository.findAll();
		return results.stream().map(CategoryDto::new).collect(Collectors.toList());
	}
	
	
	public Category saveCategory(CategoryResponse categoryResponse)
	{
		Category category = new Category();
		category.setName(categoryResponse.getName());
		category.setStatus(categoryResponse.getStatus());
		return categoryRepository.save(category);
	}
	public CategoryDto deleteCategoryById(Long id)
	{
		Category category = categoryRepository.findById(id).orElseThrow(() -> new CustomException("NOT FOUND"));
		category.setStatus(0);
		return new CategoryDto(category); 
	}
}
