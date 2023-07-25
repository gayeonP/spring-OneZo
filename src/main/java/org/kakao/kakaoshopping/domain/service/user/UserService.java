package org.kakao.kakaoshopping.domain.service.user;

import static java.util.Locale.*;

import java.util.Optional;

import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.kakao.kakaoshopping.web.dto.user.login.LoginUser;
import org.kakao.kakaoshopping.web.exception.UserNotFound;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final MessageSource messageSource;

	public User findUser(Long userId) {
		return findById(userId);

	}

	private User findById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFound(messageSource.getMessage("error.noUser", null, getDefault())));
	}

	public LoggedInUser login(LoginUser loginUser) {

		String userId = loginUser.getUserId();
		String password = loginUser.getPassword();
		Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId));

		if (user.isPresent() && user.get().getPassword().equals(password)) {
			return LoggedInUser.builder()
				.userId(user.get().getId())
				.nickname(user.get().getNickname())
				.build();
		}

		return null;
	}
}
