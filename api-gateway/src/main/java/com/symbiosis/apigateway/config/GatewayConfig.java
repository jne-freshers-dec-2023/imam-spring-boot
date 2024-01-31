//package com.symbiosis.apigateway.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GatewayConfig {
//
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
//        routeLocatorBuilder.routes().route("routToDepartment", predicateSpec -> predicateSpec.path("/symbiosis/department/**").uri("lb://department-service"))
//                                    .route("routToStudent",predicateSpec -> predicateSpec.path("/api/v1/symbiosis/student/**").uri("lb://student-service"));
//        return routeLocatorBuilder.routes().build();
//    }
//
//
//}
