package org.kakao.kakaoshopping.web.controller.inquiry;

import java.util.List;

import org.kakao.kakaoshopping.domain.entity.inquiry.Inquiry;
import org.kakao.kakaoshopping.domain.service.inquiry.InquiryService;
import org.kakao.kakaoshopping.web.annotaion.LoginUser;
import org.kakao.kakaoshopping.web.common.paging.request.InquirySearchCondition;
import org.kakao.kakaoshopping.web.dto.inquiry.request.CreateInquiry;
import org.kakao.kakaoshopping.web.dto.inquiry.request.EditInquiry;
import org.kakao.kakaoshopping.web.dto.inquiry.response.InquiryComplexView;
import org.kakao.kakaoshopping.web.dto.inquiry.response.InquirySimpleView;
import org.kakao.kakaoshopping.web.dto.user.login.LoggedInUser;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InquiryController {

	private final InquiryService inquiryService;

	/**
	 * 기능 : 상품문의 게시글의 리스트를 가져오는 메소드.
	 * 작성자 - 임창준
	 * 작성일 - 2023.07.25
	 * @param loggedInUser
	 * @param condition
	 * @param model
	 * @return
	 */
	@GetMapping("/inquiries")
	public String findInquiries(@LoginUser LoggedInUser loggedInUser, InquirySearchCondition condition, Model model) {
		// 페이지 인터페이스를 통해 페이징처리된 문의게시글 리스트를 가져온다.
		Page<Inquiry> Inquiries = inquiryService.findInquiries(condition);
		// 문의 게시글을 리스트로 가져와서 리스트로 만듬.
		List<InquirySimpleView> InquiryViews = Inquiries.getContent().stream()
			.map(InquirySimpleView::new)
			.toList();

		model.addAttribute("inquiries", InquiryViews);

		return "inquiry/inquiryViews";
	}

	/**
	 * 기능 : 상품문의 게시글 등록화면으로 이동하는 메소드
	 * 작성자 - 장원준
	 * 작성일 - 2023.07.25
	 * @param loggedInUser
	 * @param itemId
	 * @param model
	 * @return
	 */
	@GetMapping("/inquiry/create")
	public String createInquiryForm(@LoginUser LoggedInUser loggedInUser, Long itemId, Model model) {

		model.addAttribute("userId", loggedInUser.getUserId());
		model.addAttribute("itemId", itemId);

		return "inquiry/createInquiryView";
	}

	/**
	 * 기능 : 상품문의 게시글을 생성하는 메소드
	 * 작성자 - 임창준
	 * 작성일 - 2023.07.25
	 * 수정자 - 장원준
	 * 수정일 - 2023.07.26
	 * @param createInquiry
	 * @param loginUser
	 * @return
	 */
	@PostMapping("/inquiry/create")
	public String createInquiry(CreateInquiry createInquiry, @LoginUser LoggedInUser loginUser) {
		// 게시판 글을 생성하나고 리스트로 리다이렉트.
		Long saveInquiryId = inquiryService.createInquiry(createInquiry.toEntity(), loginUser.getUserId());

		return "redirect:/inquiries?inquiryId=" + saveInquiryId;
	}

	/**
	 * 기능 : 상품 문의의 상세정보를 보여주는 메소드
	 * 작성자 - 임창준
	 * 작성일 - 2023.07.25
	 * 수정자 - 장원준
	 * 수정일 - 2023.07.26
	 * @param inquiryId
	 * @param model
	 * @return
	 */
	@GetMapping("/inquiry/detail")
	public String detailInquiry(Long inquiryId, Model model) {
		// Inquiry 엔티티를 가져와 상세보기 view DTD에 담에 뷰에 넘겨준다.
		Inquiry inquiry = inquiryService.detailInquiry(inquiryId);

		model.addAttribute("inquiry", new InquiryComplexView(inquiry));

		return "inquiry/inquiryView";
	}

	/**
	 * 기능 : 상품 문의를 삭제하는 메소드
	 * 작성자 - 임창준
	 * 작성일 - 2023.07.25
	 * @param inquiryId
	 * @return
	 */
	@GetMapping("/inquiry/delete")
	public String deleteInquiry(Long inquiryId) {
		inquiryService.deleteInquiry(inquiryId);

		return "redirect:/inquiries";
	}

	/**
	 * 기능 : 상품 문의 게시글 수정화면으로 이동하는 메소드
	 * 작성자 - 임창준
	 * 작성일 - 2023.07.26
	 * @param inquiryId
	 * @return
	 */
	@GetMapping("/inquiry/edit")
	public String editInquiryForm(Long inquiryId, Model model) {

		Inquiry inquiry = inquiryService.detailInquiry(inquiryId);

		model.addAttribute("inquiry", new InquiryComplexView(inquiry));

		return "inquiry/editInquiryView";
	}

	/**
	 * 기능 : 상품 문의 게시글 수정하는 메소드
	 * 작성자 - 임창준
	 * 작성일 - 2023.07.26
	 * 수정자 - 장원준
	 * 수정일 - 2023.07.26
	 * @param editInquiry
	 * @param inquiryId
	 * @return
	 */
	@PostMapping("/inquiry/edit")
	public String editInquiry(EditInquiry editInquiry, Long inquiryId) {
		Long savedInquiryId = inquiryService.editInquiry(inquiryId, editInquiry);

		return "redirect:/inquiry/detail?inquiryId=" + savedInquiryId;
	}
}