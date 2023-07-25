package org.kakao.kakaoshopping.web.controller.user;

import org.kakao.kakaoshopping.domain.service.user.UserService;
import org.kakao.kakaoshopping.web.annotaion.LoginMember;
import org.kakao.kakaoshopping.web.dto.member.login.LoggedInMember;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;

	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}

	@PostMapping("/login")
	public String login(@LoginMember LoggedInMember user, HttpServletRequest request, RedirectAttributes rttr) {
		HttpSession session = request.getSession();

		LoggedInMember login = userService.login(user);
		if (login == null) {
			rttr.addFlashAttribute("loginFail",
				"아이디 혹은 비밀번호가 잘못되었습니다.");
			return "redirect:/login";
		}

		session.setAttribute("loggedInMember", login);
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect:/";
	}
}
