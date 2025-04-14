package com.example.NewsFeed.config;

import com.example.NewsFeed.fillter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링 설정 클래스
@Configuration
public class FilterConfig {

    // 로그인 필터 등록
    // 모든 요청에 로그인 필터 적용 및 실행 순서 설정
    @Bean
    public FilterRegistrationBean loginFilter() {

        //필터 등록 클래스
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        //로그인 필터 등록
        filterRegistrationBean.setFilter(new LoginFilter());

        //필터 실행 순서
        filterRegistrationBean.setOrder(1);

        //모든 요청 url에 적용
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
