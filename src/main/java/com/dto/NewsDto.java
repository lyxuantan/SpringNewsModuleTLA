package com.dto;

import java.util.Date;
import javax.persistence.Lob;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.model.New;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Setter
@Getter
@Builder
public class NewsDto {

	private Long newId;
	
	private Long categoryId;
	private String author;

	private String content;

	private Integer status;

	private String title;


	
	private int view;

	private Date createTime;
	private Date updateTime;
	

	public NewsDto (New news) {
		
		BeanUtils.copyProperties(news, this);
		
		
		
	}

	

}
