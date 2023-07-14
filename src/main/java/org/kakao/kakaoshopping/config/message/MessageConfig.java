package org.kakao.kakaoshopping.config.message;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		// 필요한 메세지 파일을 등록 (resource 까지는 기본으로, 이후 디렉토리까지 설정 가능)
		messageSource.setBasenames("message/messages", "message/errors");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}