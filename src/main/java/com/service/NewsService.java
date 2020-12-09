package com.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Lob;
import javax.swing.event.MenuDragMouseEvent;

import org.modelmapper.internal.bytebuddy.description.method.MethodList.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.constant.ErrorCode;
import com.dto.CategoryDto;

import com.dto.NewOutput;
import com.dto.NewsDto;
import com.dto.NewsResponse;
import com.exeption.CustomException;
import com.mapper.CategoryMapper;
import com.mapper.NewMapper;
import com.model.Category;
import com.model.New;

import com.repository.CategoryRepositoty;
import com.repository.NewsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NewsService {

	private NewsRepository newRepository;

	private NewsDto newDto;

	private NewMapper newMapper;

	public NewsDto findByTitle(String title) {
		New news = newRepository.findByTitle(title).orElseGet(null);
		NewsDto newsDto = new NewsDto(news);
		return newsDto;

	}

	public List<NewsResponse> findAll(Pageable pageable) {
		List<NewsResponse> results = new ArrayList<NewsResponse>();
		List<New> entities = newRepository.findAll(pageable).getContent();
		for (New item : entities) {
			NewsResponse newDto = newMapper.entityToResponse(item);
			results.add(newDto);
		}
		return results;
	}

	public Page<NewsResponse> findByPaging(Pageable pageable) {
		Page<New> findAll = newRepository.findAll(pageable);
		return findAll.map(NewsResponse::new);
	}

	public NewOutput showNewOutputByDate(int page, int limit) {
		NewOutput output = new NewOutput();
		output.setPage(page);
		Pageable pageable = PageRequest.of(page, limit, Sort.by("updateTime").descending());
		output.setListResult(findAll(pageable));
		output.setTotalPage((int) Math.ceil((double) (totalItem()) / limit));
		return output;

	}

//	public NewOutput showNewOutputByView(int page, int limit) {
//		NewOutput output = new NewOutput();
//		output.setPage(page);
//		Pageable pageble = PageRequest.of(page, limit, Sort.by("view").descending());
//		output.setListResult(findAll(pageble));
//		output.setTotalPage((int) Math.ceil((double) (totalItem()) / limit));
//		return output;
//	}

	public NewsResponse save(NewsResponse newsResponse) {

		New news = newRepository.save(newMapper.responseToEntity(newsResponse));
		return newMapper.entityToResponse(news);

	}

	public New saveNow(NewsResponse newsResponse) {
		Date date = new Date();
		New news = new New();
		news.setAuthor(newsResponse.getAuthor());
		news.setContent(newsResponse.getContent());
		news.setStatus(news.getStatus());
		news.setTitle(newsResponse.getTitle());
		news.setCategoryId(newsResponse.getCategoryId());
		news.setUpdateTime(date);
		return newRepository.save(news);

	}

	public List<NewsDto> getAllDtos() {

		java.util.Date date1 = new java.util.Date();
		List<New> newDtos = newRepository.findAll();

		return newDtos.stream().map(NewsDto::new).filter(date -> date.getUpdateTime().before(date1))
				.collect(Collectors.toList());

	}

	public NewsDto increatseViewCount(String title) {
		New newsByTitle = newRepository.findByTitle(title)
				.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		if (newsByTitle != null)
			newsByTitle.increaseViewCount();
		newRepository.save(newsByTitle);
		return new NewsDto(newsByTitle);

	}

	public NewOutput showNew(int page, int limit) {
		NewOutput output = new NewOutput();
		output.setPage(page);
		Pageable pageable = PageRequest.of(page - 1, limit);
		output.setListResult(findAll(pageable));
		output.setTotalPage((int) Math.ceil((double) (totalItem()) / limit));

		return output;
	}

	public int totalItem() {
		return (int) newRepository.count();
	}

//	public CategoryOutput showCategory()
//	{
//		List<Category> category = categoryRepository.findAll();
//		CategoryOutput output= new CategoryOutput();
//		
//	}

	private void checkIfNewAlreadyTaken(NewsDto newsDto) {
		Optional<New> newTitle = newRepository.findByTitle(newsDto.getTitle());
		newTitle.ifPresent(news -> {
			throw new CustomException("Title -" + newTitle + "đã tồn tại");
		});
	}

	public List<NewsResponse> getAllVideoByCategory(Long id) {
		List<New> news = newRepository.findByCategoryId(id);
		return news.stream().map(newMapper::entityToResponse).collect(Collectors.toList());

	}

	public NewsDto deleteNewByTitle(String title) {
		New news = newRepository.findByTitle(title).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

		news.setStatus(0);

		newRepository.save(news);
		return new NewsDto(news);

	}

	public NewsDto deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
