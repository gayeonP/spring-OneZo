package org.kakao.kakaoshopping.web.interceptor;

import java.io.IOException;

import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * 기능 : 로그아웃 상태 유저들의 권한 없는 페이지 접근 방지
	 * 작성자 - 임정규
	 * 작성일 - 2023.07.25
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		IOException {

		if (getLoggedInMemberFromSession(request) == null) {
			response.sendRedirect("/login");
		}

		return true;
	}

	/**
	 * 기능 : 세션에서 로그인 상태를 가져온다.
	 * 작성자 - 임정규
	 * 작성일 - 2023.07.25
	 */
	private LoggedInUser getLoggedInMemberFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		LoggedInUser loggedInUser = (LoggedInUser)session.getAttribute("loggedInUser");

		return loggedInUser;
	}

}
