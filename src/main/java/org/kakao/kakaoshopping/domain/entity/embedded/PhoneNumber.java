package org.kakao.kakaoshopping.domain.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
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
