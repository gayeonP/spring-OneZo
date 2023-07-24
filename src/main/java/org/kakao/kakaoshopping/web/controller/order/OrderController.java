package org.kakao.kakaoshopping.web.controller.order;

import java.util.List;

import org.kakao.kakaoshopping.domain.entity.order.Order;
import org.kakao.kakaoshopping.domain.service.order.OrderService;
import org.kakao.kakaoshopping.web.annotaion.LoginUser;
import org.kakao.kakaoshopping.web.common.paging.request.OrderSearchCondition;
import org.kakao.kakaoshopping.web.dto.member.login.LoggedInUser;
import org.kakao.kakaoshopping.web.dto.order.request.CreateOrder;
import org.kakao.kakaoshopping.web.dto.order.request.EditOrder;
import org.kakao.kakaoshopping.web.dto.order.response.OrderSimpleView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/order/create")
	public String createOrder(CreateOrder createOrder, @LoginUser LoggedInUser loginUser,
		RedirectAttributes rttr) {
		
		Long saveOrderId = orderService.creatOrder(createOrder.toEntity(), loginUser.getUserId());

		rttr.addFlashAttribute("orderId", saveOrderId);

		return "redirect:/orders";
	}

	@GetMapping("/order")
	public String findOrder(Long orderId, Model model) {
		Order order = orderService.findOrder(orderId);

		model.addAttribute("order", new OrderSimpleView(order));

		return "orderView";
	}

	@GetMapping("/orders")
	public String findOrders(@LoginUser LoggedInUser loggedInUser, OrderSearchCondition condition, Model model) {
		Page<Order> orders = orderService.findOrders(condition);

		List<OrderSimpleView> orderViews = orders.getContent().stream()
			.map(OrderSimpleView::new)
			.toList();

		model.addAttribute("orders", orderViews);

		return "orderViews";
	}

	@PostMapping("/order/edit")
	public String editOrder(@LoginUser LoggedInUser loggedInUser, EditOrder editOrder, Model model) {
		Long orderId = orderService.editOrder(editOrder.toEntity(), editOrder.getId());

		model.addAttribute("orderId", orderId);

		return "redirect:/order/orders";
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

		return "redirect:/order/orders";
	}
}
