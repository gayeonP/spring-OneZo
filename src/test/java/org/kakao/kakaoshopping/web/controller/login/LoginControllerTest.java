package org.kakao.kakaoshopping.web.controller.login;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kakao.kakaoshopping.domain.entity.embedded.PhoneNumber;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.enums.UserType;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.kakao.kakaoshopping.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@BeforeEach
	void createUserRepositoryBeforeTest() {
		User testUser = User.builder()
			.userLoginId("user1")
			.password("abcdefg")
			.name("테스트")
			.email("ljk@gg.net")
			.nickname("나는")
			.phoneNumber(PhoneNumber.builder()
				.headNumber("010")
				.middleNumber("2222")
				.tailNumber("3333").build())
			.userType(UserType.USER)
			.build();

		userRepository.save(testUser);

	}

	@AfterEach
	void clearUserRepositoryBeforeTest() {
		userRepository.deleteAll();
	}

	@Test
	@DisplayName("로그인 성공")
	void loginSuccessTest() throws Exception {

		mockMvc.perform(post("/login")
				.param("userId", "user1")
				.param("password", "abcdefg")
				.contentType(APPLICATION_FORM_URLENCODED))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"))
			.andDo(print());

	}

	@Test
	@DisplayName("로그인 실패")
	void loginFailTest() throws Exception {

		mockMvc.perform(post("/login")
				.param("userId", "user1")
				.param("password", "abcg")
				.contentType(APPLICATION_FORM_URLENCODED))
			.andExpect(status().is3xxRedirection())
			.andExpect(flash().attributeExists("loginFail"))
			.andExpect(view().name("redirect:/login"))
			.andDo(print());

	}

	@Test
	@DisplayName("회원가입 성공")
	void joinTest() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("userId", "user2");
		params.add("password", "abcde");
		params.add("name", "김");
		params.add("email", "kim@na.net");
		params.add("nickname", "김김");
		params.add("headNumber", "010");
		params.add("middleNumber", "2421");
		params.add("tailNumber", "5223");
		params.add("userType", "USER");

		mockMvc.perform(post("/join")
				.params(params)
				.contentType(APPLICATION_FORM_URLENCODED))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"));

	}
}
