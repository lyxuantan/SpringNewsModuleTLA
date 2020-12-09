package com.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Lob;

import org.springframework.beans.BeanUtils;

import com.model.New;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {


	
	private String author;

	@Lob
	private String content;

	private long createTime;

	private Integer status;


	private String title;

	
	private Long categoryId;
	
	private Long id;


	private Date updateTime;
	private int view;
	
	private String createTimeString;
	
	public NewsResponse (New news)
	{
		BeanUtils.copyProperties(news, this);
		if (Objects.nonNull(news.getCreateTime())) {
			 Date create = news.getCreateTime();
			this.createTime =create.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			this.createTimeString = sdf.format(create);
		}
		
	}

}
