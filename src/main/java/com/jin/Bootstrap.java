package com.jin;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wu.jinqing
 * @date 2019年11月23日
 */
@SpringBootApplication
@EnableAutoConfiguration
@RestController
@Slf4j
public class Bootstrap {
    public static final String URL = "https://www.baidu.com/";
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder)
//    {
//        return builder.routes()
////                .route("after_route", r -> {
////
////                    System.out.println("after_route");
////
////                    LocalDateTime localDateTime = LocalDateTime.parse("20191123155300", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
////                    ZoneId zoneId = ZoneOffset.of("+08:00");
////                    ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
////                    Route.AsyncBuilder asyncBuilder = r.order(1).after(zonedDateTime).uri(URL);
////                    return asyncBuilder;
////                })
////
////                .route("before_route", r -> {
////                    System.out.println("Before Predicate");
////
////                    LocalDateTime localDateTime = LocalDateTime.parse("20191123155300", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
////                    ZoneId zoneId = ZoneOffset.of("+08:00");
////                    ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
////
////                    return r.order(1).before(zonedDateTime).uri(URL);
////                })
////
////                .route("method_route", r -> {
////                    return r.order(1).method(HttpMethod.GET).uri(URL);
////                })
//
//                .route("path_route", r -> {
//                    return r.order(0).path("/ha").filters(f -> f.filter(new PreGatewayFilter())).uri(URL);
//                })
//                .build();
//    }

    @Bean
    @Order(3)
    public GlobalFilter a() {
        return (exchange, chain) -> {
            log.info("haha");
            System.out.println("pre");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("post");
            }));
        };
    }

    @HystrixCommand
    public String cmd()
    {
        return "";
    }
}
