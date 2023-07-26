package org.kakao.kakaoshopping.web.dto.user.login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoggedInUser {
	private final Long userId;
	private String nickname;
}