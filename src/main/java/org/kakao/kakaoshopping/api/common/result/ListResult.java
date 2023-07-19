package org.kakao.kakaoshopping.api.common.result;

import static org.kakao.kakaoshopping.web.common.paging.PageInfo.*;

import java.util.List;

import org.kakao.kakaoshopping.web.common.paging.PageInfo;
import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Data;

@Data
public class ListResult<T> {

	private final List<T> list;
	private final int size;
	private final PageInfo pageInfo;

	@Builder
	public ListResult(List<T> list, Page<?> page) {
		this.list = list;
		this.size = list.size();
		this.pageInfo = page == null ? null : of(page);
	}
}
