package com.example.javajpaplayground.config;

import com.example.javajpaplayground.domain.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public Filter filter1() {
        return new Filter("orderNumber", "ORD-100001");
    }

    @Bean
    public Filter filter2() {
        return new Filter("status", "PROCESSING");
    }

    @Bean
    public Filter filter3() {
        return new Filter("id", "1");
    }
}
