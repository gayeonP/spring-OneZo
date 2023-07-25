package org.kakao.kakaoshopping.web.dto.member.login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoggedInMember {
	private final Long id;
	private String userId;
	private String password;
}