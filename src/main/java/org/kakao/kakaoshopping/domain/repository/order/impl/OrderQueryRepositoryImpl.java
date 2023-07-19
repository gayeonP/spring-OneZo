package org.kakao.kakaoshopping.domain.repository.order.impl;

import static org.kakao.kakaoshopping.domain.entity.order.QOrder.*;
import static org.kakao.kakaoshopping.domain.entity.order.QOrderItem.*;

import java.util.List;

import org.kakao.kakaoshopping.domain.entity.order.Order;
import org.kakao.kakaoshopping.domain.repository.order.OrderQueryRepository;
import org.kakao.kakaoshopping.web.common.paging.request.OrderSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Order> findOrders(OrderSearchCondition condition) {
		List<Order> boards = queryFactory
			.selectFrom(order)
			.leftJoin(order.orderItems, orderItem)
			.fetchJoin()
			.where(
				memberEq(condition.getMemberId())
			)
			.offset(condition.getPageable().getOffset())
			.limit(condition.getPageable().getPageSize())
			.fetch();

		long totals = queryFactory
			.selectFrom(order)
			.leftJoin(order.orderItems, orderItem)
			.fetchJoin()
			.where(
				memberEq(condition.getMemberId())
			)
			.offset(condition.getPageable().getOffset())
			.limit(condition.getPageable().getPageSize())
			.fetch()
			.size();

		return new PageImpl<>(boards, condition.getPageable(), totals);
	}

	private BooleanBuilder memberEq(Long memberId) {
		// todo 추후 Member 엔티티를 매핑하도록 수정해야 함
		return null;
		//return nullSafeBuilder(() -> order.memberId.eq(memberId));
	}
}
