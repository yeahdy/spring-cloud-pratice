package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

    //application.yml 파일에 설정한 정보와 동일
//    @Bean
    public RouteLocator gatewatRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("first-request","first request value")
                                .addResponseHeader("first-response","first response value"))
                        .uri("http://localhost:8081"))
                .route(r -> r.path("/second-service/**") //application.yml 파일에 설정한 정보와 동일
                        .filters(f -> f.addRequestHeader("second-request","second request value")
                                .addResponseHeader("second-response","second response value"))
                        .uri("http://localhost:8082"))
                .build();
    }

}
