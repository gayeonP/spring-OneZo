package org.kakao.kakaoshopping.web.dto.inquiry.response;

import org.kakao.kakaoshopping.domain.entity.inquiry.Inquiry;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InquiryDetailView {
	// 게시물 Id
	private Long id;
	// 작성자 Id
	private Long userId;
	// 글 제목
	private String title;
	// 글 내용
	private String contents;

	public InquiryDetailView(Inquiry inquiry) {
		this.id = inquiry.getId();
		this.userId = inquiry.getUser().getId();
		this.title = inquiry.getTitle();
		this.contents = inquiry.getContents();
	}
}
