package com.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class New implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	


	@Column(name = "Author")
	private String author;

	@Lob
	private String content;

	@CreationTimestamp
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "CreateTime")
	@Temporal(TemporalType.DATE)
	private Date createTime;

	@Column(name = "Status")
	private Integer status;

	@Column(name = "Title")
	private String title;
	
	
	@Column(name = "CategoryId")
	private Long categoryId;
	@Column(name = "UpdateTime")
	@Temporal(TemporalType.DATE)
	private Date updateTime;

	@Column(name = "View")
	private int view;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", insertable=false,updatable = false)
	
	private Category category;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "news")

	List<NewImage> image = new ArrayList<NewImage>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "news")

	List<Comment> comment = new ArrayList<Comment>();

	public void increaseViewCount() {
		view++;
	}

}