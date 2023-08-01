package org.kakao.kakaoshopping.web.dto.user.login;

import org.kakao.kakaoshopping.domain.enums.UserType;

import lombok.Data;

@Data
public class LoginUser {
	private String userLoginId;
	private String password;
	private UserType userType;
}
