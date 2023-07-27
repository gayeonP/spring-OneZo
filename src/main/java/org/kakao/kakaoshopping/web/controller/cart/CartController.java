package org.kakao.kakaoshopping.web.controller.cart;

import java.util.List;

import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.service.cart.CartService;
import org.kakao.kakaoshopping.web.annotaion.LoginUser;
import org.kakao.kakaoshopping.web.dto.cart.request.CartToOrder;
import org.kakao.kakaoshopping.web.dto.cart.request.CreateCart;
import org.kakao.kakaoshopping.web.dto.cart.request.EditCart;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;

	// 장바구니 담기
	@PostMapping("/createCart")
	public String createCart(CreateCart createCart, Long itemId, @LoginUser LoggedInUser loggedInUser) {
		cartService.addCart(createCart.toEntity(), itemId, loggedInUser.getUserId());
		return "redirect:/user/carts";
	}

	// 장바구니 목록 보여주기
	@GetMapping("/carts")
	public String viewCarts(Model model, @LoginUser LoggedInUser loggedInUser) {
		List<Cart> carts = cartService.getItemsInCart(loggedInUser.getUserId());
		model.addAttribute("carts", carts);
		return "/cartViews";
	}

	// 장바구니 안 아이템 삭제
	@PostMapping("/deleteItemInCart")
	@ResponseBody
	public ResponseEntity<Void> deleteItemInCart(@RequestBody EditCart cart) {
		cartService.deleteItemInCart(cart.toEntity());
		return ResponseEntity.ok().build();
	}

	// 장바구니 자체 삭제
	@PostMapping("/deleteCart")
	public String deleteCart(@LoginUser LoggedInUser loggedInUser) {
		cartService.deleteCart(loggedInUser.getUserId());
		return "redirect:/user/carts";
	}

	// 장바구니 수량 업데이트
	@PostMapping("/updateQuantityCart")
	@ResponseBody
	public ResponseEntity<Integer> updateQuantityCart(@RequestBody EditCart editCart) {
		Integer savedQuantity = cartService.updateCart(editCart.toEntity());
		return ResponseEntity.ok(savedQuantity);
	}

	@GetMapping("/orderForm")
	public String test(List<CartToOrder> carts) {
		// DB 가서 아이템 조회 후, 주문에 필요한 정보를 dto에 담아서\
		// 뷰로 전달해 주면 된다.
		return "cart/cartViews";
	}
}
