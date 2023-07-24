package org.kakao.kakaoshopping.domain.service.user;

import static java.util.Locale.*;

import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
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

}
