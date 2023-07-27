package org.kakao.kakaoshopping.web.controller.cart;

import lombok.RequiredArgsConstructor;
import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.service.cart.CartService;
import org.kakao.kakaoshopping.web.annotaion.LoginUser;
import org.kakao.kakaoshopping.web.dto.cart.request.CreateCart;
import org.kakao.kakaoshopping.web.dto.cart.request.EditCart;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * 기능: 장바구니에 아이템을 담는다
     * 작성자 - 장규민
     * 작성일 - 2023.07.25
     *
     * @param createCart
     * @param id
     * @param loggedInUser
     * @return String
     */
    @PostMapping("/createCart/{id}")
    public String createCart(CreateCart createCart, @PathVariable Long id, @LoginUser LoggedInUser loggedInUser) {
        cartService.addCart(createCart.toEntity(), id, loggedInUser.getUserId());
        return "redirect:/user/carts";
    }

    /**
     * 기능: 장바구니를 조회한다.
     * 작성자 - 장규민
     * 작성일 - 2023.07.25
     *
     * @param model
     * @param loggedInUser
     * @return String
     */
    @GetMapping("/carts")
    public String viewCarts(Model model, @LoginUser LoggedInUser loggedInUser) {
        List<Cart> carts = cartService.getItemsInCart(loggedInUser.getUserId());
        model.addAttribute("items", carts);
        return "/cartViews";
    }

    /**
     * 기능: 장바구니 안 아이템을 삭제한다.
     * 작성자 - 박가연
     * 작성일 - 2023.07.25
     *
     * @param cart
     * @return String
     */
    // 장바구니 안 아이템 삭제
    @PostMapping("/deleteItemInCart")
    public String deleteItemInCart(EditCart cart) {
        cartService.deleteItemInCart(cart.toEntity());
        return "redirect:/user/carts";
    }

    /**
     * 기능: 장바구니 자체를 삭제한다.
     * 작성자 - 박가연
     * 작성일 - 2023.07.25
     *
     * @param loggedInUser
     * @return String
     */
    @PostMapping("/deleteCart")
    public String deleteCart(@LoginUser LoggedInUser loggedInUser) {
        cartService.deleteCart(loggedInUser.getUserId());
        return "redirect:/user/carts";
    }

    /**
     * 기능: 장바구니 아이템 수량을 업데이트한다.
     * 작성자 - 박가연
     * 작성일 - 2023.07.25
     *
     * @param editCart
     * @return String
     */
    @PostMapping("/updateQuantityCart")
    public String updateQuantityCart(EditCart editCart) {
        cartService.updateCart(editCart.toEntity());
        return "redirect:/user/carts";
    }
}
