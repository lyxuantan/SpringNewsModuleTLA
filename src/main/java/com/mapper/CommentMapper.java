package com.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.dto.CommentRequest;
import com.dto.CommentsDto;
import com.model.Comment;


@Component
public class CommentMapper {
	public CommentsDto mapToDto(Comment comment) {
		CommentsDto commentDto = new CommentsDto();

		BeanUtils.copyProperties(comment, commentDto);

		commentDto.setUserId(comment.getUser().getUserId());
		commentDto.setNewId(comment.getNews().getId());
		commentDto.setTitle(comment.getNews().getTitle());
		commentDto.setUsername(comment.getUser().getUsername());
		return commentDto;

	}
	
	public Comment mapToComment(CommentRequest commentRequest)
	{
		Comment comment = new Comment();
		
		
		
		BeanUtils.copyProperties(commentRequest, comment);
			
		return comment;
	}
	


}
