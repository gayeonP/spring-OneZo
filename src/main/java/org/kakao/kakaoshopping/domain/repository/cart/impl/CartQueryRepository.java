package org.kakao.kakaoshopping.domain.repository.cart.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.cart.QCart;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.enums.OrderStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartQueryRepository {

    private final JPAQueryFactory queryFactory;

    public void deleteCart(User user) {
        BooleanBuilder builder = new BooleanBuilder();
        QCart cart = QCart.cart;
        builder.and(cart.user.id.eq(user.getId()))
                .and(cart.orderStatus.eq(OrderStatus.N));

        queryFactory.delete(cart)
                .where(builder)
                .execute();
    }
}
