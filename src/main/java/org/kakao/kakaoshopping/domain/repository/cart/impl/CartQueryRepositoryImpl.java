package org.kakao.kakaoshopping.domain.repository.cart.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.entity.cart.QCart;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.enums.OrderStatus;
import org.kakao.kakaoshopping.domain.repository.cart.CartQueryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartQueryRepositoryImpl implements CartQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteByUser(User user) {
        BooleanBuilder builder = new BooleanBuilder();

        QCart cart = QCart.cart;
        builder.and(cart.user.id.eq(user.getId()))
                .and(cart.orderStatus.eq(OrderStatus.N));

        queryFactory.delete(cart)
                .where(builder)
                .execute();
    }

    @Override
    public List<Cart> findAllByUser(User user) {
        BooleanBuilder builder = new BooleanBuilder();

        QCart cart = QCart.cart;
        builder.and(cart.user.id.eq(user.getId()))
                .and(cart.orderStatus.eq(OrderStatus.N));
        return queryFactory.selectFrom(cart)
                .where(builder)
                .stream().toList();
    }
}
