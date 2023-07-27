package org.kakao.kakaoshopping.web.dto.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.enums.OrderStatus;

/**
 * 기능 : 새로운 장바구니를 등록하기 위한 DTO
 * 작성자 - 장규민
 * 작성일 - 2023.07.25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCart {
    private Integer quantity;

    public Cart toEntity() {
        return Cart.builder()
                .orderStatus(OrderStatus.N)
                .quantity(quantity)
                .build();
    }
}
