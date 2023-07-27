package org.kakao.kakaoshopping.web.validator.user;

import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.kakao.kakaoshopping.web.dto.user.request.CreateUser;
import org.kakao.kakaoshopping.web.validator.AbstractValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckNicknameValidator extends AbstractValidator<CreateUser> {

	private final UserRepository userRepository;

	/**
	 * 기능 : 닉네임 중복 검증
	 * 작성자 - 임정규
	 * 작성일 - 2023.07.26
	 * @param dto
	 * @param errors
	 */
	@Override
	protected void doValidate(CreateUser dto, Errors errors) {
		if (userRepository.existsByNickname(dto.getNickname())) {
			errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임입니다.");
		}
	}
}
