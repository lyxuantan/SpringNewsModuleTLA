package com.dto;

import com.constant.ErrorCode;
import com.exeption.CustomException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
	private Object data;
	private String errorCode = "0";
	private String errorDesc = "Thành công";

	public ApiResponse(CustomException e) {
		this.errorCode = e.getCode();
		this.errorDesc = e.getDesc();
	}
	public ApiResponse(Object data) {
		this.data = data;
	}
	public ApiResponse(ErrorCode data) {
		this.errorCode = data.getCode();
		this.errorDesc = data.getDesc();
	}

}
