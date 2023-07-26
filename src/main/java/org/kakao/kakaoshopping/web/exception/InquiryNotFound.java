package org.kakao.kakaoshopping.web.exception;

import org.springframework.http.HttpStatus;

public class InquiryNotFound extends KakaoException {

	public InquiryNotFound(String message) {
		super(message);
	}

	public InquiryNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return null;
	}
}
