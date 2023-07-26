package org.kakao.kakaoshopping.web.controller.user;

import org.kakao.kakaoshopping.domain.service.user.UserService;
import org.kakao.kakaoshopping.web.annotaion.LoginUser;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.kakao.kakaoshopping.web.dto.user.request.CreateUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/join")
	public String joinForm() {
		return "user/join";
	}

	@PostMapping("/join")
	public String join(CreateUser createUser) {

		userService.createUser(createUser.toEntity());

		return "redirect:/";
	}

	@GetMapping("/user/mypage")
	public String myPage(@LoginUser LoggedInUser loginUser) {

		return null;
	}
}
