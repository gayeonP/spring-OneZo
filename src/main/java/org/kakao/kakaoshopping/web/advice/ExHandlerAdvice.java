package org.kakao.kakaoshopping.web.advice;

import org.kakao.kakaoshopping.web.exception.KakaoException;
import org.kakao.kakaoshopping.web.exception.dto.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = "org.kakao.kakaoshopping.web")
public class ExHandlerAdvice {

	@ExceptionHandler
	public ModelAndView commonExHandler(KakaoException ex) {
		ModelAndView mav = new ModelAndView();
		HttpStatus status = ex.getHttpStatus();
		String viewName = parseViewFrom(status);

		ErrorResult errorResult = ErrorResult.builder()
			.errorCode(ex.getHttpStatus())
			.errorMessage(ex.getMessage())
			.exception(ex)
			.build();

		mav.addObject("errorResult", errorResult);
		mav.setViewName(viewName);
		mav.setStatus(status);

		return mav;
	}

	private String parseViewFrom(HttpStatus status) {
		String statusCode = String.valueOf(status.value());
		return "error/" + statusCode.substring(0, 1) + "XXPage";
	}
}
