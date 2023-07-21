package org.kakao.kakaoshopping.web.controller.item;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kakao.kakaoshopping.web.dto.item.request.CreateItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {
	@Autowired
	MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	ObjectMapper mapper;

	@Test
	@DisplayName("상품 등록 테스트")
	void createItemTest() throws Exception {
		CreateItem dto = CreateItem.builder()
			.name("name")
			.price(10000)
			.stock(20)
			.itemDetail("itemDetail").build();

		mvc.perform(post("/seller/createItem")
				.content(mapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().is3xxRedirection())
			.andDo(print())
			.andReturn().toString().equals("page");
	}
}