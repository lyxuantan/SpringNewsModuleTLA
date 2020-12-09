package com.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




/**
 * The persistent class for the new_image database table.
 * 
 */
@Entity
@Table(name="new_image")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class NewImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	
	private Long imageId;
	

	
	
	
	@Column(name = "File")
	private String file;
	
	
//	@Column(name = "NewId")
//	private BigInteger newId;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NewId")
	private New news;
	

	

	

}