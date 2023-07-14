package org.kakao.kakaoshopping.domain.repository.order.impl;

import org.kakao.kakaoshopping.domain.repository.order.OrderItemQueryRepository;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderItemQueryRepositoryImpl implements OrderItemQueryRepository {

	private final JPAQueryFactory queryFactory;
}
