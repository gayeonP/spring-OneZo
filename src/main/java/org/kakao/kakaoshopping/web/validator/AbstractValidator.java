package org.kakao.kakaoshopping.web.validator;

import org.kakao.kakaoshopping.web.exception.KakaoException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @param <T>
 */
public abstract class AbstractValidator<T> implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validate(Object target, Errors errors) {
		try {
			doValidate((T)target, errors);
		} catch (KakaoException e) {
			throw e;
		}
	}

	protected abstract void doValidate(final T dto, final Errors errors);

}
