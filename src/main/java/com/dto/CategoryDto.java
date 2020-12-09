package com.dto;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.model.Category;
import com.model.New;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private Long id;

	private String name;

	private int status;

	private Set<CategoryDto> children;

	private Long parentId;
	private String parentName;

	public CategoryDto(Category category) {
//		BeanUtils.copyProperties(category, this);
		this.id = category.getId();
		this.name = category.getName();
		this.status = category.getStatus();
		Set<Category> children = category.getChildren();
		if (!CollectionUtils.isEmpty(children)) {
			this.children = children.stream().map(CategoryDto::new).collect(Collectors.toSet());
		}
		Category parent = category.getParent();
		if (Objects.nonNull(parent)) {
			this.parentId = parent.getId();
			this.parentName = parent.getName();
		}
	}
}
