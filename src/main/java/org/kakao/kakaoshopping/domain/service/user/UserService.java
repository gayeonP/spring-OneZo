package org.kakao.kakaoshopping.domain.service.user;

<<<<<<< HEAD
import static java.util.Locale.*;

=======
>>>>>>> origin/feature/login
import java.util.Optional;

import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
<<<<<<< HEAD
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.kakao.kakaoshopping.web.dto.user.login.LoginUser;
import org.kakao.kakaoshopping.web.exception.UserNotFound;
import org.springframework.context.MessageSource;
=======
import org.kakao.kakaoshopping.web.dto.member.login.LoggedInMember;
>>>>>>> origin/feature/login
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
<<<<<<< HEAD
	private final MessageSource messageSource;

	public User findUser(Long userId) {
		return findById(userId);

	}

	private User findById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFound(messageSource.getMessage("error.noUser", null, getDefault())));
	}

	public LoggedInUser login(LoginUser loginUser) {
=======

	public LoggedInMember login(LoggedInMember loginUser) {
>>>>>>> origin/feature/login
		String userId = loginUser.getUserId();
		String password = loginUser.getPassword();
		Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId));

		if (user.isPresent() && user.get().getPassword().equals(password)) {
<<<<<<< HEAD
			return LoggedInUser.builder()
				.userId(user.get().getId())
				.nickname(user.get().getNickname())
=======
			return LoggedInMember.builder()
				.id(user.get().getId())
				.userId(userId)
>>>>>>> origin/feature/login
				.build();
		}

		return null;
	}
}
