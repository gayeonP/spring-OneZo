package org.kakao.kakaoshopping.domain.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Delivery {

	@Embedded
	private Address address;

	@Embedded
	private PhoneNumber phoneNumber;

	@Column(columnDefinition = "VARCHAR(100) DEFAULT ''", nullable = false)
	private String deliveryRequest;

	@Builder
	public Delivery(Address address, PhoneNumber phoneNumber, String deliveryRequest) {
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.deliveryRequest = deliveryRequest;
	}
}
