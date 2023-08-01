package org.kakao.kakaoshopping.web.exception;

import org.springframework.http.HttpStatus;

public class CartNotFound extends KakaoException {

    public CartNotFound(String message) {
        super(message);
    }

    public CartNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
