package org.kakao.kakaoshopping.web.exception.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class FieldError {

	private String field;
	private String errorMessage;

	@Builder
	public FieldError(String field, String errorMessage) {
		this.field = field;
		this.errorMessage = errorMessage;
	}
}