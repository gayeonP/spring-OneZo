package org.kakao.kakaoshopping.domain.service.order;

import static java.util.Locale.*;

import org.kakao.kakaoshopping.domain.entity.order.Order;
import org.kakao.kakaoshopping.domain.repository.order.OrderRepository;
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

	public Long creatOrder(Order order, Long userId) {
		//		User user = userService.findUser(userId);
		//order.setUser(user);
		order.calculateTotalPrice();
		order.getOrderItems().forEach(orderItem -> {
			Long itemId = orderItem.getItem().getId();
			// todo
			// Item item = itemService.findItem(itemId);
			// orderItem.setItem(item);
			orderItem.setOrder(order);
			orderItemService.createOrderItem(orderItem);
		});

		return orderRepository.save(order).getId();
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

	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	private Order findById(Long id) {
		return orderRepository.findById(id)
			.orElseThrow(() -> new OrderNotFound(messageSource.getMessage("error.noBoard", null, getDefault())));
	}
}
