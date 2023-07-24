package org.kakao.kakaoshopping.web.dto.member.login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoggedInUser {
	private final Long userId;
}