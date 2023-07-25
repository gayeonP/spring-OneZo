package org.kakao.kakaoshopping.web.dto.member.login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoggedInMember {
	// todo 추후 회원이 어떤 정보로 로그인 할건지 정하고 수정해야 함
	private final Long memberId;
}