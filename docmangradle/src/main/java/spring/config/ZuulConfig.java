package spring.config;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Bean;
import spring.filters.*;

@Configuration
public class ZuulConfig {

    @org.springframework.context.annotation.Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @org.springframework.context.annotation.Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
}
