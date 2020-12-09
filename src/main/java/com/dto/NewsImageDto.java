package com.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.model.NewImage;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@Component
@Getter
@Setter
public class NewsImageDto {
	private Long imageId;
	private String file;
	
	
	public NewsImageDto entityToDto(NewImage newsImage) {
		NewsImageDto newsImaege = new NewsImageDto();
		BeanUtils.copyProperties(newsImage, newsImaege);
		return newsImaege;
		
	}
	
	
	
}
