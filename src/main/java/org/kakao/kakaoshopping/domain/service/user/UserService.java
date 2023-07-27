package org.kakao.kakaoshopping.domain.service.user;

import static java.util.Locale.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.kakao.kakaoshopping.web.dto.user.login.LoginUser;
import org.kakao.kakaoshopping.web.exception.UserNotFound;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final MessageSource messageSource;

	public User findUser(Long userId) {
		return findById(userId);

	}

	public User findUserByUserLoginId(String userLoginId) {
		return userRepository.findByUserId(userLoginId);
	}

	private User findById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFound(messageSource.getMessage("error.noUser", null, getDefault())));
	}

	/**
	 * 기능 : 로그인 ID, PW 검증
	 * 작성자 - 임정규
	 * 작성일 - 2023.07.25
	 * 수정자 - 임정규
	 * 수정일 - 2023.07.26
	 * @param loginUser
	 */
	@Transactional(readOnly = true)
	public LoggedInUser login(LoginUser loginUser) {

		String userLoginId = loginUser.getUserLoginId();
		String password = loginUser.getPassword();
		Optional<User> user = Optional.ofNullable(findUserByUserLoginId(userLoginId));

		if (user.isPresent() && user.get().getPassword().equals(password)) {
			return LoggedInUser.builder()
					.userType(user.get().getUserType())
				.userId(user.get().getId())
				.nickname(user.get().getNickname())
				.build();
		}

		return null;
	}

	/**
	 * 기능 : 회원가입
	 * 작성자 - 임정규
	 * 작성일 - 2023.07.25
	 * @param user
	 */
	public Long createUser(User user) {
		return userRepository.save(user).getId();
	}

	/**
	 * 기능 : 회원가입 유효성 검사
	 * 작성자 - 임정규
	 * 작성일 - 2023.07.26
	 * @param errors
	 */
	@Transactional(readOnly = true)
	public Map<String, String> validateCreateUser(Errors errors) {
		Map<String, String> validResult = new HashMap<>();

		errors.getFieldErrors().forEach(
			(error) -> validResult.put(String.format("valid_%s", error.getField()), error.getDefaultMessage()));

		return validResult;
	}

}
