package org.kakao.kakaoshopping.web.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFound extends KakaoException {

	public OrderNotFound(String message) {
		super(message);
	}

	public OrderNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
