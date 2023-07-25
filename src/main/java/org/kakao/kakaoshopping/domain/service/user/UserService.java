package org.kakao.kakaoshopping.domain.service.user;

<<<<<<< HEAD
import static java.util.Locale.*;

import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.kakao.kakaoshopping.web.exception.UserNotFound;
import org.springframework.context.MessageSource;
import java.util.Optional;

import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.kakao.kakaoshopping.web.dto.member.login.LoggedInMember;
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

	public LoggedInMember login(LoggedInMember loginUser) {
		String userId = loginUser.getUserId();
		String password = loginUser.getPassword();
		Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId));

		if (user.isPresent() && user.get().getPassword().equals(password)) {
			return LoggedInMember.builder()
				.id(user.get().getId())
				.userId(userId)
				.build();
		}

		return null;
	}
}
