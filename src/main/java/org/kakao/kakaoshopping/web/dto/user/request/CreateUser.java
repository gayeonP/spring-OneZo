package org.kakao.kakaoshopping.web.dto.user.request;

import org.kakao.kakaoshopping.domain.entity.embedded.PhoneNumber;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {

	private String userId;
	private String password;
	private String name;
	private String nickname;
	private String email;
	private UserType userType;
	private String headNumber;
	private String middleNumber;
	private String tailNumber;

	public User toEntity() {
		return User.builder()
			.userId(userId)
			.password(password)
			.name(name)
			.nickname(nickname)
			.email(email)
			.userType(userType)
			.phoneNumber(parsePhoneNumber())
			.build();
	}

	private PhoneNumber parsePhoneNumber() {
		return PhoneNumber.builder()
			.headNumber(headNumber)
			.middleNumber(middleNumber)
			.tailNumber(tailNumber)
			.build();
	}

}
