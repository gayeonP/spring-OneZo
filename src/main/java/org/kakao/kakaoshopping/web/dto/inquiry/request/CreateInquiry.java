package org.kakao.kakaoshopping.web.dto.inquiry.request;

import org.kakao.kakaoshopping.domain.entity.inquiry.Inquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 기능 : 상품 문의를 생성하는 DTO.
 * 작성자 - 임창준
 * 작성일 - 2020.07.25
 */
public class CreateInquiry {
	// cj 게시물 생성에 필요한 필드를 선언.
	private String title;
	private String contents;

	// cj builder를 통해 DTO를 Entity로 변환.
	public Inquiry toEntity() {
		return Inquiry.builder()
			.title(title)
			.contents(contents)
			.build();
	}
}
