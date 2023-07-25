package org.kakao.kakaoshopping.domain.repository.inquiry.impl;

import static org.kakao.kakaoshopping.domain.entity.inquiry.QInquiry.*;

import java.util.List;

import org.kakao.kakaoshopping.domain.entity.inquiry.Inquiry;
import org.kakao.kakaoshopping.domain.repository.inquiry.InquiryQueryRepository;
import org.kakao.kakaoshopping.web.common.paging.request.InquirySearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InquiryQueryRepositoryImpl implements InquiryQueryRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Inquiry> findInquiries(InquirySearchCondition condition) {

		List<Inquiry> inquiries = queryFactory
			.selectFrom(inquiry)
			.where(
				userEq(condition.getUserId())
			)
			.offset(condition.getPageable().getOffset())
			.limit(condition.getPageable().getPageSize())
			.fetch();

		// 전체 항목 수를 가져오는 쿼리를 실행하여 total 변수에 할당합니다.
		long total = queryFactory
			.selectFrom(inquiry)
			.where(
				userEq(condition.getUserId())
			)
			.offset(condition.getPageable().getOffset())
			.limit(condition.getPageable().getPageSize())
			.fetchCount();

		return new PageImpl<>(inquiries, condition.getPageable(), total);
	}

	private BooleanBuilder userEq(Long userId) {
		// todo 추후 Member 엔티티를 매핑하도록 수정해야 함
		return null;
		//return nullSafeBuilder(() -> order.memberId.eq(memberId));
	}
}
