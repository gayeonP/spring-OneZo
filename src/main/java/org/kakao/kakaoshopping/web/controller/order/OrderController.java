package org.kakao.kakaoshopping.web.controller.order;

import java.util.List;

import org.kakao.kakaoshopping.domain.entity.cart.Cart;
import org.kakao.kakaoshopping.domain.entity.order.Order;
import org.kakao.kakaoshopping.domain.service.cart.CartService;
import org.kakao.kakaoshopping.domain.service.order.OrderService;
import org.kakao.kakaoshopping.web.annotaion.LoginUser;
import org.kakao.kakaoshopping.web.common.paging.request.OrderSearchCondition;
import org.kakao.kakaoshopping.web.dto.cart.response.CartSimpleView;
import org.kakao.kakaoshopping.web.dto.order.request.CreateOrder;
import org.kakao.kakaoshopping.web.dto.order.request.CreateOrderItem;
import org.kakao.kakaoshopping.web.dto.order.response.OrderComplexView;
import org.kakao.kakaoshopping.web.dto.order.response.OrderSimpleView;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final CartService cartService;
	/**
	 * 기능 : 주문을 등록한다.
	 * 작성자 - 장원준
	 * 작성일 - 2023.07.14ㅐ
	 * 수정자 - 장원준
	 * 수정일 - 2023.07.25
	 * @param createOrder
	 * @param loginUser
	 * @param model
	 * @return
	 */
	@PostMapping("/order/create")
	public String createOrder(CreateOrder createOrder, @LoginUser LoggedInUser loginUser,
		Model model) {
		List<Long> ids = createOrder.getOrderItems().stream()
			.map(CreateOrderItem::getItemId).toList();
		Long saveOrderId = orderService.creatOrder(createOrder.toEntity(), loginUser.getUserId());

		model.addAttribute("orderId", saveOrderId); // 한 번더 조회해야 된다.

		return "order/orderCompleteView";
	}

	/**
	 * 기능 : 장바구니의 데이터를 바탕으로 주문을 등록한다.
	 * 작성자 - 장원준
	 * 작성일 - 2023.07.25
	 * 수정자 - 장원준
	 * 수정일 - 2023.07.25
	 * @param createOrder
	 * @param loggedInUser
	 * @param cardId
	 * @return
	 */
	public String createOrderFromCart(CreateOrder createOrder, @LoginUser LoggedInUser loggedInUser, Long cardId) {
		// todo 장바구니에서 가져온 주문 데이터를 등록하는 로직이 필요함.
		// todo 장바구니 번호를 가지고 DB를 조회해서 장바구니에 등록된 상품들을 등록해줘야 됨.
		orderService.creatOrderFromCart(createOrder.toEntity(), loggedInUser.getUserId(), cardId);

		return "redirect:/orders";
	}

	/**
	 * 기능 : 주문 1건을 조회한다.
	 * 작성자 - 장원준
	 * 작성일 - 2023.07.14
	 * 수정자 - 장원준
	 * 수정일 - 2023.07.25
	 * @param orderId
	 * @param model
	 * @return
	 */
	@GetMapping("/order")
	public String findOrder(Long orderId, Model model) {
		Order order = orderService.findOrder(orderId);
		model.addAttribute("order", new OrderComplexView(order));

		return "order/orderView";
	}

	/**
	 * 기능 : 주문 여러 건을 조회한다.
	 * 작성자 - 장원준
	 * 작성일 - 2023.07.14
	 * 수정자 - 장원준
	 * 수정일 - 2023.07.25
	 * @param loggedInUser
	 * @param condition
	 * @param model
	 * @return
	 */
	@GetMapping("/orders")
	public String findOrders(@LoginUser LoggedInUser loggedInUser, OrderSearchCondition condition, Model model) {
		Page<Order> orders = orderService.findOrders(condition);

		List<OrderSimpleView> orderViews = orders.getContent().stream()
			.map(OrderSimpleView::new)
			.toList();

		return "order/orderViews";
	}

	/**
	 * 기능 : 삭제기능으로 분기하는 컨트롤러
	 * 작성자 - 장원준
	 * 작성일 - 2023.07.20
	 * 수정자 - 임창준
	 * 수정일 - 2023.07.21
	 * @param orderId
	 */
	@GetMapping("/order/delete")
	public String deleteOrder(Long orderId) {
		orderService.deleteOrder(orderId);

		return "redirect:/orders";
	}

	@GetMapping("/order/form")
	public String orderForm(Model model, @LoginUser LoggedInUser loggedInUser) {
		List<Cart> carts = cartService.getItemsInCart(loggedInUser.getUserId());
		List<CartSimpleView> cartSimpleView = carts.stream().map(CartSimpleView::new)
			.toList();
		model.addAttribute("carts", cartSimpleView);
		return "order/orderForm";
	}
}
