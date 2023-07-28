package org.kakao.kakaoshopping.web.controller.order;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kakao.kakaoshopping.domain.service.order.OrderService;
import org.kakao.kakaoshopping.web.dto.order.request.CreateOrder;
import org.kakao.kakaoshopping.web.dto.order.request.CreateOrderItem;
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
@ActiveProfiles("test") // test 시에는 test profile을 사용할 수 있도록 설정
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private OrderService orderService;

	private CreateOrder createOrder;

	@Test
	@DisplayName("주문 등록 성공")
	void createOrderSuccessTest() throws Exception {
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("memberId", "1");
		params.add("orderItems[0].itemId", "1");
		params.add("orderItems[0].quantity", "10");
		params.add("orderItems[1].itemId", "2");
		params.add("orderItems[1].quantity", "200");

		// expected
		mockMvc.perform(post("/order/create")
				.params(params)
				.contentType(APPLICATION_FORM_URLENCODED))
			.andExpect(status().is3xxRedirection())
			.andExpect(flash().attributeExists("orderId"))
			.andDo(print());
	}

	@Test
	@DisplayName("주문 1개 조회 성공")
	void findOrderSuccessTest() throws Exception {
		// given
		saveOrder();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("orderId", "1");

		// expected
		mockMvc.perform(get("/order")
				.params(params)
				.contentType(APPLICATION_FORM_URLENCODED))
			.andExpect(status().isOk())
			.andExpect(view().name("orderView"))
			.andExpect(model().attributeExists("order"))
			.andDo(print());
	}

	@Test
	@DisplayName("주문 1개 조회 실패")
	void findOrderFailTest() throws Exception {
		// given
		saveOrder();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("orderId", "1000000");

		// expected
		mockMvc.perform(get("/order")
				.params(params)
				.contentType(APPLICATION_FORM_URLENCODED))
			.andExpect(status().isBadRequest())
			.andExpect(view().name("error/4XXPage"))
			.andExpect(model().attributeExists("errorResult"))
			.andDo(print());
	}

	@Test
	@DisplayName("주문 여러 개 조회 성공")
	void findOrdersSuccessTest() throws Exception {
		// given
		saveOrder();
		saveOrder();

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("memberId", "1");

		// expected
		mockMvc.perform(get("/orders")
				.params(params)
				.contentType(APPLICATION_FORM_URLENCODED))
			.andExpect(status().isOk())
			.andExpect(view().name("orderViews"))
			.andExpect(model().attributeExists("orders"))
			.andDo(print());
	}

	@Test
	@DisplayName("주문 1개 수정 성공")
	void editOrderSuccessTest() throws Exception {
		// given
		saveOrder();

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("id", "1");
		params.add("orderItems[0].itemId", "100");
		params.add("orderItems[0].quantity", "3400");
		params.add("orderItems[1].itemId", "1000");
		params.add("orderItems[1].quantity", "5000");

		// expected
		mockMvc.perform(post("/order/edit")
				.params(params)
				.contentType(APPLICATION_FORM_URLENCODED))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/order/orders"))
			.andDo(print());
	}

	@Test
	@DisplayName("주문 1개 삭제 성공")
	void deleteOrderSuccessTest() throws Exception {
		// given
		saveOrder();

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("orderId", "1");

		// expected
		mockMvc.perform(get("/order/delete")
				.params(params)
				.contentType(APPLICATION_FORM_URLENCODED))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/order/orders"))
			.andDo(print());
	}

	private Long saveOrder() {
		CreateOrderItem item1 = CreateOrderItem.builder()
			.itemId(1L)
			.quantity(10)
			.build();

		CreateOrderItem item2 = CreateOrderItem.builder()
			.itemId(2L)
			.quantity(50)
			.build();

		List orderItems = List.of(item1, item2);

		createOrder = CreateOrder.builder()
			.build();

		return orderService.creatOrder(createOrder.toEntity(), orderItems, 1L);
	}

}