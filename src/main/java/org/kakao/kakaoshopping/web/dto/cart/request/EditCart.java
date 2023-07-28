package org.kakao.kakaoshopping.web.dto.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.cart.Cart;

/**
 * 기능 : 수정한 장바구니 아이템 수량을 담기 위한 DTO
 * 작성자 - 장규민
 * 작성일 - 2023.07.25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCart {
    private Integer quantity;
    private Long cartId;

    public Cart toEntity() {
        return Cart.toEdit()
                .quantity(quantity)
                .id(cartId)
                .editBuild()
                ;
    }
}
