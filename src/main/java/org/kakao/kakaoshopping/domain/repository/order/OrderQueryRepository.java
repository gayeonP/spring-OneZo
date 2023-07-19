package org.kakao.kakaoshopping.domain.repository.order;

import org.kakao.kakaoshopping.domain.entity.order.Order;
import org.kakao.kakaoshopping.web.common.paging.request.OrderSearchCondition;
import org.springframework.data.domain.Page;

public interface OrderQueryRepository {
	Page<Order> findOrders(OrderSearchCondition condition);
}
