package com.constant;

public enum ErrorCode {
	API_FAIL_UNKNOW("ERR001", "Lỗi hệ thống"),
	NOT_FOUND("ERR002", "Không tìm thấy thông tin");

	private ErrorCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
