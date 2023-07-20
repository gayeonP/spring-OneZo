package org.kakao.kakaoshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class KakaoShoppingApplication {
	public static void main(String[] args) {
		SpringApplication.run(KakaoShoppingApplication.class, args);
	}
}
