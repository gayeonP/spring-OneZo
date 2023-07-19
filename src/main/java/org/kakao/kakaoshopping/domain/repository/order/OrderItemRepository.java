package org.kakao.kakaoshopping.domain.repository.order;

import org.kakao.kakaoshopping.domain.entity.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, OrderItemQueryRepository {
}
