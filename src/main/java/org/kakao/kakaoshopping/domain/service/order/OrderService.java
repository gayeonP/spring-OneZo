package org.kakao.kakaoshopping.domain.service.order;

import static java.util.Locale.*;

import java.util.List;

import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.entity.order.Order;
import org.kakao.kakaoshopping.domain.entity.order.OrderItem;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.order.OrderRepository;
import org.kakao.kakaoshopping.domain.service.cart.CartService;
import org.kakao.kakaoshopping.domain.service.item.ItemService;
import org.kakao.kakaoshopping.domain.service.user.UserService;
import org.kakao.kakaoshopping.web.common.paging.request.OrderSearchCondition;
import org.kakao.kakaoshopping.web.exception.OrderNotFound;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MessageSource messageSource;
	private final OrderItemService orderItemService;
	private final UserService userService;
	private final ItemService itemService;
	private final CartService cartService;

	public Long creatOrder(Order order, Long userId) {
		// todo 재고가 0이면 반려해야 됨
		User user = userService.findUser(userId);
		order.setUser(user);
		order.calculateTotalPrice();

		List<OrderItem> orderItems = order.getOrderItems();
		order.setOrderItems(null);
		Order savedOrder = orderRepository.save(order);
		orderItems.forEach(orderItem -> {
			Long itemId = orderItem.getItem().getId();
			// todo
			Item item = itemService.getItemComplex(itemId);
			orderItem.setItem(item);
			orderItem.setOrder(savedOrder);
			orderItemService.createOrderItem(orderItem);
		});
		cartService.updateOrderStateCart(userId);

		return savedOrder.getId();
	}

	public Long creatOrderFromCart(Order order, Long userId, Long cardId) {
		// 장바구니 삭제해줌
		// cartService.deleteCart(cartId)


		return creatOrder(order, userId);
	}

	public Order findOrder(Long id) {
		return findById(id);
	}

	public Page<Order> findOrders(OrderSearchCondition condition) {
		return orderRepository.findOrders(condition);
	}

	public Long editOrder(Order order, Long id) {
		Order savedOrder = findById(id);
		savedOrder.edit(order);

		return savedOrder.getId();
	}

	/**
	 * 기능 : 상품을 삭제한다.
	 * 작성자 - 장원준
	 * 작성일 - 2023.07.20
	 * 수정자 - 임창준
	 * 수정일 - 2023.07.21
	 * @param id
	 */
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	private Order findById(Long id) {
		return orderRepository.findById(id)
			.orElseThrow(() -> new OrderNotFound(messageSource.getMessage("error.noBoard", null, getDefault())));
	}
}
