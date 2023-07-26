package org.kakao.kakaoshopping.domain.repository.inquiry;

import org.kakao.kakaoshopping.domain.entity.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>, InquiryQueryRepository {
}
