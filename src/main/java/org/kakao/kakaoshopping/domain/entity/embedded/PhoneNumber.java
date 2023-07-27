package org.kakao.kakaoshopping.domain.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
/**
 * 기능 : 연락처 번호 엔티티 & 전화번호 형식으로 데이터 가공
 * 작성자 - 장원준
 * 작성일 - 2023.07.20
 * 수정자 - 임창준
 * 수정일 - 2023.07.21
 */
@NoArgsConstructor
public class PhoneNumber {

	@Column(columnDefinition = "VARCHAR(10) DEFAULT ''", nullable = false)
	private String headNumber;

	@Column(columnDefinition = "VARCHAR(10) DEFAULT ''", nullable = false)
	private String middleNumber;

	@Column(columnDefinition = "VARCHAR(10) DEFAULT ''", nullable = false)
	private String tailNumber;

	@Builder
	public PhoneNumber(String headNumber, String middleNumber, String tailNumber) {
		this.headNumber = headNumber;
		this.middleNumber = middleNumber;
		this.tailNumber = tailNumber;
	}

	public String toStringPhone() {
		return headNumber + " - " + middleNumber + " - " + tailNumber;
	}
}
