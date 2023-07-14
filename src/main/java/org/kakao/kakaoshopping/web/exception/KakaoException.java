package org.kakao.kakaoshopping.web.exception;

import org.springframework.http.HttpStatus;

public abstract class KakaoException extends RuntimeException {

	public KakaoException(String message) {
		super(message);
	}

	public KakaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public abstract HttpStatus getHttpStatus();

}
