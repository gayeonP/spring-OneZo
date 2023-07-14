package org.kakao.kakaoshopping.domain.service.order;

import org.kakao.kakaoshopping.domain.entity.OrderItem;
import org.kakao.kakaoshopping.domain.repository.order.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemService {

	private final OrderItemRepository orderItemRepository;

	public void createOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}
}
