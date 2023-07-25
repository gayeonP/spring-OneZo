package org.kakao.kakaoshopping.config.web;

import java.util.List;

import org.kakao.kakaoshopping.web.argumentResolver.LoginUserArgumentResolver;
import org.kakao.kakaoshopping.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginUserArgumentResolver());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("/order/**")
			.addPathPatterns("/orders")
			.addPathPatterns("/user/**")
			.addPathPatterns("/admin/**");
	}
}
