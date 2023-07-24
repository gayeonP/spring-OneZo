package org.kakao.kakaoshopping.web.dto.item.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSimpleView {
	private String name;
	private BigDecimal price;
	private String imagePath;

}
