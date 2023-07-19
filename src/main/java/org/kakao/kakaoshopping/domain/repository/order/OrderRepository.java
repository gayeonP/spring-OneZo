package org.kakao.kakaoshopping.domain.repository.order;

import org.kakao.kakaoshopping.domain.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderQueryRepository {
}
