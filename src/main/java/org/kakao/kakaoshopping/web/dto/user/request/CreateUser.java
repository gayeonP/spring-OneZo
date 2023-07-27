package org.kakao.kakaoshopping.web.dto.user.request;

import org.kakao.kakaoshopping.domain.entity.embedded.PhoneNumber;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.enums.UserType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {

	@NotBlank(message = "아이디를 입력해주세요.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-z]).{6,20}$", message = "아이디는 영문과 숫자를 포함한 6~20자리여야 합니다.")
	private String userLoginId;

	@NotBlank(message = "패스워드를 입력해주세요.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 공백을 제외하고 영문, 숫자, 특수문자를 포함한 8~16자리여야 합니다.")
	private String password;

	@NotBlank(message = "이름을 입력해주세요.")
	@Pattern(regexp = "^[가-힣]{2,10}$", message = "잘못된 이름입니다.")
	private String name;

	@NotBlank(message = "닉네임을 입력해주세요.")
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
	private String nickname;

	@NotBlank(message = "이메일을 입력해주세요.")
	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "잘못된 이메일 형식입니다.")
	private String email;

	@NotBlank(message = "전화번호를 입력해주세요.")
	@Positive
	@Pattern(regexp = "^[0-9]{2,3}$", message = "잘못된 전화번호 형식입니다.")
	private String headNumber;
	@NotBlank(message = "전화번호를 입력해주세요.")
	@Positive
	@Pattern(regexp = "^[0-9]{3,4}$", message = "잘못된 전화번호 형식입니다.")
	private String middleNumber;
	@NotBlank(message = "전화번호를 입력해주세요.")
	@Positive
	@Pattern(regexp = "^[0-9]{3,4}$", message = "잘못된 전화번호 형식입니다.")
	private String tailNumber;

	private UserType userType;

	public User toEntity() {
		return User.builder()
			.userLoginId(userLoginId)
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
