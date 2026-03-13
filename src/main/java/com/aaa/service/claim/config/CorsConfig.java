package com.aaa.service.claim.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aaa.service.interceptor.JwtInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {
	
	private final JwtInterceptor jwtInterceptor;
	
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
	        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS")
	        .allowedHeaders("*")
	        .maxAge(3600)
	        .allowedOrigins("*");
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//addJwtInterceptors(registry);  To authenticate the bearer token in te header
	}
	
	private void addJwtInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(jwtInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("**/*swagger*/**")
			.excludePathPatterns("/**/*swagger*/**")
			.excludePathPatterns("/**/**/**springfox**")
			.excludePathPatterns("/**/**/**swagger**");
	}
}