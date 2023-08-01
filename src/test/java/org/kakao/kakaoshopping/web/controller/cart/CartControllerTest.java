package org.kakao.kakaoshopping.web.controller.cart;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureWebMvc
class CartControllerTest {
//
//    @Autowired
//    MockMvc mvc;
/*
@PostMapping("/createCart")
    public String createCart(CreateCart createCart, Long itemId, @LoginUser LoggedInUser loggedInUser) {
        cartService.addCart(createCart.toEntity(), itemId, loggedInUser.getUserId());
        return "redirect:/user/carts";
    }

    // 장바구니 목록 보여주기
    @GetMapping("/carts")
    public String viewCarts(Model model, @LoginUser LoggedInUser loggedInUser) {
        List<Cart> carts = cartService.getItemsInCart(loggedInUser.getUserId());
        model.addAttribute("items", carts);
        return "/cartViews";
    }

    // 장바구니 안 아이템 삭제
    @PostMapping("/deleteItemInCart")
    public String deleteItemInCart(EditCart cart){
        cartService.deleteItemInCart(cart.toEntity());
        return "redirect:/user/carts";
    }

    // 장바구니 체 자삭제
    @PostMapping("/deleteCart")
    public String deleteCart(@LoginUser LoggedInUser loggedInUser) {
        cartService.deleteCart(loggedInUser.getUserId());
        return "redirect:/user/carts";
    }
        //
    @PostMapping("/updateQuantityCart")
    public String updateQuantityCart(EditCart editCart){
        cartService.updateCart(editCart.toEntity());
        return "redirect:/user/carts";
    }
 */


}