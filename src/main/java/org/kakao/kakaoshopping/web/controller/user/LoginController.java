package org.kakao.kakaoshopping.web.controller.user;

import org.kakao.kakaoshopping.domain.service.user.UserService;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.kakao.kakaoshopping.web.dto.user.login.LoginUser;
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

	/**
	 * 기능 : 로그인
	 * 작성자 - 임정규
	 * 작성일 - 2023.07.25
	 * 수정자 - 임정규
	 * 수정일 - 2023.07.26
	 * @param user
	 * @param request
	 * @param rttr
	 * @return
	 */
	@PostMapping("/login")
	public String login(LoginUser user, HttpServletRequest request, RedirectAttributes rttr) {

		HttpSession session = request.getSession();

		LoggedInUser login = userService.login(user);
		if (login == null) {
			rttr.addFlashAttribute("loginFail",
				"아이디 혹은 비밀번호가 잘못되었습니다.");
			return "redirect:/login";
		}

		session.setAttribute("loggedInUser", login);
		return "redirect:/";
	}

	/**
	 * 기능 : 로그아웃
	 * 작성자 - 임정규
	 * 작성일 - 2023.07.25
	 * 수정자 - 장규민
	 * 수정일 - 2023.07.27
	 * @param session
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
