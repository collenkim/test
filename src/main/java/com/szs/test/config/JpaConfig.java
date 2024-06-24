package com.szs.test.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"com.szs.test.domain"} )
public class JpaConfig {
}
