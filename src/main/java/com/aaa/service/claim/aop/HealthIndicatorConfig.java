package com.aaa.service.claim.aop;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthIndicatorConfig {

  @Value("${spring.datasource.hikari.connection-test-query}")
  String validationquery;

  @Bean("smartDBHealthIndicator")
  public HealthIndicator smartDBHealthIndicator(@Autowired DataSource ds) {
    return new DataSourceHealthIndicator(ds, validationquery);
  }
}