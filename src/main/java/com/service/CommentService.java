package com.service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.CommentRequest;
import com.dto.CommentsDto;
import com.dto.NewsDto;
import com.dto.NewsResponse;
import com.exeption.CustomException;
import com.mapper.CommentMapper;
import com.model.Comment;
import com.model.New;
import com.model.User;
import com.repository.CommentRepository;
import com.repository.NewsRepository;
import com.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CommentService {

	private NewsRepository newsRepository;
	private CommentRepository commentRepository;
	private CommentMapper commentMapper;
	private UserRepository userRepository;

	@Transactional
	public CommentRequest save(CommentRequest commentsRequest) {

		commentRepository.save(commentMapper.mapToComment(commentsRequest));
		return commentsRequest;
	}

	public List<CommentsDto> getAllCommentsForNews(Long id) {

		New news = newsRepository.findById(id).orElseThrow(() -> new CustomException("NOT FOUND"));

		return commentRepository.findByNews(news).stream().map(commentMapper::mapToDto).filter(x -> x.getNewId() == id)
				.collect(Collectors.toList());

	}

	public List<CommentsDto> getAllCommentForUser(String username) {
		User userI = userRepository.findByUsername(username);
		return commentRepository.findByUser(userI).stream().map(commentMapper::mapToDto)
				.collect(Collectors.toList());

	}
	
//	public List<CommentsDto> findAll()
//	{
//		List<CommentsDto> results = new ArrayList<CommentsDto>();
//		List<Comment> entities = commentRepository.findAll();
//		for(Comment item : entities)
//		{
//			
//			CommentsDto commentDto  = commentMapper.mapToDto(item);
//			results.add(commentDto);
//		}
//		return results;
//	
//	}

}
