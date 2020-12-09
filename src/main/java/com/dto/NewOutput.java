package com.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.model.New;

public class NewOutput {
	private int page;
	private int totalPage;
	private List<NewsResponse> listResult = new ArrayList<NewsResponse>();
	
	public int getPage()
	{
		return page;
	}
	
	public void setPage(int page)
	{
		this.page = page;
		
	}
	
	
	public int getTotalPage()
	{
		return totalPage;
	}
	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}
	
	public List<NewsResponse> getListResult()
	{
		return listResult;
	}
	public void setListResult(List<NewsResponse> page2)
	{
		this.listResult = page2;
	}

	
		
	

}
