package com.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the type_new database table.
 * 
 */
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
//	@Column(name = "ParrentId")
//	private Long parrentId;
	

	@Column(name = "Name")
	private String name;

	@Column(name = "Status")
	private int status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<New> news;
	
	@OneToMany(mappedBy = "parent")
	private Set<Category> children;

	@ManyToOne
	@JoinColumn(name = "ParrentId", insertable=false,updatable = false)
	private Category parent;
}