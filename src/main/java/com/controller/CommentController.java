package com.controller;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CommentRequest;
import com.dto.CommentsDto;
import com.model.User;
import com.service.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

	private CommentService commentService;

	@PostMapping("/add")
	public CommentRequest createComment(@RequestBody CommentRequest comment) {
		return commentService.save(comment);

	}
	
	@GetMapping("/newid/{id}")
	public List<CommentsDto> getAllComment(@PathVariable Long id)
	{
		
		return commentService.getAllCommentsForNews(id);
		
	}
	@GetMapping("/user/{username}")
	public List<CommentsDto> getCommentByUserName(@PathVariable String username)
	{
		
		return commentService.getAllCommentForUser(username);
	}

}
