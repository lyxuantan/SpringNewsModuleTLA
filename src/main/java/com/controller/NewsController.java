package com.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.constant.ErrorCode;
import com.data.RObject;
import com.dto.ApiResponse;
import com.dto.NewOutput;
import com.dto.NewsDto;

import com.dto.NewsResponse;
import com.exeption.CustomException;
import com.model.New;
import com.service.NewsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {

	@Autowired
	private NewsService newService;

	@GetMapping(value = "/find-title")
	public ResponseEntity<ApiResponse> findNewsByTitle(@RequestParam(value = "title") String title) {
		ApiResponse response;
		try {
			response = new ApiResponse(newService.increatseViewCount(title));
		} catch (CustomException e) {
			response = new ApiResponse(e);
		} catch (Exception e) {
			response = new ApiResponse(ErrorCode.API_FAIL_UNKNOW);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/find-title-2")
	public ApiResponse findNewsByTitle2(@RequestParam(value = "title") String title) {
		ApiResponse response;
		try {
			response = new ApiResponse(newService.increatseViewCount(title));

		} catch (CustomException e) {
			response = new ApiResponse(e);
		} catch (Exception e) {
			response = new ApiResponse(ErrorCode.API_FAIL_UNKNOW);
		}
		return response;
	}

	@PostMapping(value = "/add")
	public NewsResponse save(@RequestBody NewsResponse newResponse) {
		return newService.save(newResponse);

	}

	@GetMapping(value = "/find")
	public ResponseEntity<?> find() {
		List<NewsDto> newDto = newService.getAllDtos();
		return ResponseEntity.ok(new RObject(400, "success").response(newDto));
	}

	@GetMapping(value = "/find-by-category")
	public List<NewsResponse> findByCategory(@RequestParam("id") Long id) {
		return newService.getAllVideoByCategory(id);
	}

	@GetMapping(value = "/new")
	public NewOutput showNewOutput(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		return newService.showNew(page, limit);
	}

	@PostMapping(value = "/addnow")
	public New saveNews(@RequestBody NewsResponse newsResponse)

	{
		return newService.saveNow(newsResponse);
	}

	@GetMapping(value = "/new-by-date")
	public NewOutput showNewOutputByDate(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		return newService.showNewOutputByDate(page, limit);
	}

	@GetMapping(value = "/new-by-view")
	public ApiResponse showNewOutputByView(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		try {
			PageRequest pageable = PageRequest.of(page, limit, Direction.DESC, "id");
			Page<NewsResponse> output = newService.findByPaging(pageable);
			return new ApiResponse(output);
		} catch (CustomException e) {
			return new ApiResponse(e);
		} catch (Exception e) {
			return new ApiResponse(ErrorCode.API_FAIL_UNKNOW);
		}

	}

	@DeleteMapping(value = "/title")
	public NewsDto delete(@RequestParam("title") String title) {
		return newService.deleteNewByTitle(title);
	}

	@DeleteMapping(value = "/id/{id}")
	public NewsDto deleteById(@PathVariable("id") int id) {
		return newService.deleteById(id);
	}

}
