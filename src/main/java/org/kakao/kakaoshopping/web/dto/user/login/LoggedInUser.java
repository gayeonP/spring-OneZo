package org.kakao.kakaoshopping.web.dto.user.login;

import lombok.Builder;
import lombok.Data;
import org.kakao.kakaoshopping.domain.enums.UserType;

@Data
@Builder
public class LoggedInUser {
	private final Long userId;
	private String nickname;
	private UserType userType;
}