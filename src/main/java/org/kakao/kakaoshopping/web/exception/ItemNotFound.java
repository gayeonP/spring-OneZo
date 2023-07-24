package org.kakao.kakaoshopping.web.exception;

import org.springframework.http.HttpStatus;

public class ItemNotFound extends KakaoException{

	public ItemNotFound(String message){
		super(message);
	}

	public ItemNotFound(String message, Throwable cause){
		super(message, cause);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
