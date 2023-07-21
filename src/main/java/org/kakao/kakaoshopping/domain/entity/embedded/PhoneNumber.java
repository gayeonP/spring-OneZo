package org.kakao.kakaoshopping.domain.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class PhoneNumber {

	@Column(columnDefinition = "VARCHAR(10) DEFAULT ''", nullable = false)
	private String headNumber;

	@Column(columnDefinition = "VARCHAR(10) DEFAULT ''", nullable = false)
	private String middleNumber;

	@Column(columnDefinition = "VARCHAR(10) DEFAULT ''", nullable = false)
	private String tailNumber;

	public String toStringPhone() {
		return headNumber + " - " + middleNumber + " - " + tailNumber;
	}
}
