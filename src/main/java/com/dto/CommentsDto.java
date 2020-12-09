package com.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentsDto {
	
	private Long newId;
	
	private Long userId;
	
	private String content;
	
	private String username;
	private String title;
	
	
	

	

}
