package org.kakao.kakaoshopping.web.interceptor;

import java.io.IOException;

import org.kakao.kakaoshopping.domain.enums.UserType;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		IOException {
		LoggedInUser user = getLoggedInMemberFromSession(request);
		if (user == null) {
			response.sendRedirect("/login");
		}else if(request.getRequestURI().startsWith("/seller")
				&& user.getUserType() != UserType.SELLER){
			response.sendRedirect("/");
		}
		return true;
	}

	private LoggedInUser getLoggedInMemberFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		LoggedInUser loggedInUser = (LoggedInUser)session.getAttribute("loggedInUser");

		return loggedInUser;
	}

}
