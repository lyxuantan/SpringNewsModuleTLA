package com.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


import com.dto.NewsResponse;
import com.model.New;

@Component
public class NewMapper {

	public New responseToEntity(NewsResponse newsResponse)
	{
		New news= new New();
		BeanUtils.copyProperties(newsResponse, news);
		return news;
		
	}
	public NewsResponse entityToResponse(New news)
	{
		NewsResponse newsresponse = new NewsResponse();
		BeanUtils.copyProperties(news, newsresponse);
		
		return newsresponse;
		
	}
}
