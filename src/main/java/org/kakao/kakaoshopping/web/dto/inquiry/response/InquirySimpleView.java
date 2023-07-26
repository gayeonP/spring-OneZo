package org.kakao.kakaoshopping.web.dto.inquiry.response;

import org.kakao.kakaoshopping.domain.entity.inquiry.Inquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
// @NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquirySimpleView {

	// 게시물 Id
	private Long id;
	// 작성자 Id
	private Long memberId;
	// 글 제목
	private String title;

	public InquirySimpleView(Inquiry inquiry) {
		this.id = inquiry.getId();
		this.memberId = inquiry.getUser().getId();
		this.title = inquiry.getTitle();
	}
}
