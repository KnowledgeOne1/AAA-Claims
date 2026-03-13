package com.aaa.service.claim;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration.Dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableScheduling
@EntityScan("com.aaa")
@EnableAsync
@EnableAutoConfiguration
@ComponentScan({"com.aaa"})
@EnableJpaRepositories(basePackages = {"com.aaa"})
@PropertySource({"classpath:application.properties"})
public class ClaimApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(ClaimApplication.class, args);
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		try (AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext()){
			context.register(ClaimApplication.class);
			context.setServletContext(servletContext);
			Dynamic reg = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
			reg.addMapping("/*");
			reg.setAsyncSupported(true);
			super.onStartup(servletContext);
		}
	}
	
	//	Allows service to be run externally on Tomcat (i.e. in my AWS EC2 Instance)
	@Override
	protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(ClaimApplication.class);
	}
}
