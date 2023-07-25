package org.kakao.kakaoshopping.web.interceptor;

import java.io.IOException;

<<<<<<< HEAD
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.springframework.web.servlet.HandlerInterceptor;
=======
import org.kakao.kakaoshopping.web.dto.member.login.LoggedInMember;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
>>>>>>> origin/feature/login

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		IOException {

<<<<<<< HEAD
		if (getLoggedInMemberFromSession(request) == null) {
			response.sendRedirect("/login");
=======
		HttpSession session = request.getSession();

		if (session.getAttribute("loggedInMember") != null) {
			response.sendRedirect("/index");
>>>>>>> origin/feature/login
		}

		return true;
	}

<<<<<<< HEAD
	private LoggedInUser getLoggedInMemberFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		LoggedInUser loggedInUser = (LoggedInUser)session.getAttribute("loggedInUser");

		return loggedInUser;
=======
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) {

		modelAndView.addObject("loggedInMember", getLoggedInMemberFromSession(request));
	}

	private LoggedInMember getLoggedInMemberFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		LoggedInMember loggedInMember = (LoggedInMember)session.getAttribute("loggedInMember");

		return loggedInMember;
>>>>>>> origin/feature/login
	}
}
