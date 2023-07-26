package org.kakao.kakaoshopping.web.common.paging.request;

import static java.lang.Math.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class InquirySearchCondition {
	private static final int MAX_SIZE = 2000;
	private final int size = 10;

	private int page;
	private String keyword;
	private Long userId;

	public InquirySearchCondition(int page, String keyword, Long userId) {
		this.page = page;
		this.keyword = keyword;
		this.userId = userId;
	}

	public Pageable getPageable() {
		return PageRequest.of(max(0, page), min(size, MAX_SIZE));
	}
}
