package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long userId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Password")
	private String password;
	
	
	@Column(name ="Username")
	private String username;
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")

	List<Comment> comment = new ArrayList<Comment>();
	
	
	

	

}