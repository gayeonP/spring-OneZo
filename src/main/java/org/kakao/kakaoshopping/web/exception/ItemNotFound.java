package org.kakao.kakaoshopping.web.exception;

import org.springframework.http.HttpStatus;

/**
 * 기능: 상품에 대한 정보가 DB 에 없을 때 예외 처리
 * 작성자: 장규민
 * 작성일: 2023.07.24
 */
public class ItemNotFound extends KakaoException {

    public ItemNotFound(String message) {
        super(message);
    }

    public ItemNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
