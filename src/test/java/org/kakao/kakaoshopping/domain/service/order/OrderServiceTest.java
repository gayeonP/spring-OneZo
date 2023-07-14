package org.kakao.kakaoshopping.domain.service.order;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.util.ReflectionTestUtils.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kakao.kakaoshopping.domain.entity.Order;
import org.kakao.kakaoshopping.domain.repository.order.OrderRepository;
import org.kakao.kakaoshopping.web.dto.order.request.CreateOrder;
import org.kakao.kakaoshopping.web.dto.order.request.CreateOrderItem;
import org.kakao.kakaoshopping.web.exception.OrderNotFound;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;

class OrderServiceTest {

	// 서비스의 단위 테스트는 스프링 컨테이너 의존 없이 가능
	// mock 인스턴스를 활용하여 더 빠른 테스트를 할 수 있다.
	private OrderRepository orderRepository = Mockito.mock(OrderRepository.class);

	private MessageSource messageSource = Mockito.mock(MessageSource.class);

	private OrderItemService orderItemService = Mockito.mock(OrderItemService.class);

	private OrderService orderService;

	private CreateOrder createOrder;

	@BeforeEach
	void init() {
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
			.memberId(1L)
			.orderItems(orderItems)
			.build();

		orderService = new OrderService(orderRepository, messageSource, orderItemService);
	}

	@Test
	@DisplayName("주문 등록 성공")
	void createOrderSuccessTest() {
		// given
		Order order = createOrder.toEntity();
		Mockito.when(orderRepository.save(order))
			.thenAnswer(invocation -> {
				setField(order, "id", 1L);
				return order;
			});

		// when
		Long savedOrderId = orderService.creatOrder(order);

		// then
		assertThat(savedOrderId).isNotNull();
		assertThat(savedOrderId).isEqualTo(order.getId());
	}

	@Test
	@DisplayName("주문 1개 조회 성공")
	void findOrderSuccessTest() {
		// given
		Long orderId = 1L;
		Order order = createOrder.toEntity();

		Mockito.when(orderRepository.findById(orderId))
			.thenReturn(Optional.of(order));

		// when
		Order savedOrder = orderService.findOrder(orderId);

		// then
		assertThat(savedOrder).isNotNull();
		assertThat(savedOrder.getId()).isEqualTo(order.getId());
		assertThat(savedOrder.getOrderItems().size()).isEqualTo(2);
	}

	@Test
	@DisplayName("주문 1개 조회 실패")
	void findOrderFailTest() {
		// given
		Long orderId = 100L;

		Mockito.when(orderRepository.findById(orderId))
			.thenReturn(Optional.empty());

		// expected
		assertThatThrownBy(() -> {
			// 에러가 발생할 것 같은 로직을 람다 바디에 작성
			orderService.findOrder(orderId);
		}).isInstanceOf(OrderNotFound.class);
	}
}