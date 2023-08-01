package org.kakao.kakaoshopping.web.dto.inquiry.response;

import org.kakao.kakaoshopping.domain.entity.inquiry.Inquiry;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InquirySimpleView {

	// 게시물 Id
	private Long id;
	// 작성자 이름
	private String username;
	// 글 제목
	private String title;
	// 글 제목
	private int commentSize;

	public InquirySimpleView(Inquiry inquiry) {
		this.id = inquiry.getId();
		this.username = inquiry.getUser().getName();
		this.title = inquiry.getTitle();
		this.commentSize = inquiry.getComments().size();
	}
}
