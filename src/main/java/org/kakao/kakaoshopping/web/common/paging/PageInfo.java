package org.kakao.kakaoshopping.web.common.paging;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageInfo {

	private long pageNumber;
	private long pageSize;
	private long totalPage;
	private long totalSize;

	public static PageInfo of(Page page) {
		return PageInfo.builder()
			.pageNumber(page.getPageable().getPageNumber())
			.pageSize(page.getPageable().getPageSize())
			.totalPage(page.getTotalPages())
			.totalSize(page.getTotalElements())
			.build();
	}
}