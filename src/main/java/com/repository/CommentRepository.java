package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Comment;
import com.model.New;
import com.model.User;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
		List<Comment> findByNews(New news);
		
		List<Comment> findByUser(User user); 

}
