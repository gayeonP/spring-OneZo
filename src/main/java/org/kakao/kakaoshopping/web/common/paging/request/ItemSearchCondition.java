package org.kakao.kakaoshopping.web.common.paging.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSearchCondition {
    private String name;
    private BigDecimal price;
    private String keyWord;

    public Pageable getPageable(int pageNum, int size) {
        return PageRequest.of(pageNum, size, Sort.by("id").descending());
    }
}