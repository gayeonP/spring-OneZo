package org.kakao.kakaoshopping.domain.repository.inquiry;

import org.kakao.kakaoshopping.domain.entity.inquiry.Inquiry;
import org.kakao.kakaoshopping.web.common.paging.request.InquirySearchCondition;
import org.springframework.data.domain.Page;

public interface InquiryQueryRepository {
	Page<Inquiry> findInquiries(InquirySearchCondition condition);
}
