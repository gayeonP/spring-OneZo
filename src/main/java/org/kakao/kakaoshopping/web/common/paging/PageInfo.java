package org.kakao.kakaoshopping.web.common.paging;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class PageInfo {

    private long pageNumber;
    private long pageSize;
    private long totalPage;
    private long totalSize;
    private boolean hasPrevious;
    private boolean hasNext;
    private long lastNumber;
    private long firstNumber;

    public static PageInfo of(Page page) {
        int totalPages = page.getTotalPages();
        int pageNumber = page.getPageable().getPageNumber();

        return PageInfo.builder()
                .pageNumber(page.getPageable().getPageNumber())
                .pageSize(page.getPageable().getPageSize())
                .totalPage(totalPages)
                .totalSize(page.getTotalElements())
                .hasPrevious((int)((pageNumber / 3) * 3 + 1) >= 3)
                .hasNext(totalPages > (pageNumber / 3) * 3 + 3)
                .firstNumber((int)((pageNumber / 3) * 3 + 1))
                .lastNumber(Math.min((pageNumber / 3) * 3 + 3, page.getTotalPages()))
                .build();
    }
}