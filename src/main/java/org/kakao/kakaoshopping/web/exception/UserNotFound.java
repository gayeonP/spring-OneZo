package org.kakao.kakaoshopping.web.exception;

import org.springframework.http.HttpStatus;

public class UserNotFound extends KakaoException {

	public UserNotFound(String message) {
		super(message);
	}

	public UserNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
