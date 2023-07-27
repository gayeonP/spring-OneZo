package org.kakao.kakaoshopping.domain.service.inquiry;

import static java.util.Locale.*;

import org.kakao.kakaoshopping.domain.entity.inquiry.Inquiry;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.repository.inquiry.InquiryRepository;
import org.kakao.kakaoshopping.domain.service.user.UserService;
import org.kakao.kakaoshopping.web.common.paging.request.InquirySearchCondition;
import org.kakao.kakaoshopping.web.dto.inquiry.request.EditInquiry;
import org.kakao.kakaoshopping.web.exception.InquiryNotFound;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InquiryService {

	private final UserService userService;
	private final MessageSource messageSource;
	private final InquiryRepository inquiryRepository;

	/**
	 * 기능 : 게시판글을 생성하는 서비스 메소드.
	 * 작성자 - 임창준
	 * 작성일 - 2023.07.25
	 * @param inquiry
	 * @param userId
	 * @return
	 */
	public Long createInquiry(Inquiry inquiry, Long userId) {
		// cj userId 정보를 통해 유저 객체를 가져온다.
		User user = userService.findUser(userId);
		// cj 엔티티에 user는 세터로 주입힌다.
		inquiry.setUser(user);

		return inquiryRepository.save(inquiry).getId();
	}

	/**
	 * 기능 : 문의게시글의 상세 정보를 가져오는 서비스 메소드.
	 * 작성자 - 임창준
	 * 작성일 - 2023.07.25
	 * * @param id
	 * @return
	 */
	public Inquiry detailInquiry(Long id) {
		// cj 게시글 id를 키값으로하여 게시글의 상세내용을 가져온다.
		return findById(id);
	}

	/**
	 * 기능 : 문의게시글을 페이징 처리하여 가져와 조회하는 메소드
	 * 작성자 - 임창준
	 * 작성일 - 2023.07.25
	 * @param condition
	 * @return
	 */
	public Page<Inquiry> findInquiries(InquirySearchCondition condition) {
		//
		return inquiryRepository.findInquiries(condition);
	}

	// 게시글 수정
	// todo

	/**
	 * 기능 : 상품문의 게시글을 삭제하는 메소드
	 * 작성자 - 임창준
	 * 작성일 - 2020.07.25
	 * @param inquiryId
	 */
	public void deleteInquiry(Long inquiryId) {
		inquiryRepository.deleteById(inquiryId);
	}

	private Inquiry findById(Long id) {
		return inquiryRepository.findById(id)
			.orElseThrow(() -> new InquiryNotFound(messageSource.getMessage("error.noInquiry", null, getDefault())));
	}

	public Long editInquiry(Long inquiryId, EditInquiry editInquiry) {
		Inquiry savedInquiry = findById(inquiryId);
		savedInquiry.changeTitle(editInquiry.getTitle());
		savedInquiry.changeContents(editInquiry.getContents());

		return savedInquiry.getId();
	}
}
