package org.kakao.kakaoshopping.web.controller.cart;

import lombok.RequiredArgsConstructor;
import org.kakao.kakaoshopping.domain.service.cart.CartService;
import org.kakao.kakaoshopping.web.annotaion.LoginUser;
import org.kakao.kakaoshopping.web.dto.cart.request.CreateCart;
import org.kakao.kakaoshopping.web.dto.cart.response.SendCartId;
import org.kakao.kakaoshopping.web.dto.member.login.LoggedInUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/createCart")
    @ResponseBody
    public ResponseEntity<SendCartId> createCart(CreateCart createCart, Long itemId, @LoginUser LoggedInUser loggedInUser) {
        cartService.addCart(createCart.toEntity(), itemId, loggedInUser.getUserId());
        return null;
    }

    // need?
//    @PostMapping("/insertCart")
//    public String insertCart(CreateCart createCart, HttpServletRequest request) {
//        if (createCart.getCartId() == null) {
//            return "/createCart";
//        }
//        return "";
//    }

    // 장바구니 목록 보여주기
    @GetMapping("/carts")
    public String viewCarts() {

        return "";
    }
}
