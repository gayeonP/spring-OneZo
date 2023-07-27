package org.kakao.kakaoshopping.web.controller.user;

import java.util.Map;

import org.kakao.kakaoshopping.domain.service.user.UserService;
import org.kakao.kakaoshopping.web.dto.user.request.CreateUser;
import org.kakao.kakaoshopping.web.validator.user.CheckEmailValidator;
import org.kakao.kakaoshopping.web.validator.user.CheckNicknameValidator;
import org.kakao.kakaoshopping.web.validator.user.CheckUserLoginIdValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final CheckUserLoginIdValidator checkUserLoginIdValidator;
	private final CheckNicknameValidator checkNicknameValidator;
	private final CheckEmailValidator checkEmailValidator;

	@InitBinder
	public void validatorBinder(WebDataBinder binder) {
		binder.addValidators(checkUserLoginIdValidator);
		binder.addValidators(checkNicknameValidator);
		binder.addValidators(checkEmailValidator);
	}

	@GetMapping("/join")
	public String createUserForm() {
		return "user/join";
	}

	@GetMapping("/join/proc")
	public String createUserProcForm() {
		return "user/joinProc";
	}

	/**
	 * 기능 : 회원 가입
	 * 작성자 - 임정규
	 * 작성일 - 2023.07.25
	 * 수정자 - 임정규
	 * 수정일 - 2023.07.26
	 * @param createUser
	 * @param errors
	 * @param model
	 * @return
	 */
	@PostMapping(value = {"/join", "/join/proc"})
	public String createUser(@Valid CreateUser createUser, Errors errors, Model model) {

		if (errors.hasErrors()) {
			model.addAttribute("createUser", createUser);

			Map<String, String> validResult = userService.validateCreateUser(errors);
			validResult.forEach(
				(key, value) -> model.addAttribute(key, value));

			return "user/joinProc";
		}
		userService.createUser(createUser.toEntity());
		return "redirect:/joinSuccess";
	}

	@GetMapping("/joinSuccess")
	public String createSuccess() {
		return "user/joinSuccess";
	}
}
