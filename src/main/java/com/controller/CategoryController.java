package com.controller;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CategoryDto;
import com.dto.CategoryResponse;
import com.model.Category;
import com.service.CategoryService;
import com.service.NewsService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/category")
@AllArgsConstructor

public class CategoryController {
	private NewsService newService;
	private CategoryService categoryService;

	@GetMapping("/get")
	public List<CategoryDto> getAllList() {
		return categoryService.findAllIndent();

	}

	@PostMapping("/add")
	public Category save(@RequestBody CategoryResponse categoryResponse) {
		return categoryService.saveCategory(categoryResponse);
	}
	
	@DeleteMapping("/delete")
	public CategoryDto delete(@RequestParam("id") Long id)
	{
		return categoryService.deleteCategoryById(id);
	}

}
