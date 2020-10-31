package com.jin;

import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局
 * @author wu.jinqing
 * @date 2019年11月26日
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        System.out.println("MyGlobalFilter");
        String path = exchange.getRequest().getPath().toString();
        MDC.put("name", "zhangsan");
        Mono<Void> m= chain.filter(exchange);

        System.out.println(exchange.getResponse().getStatusCode());
        return m;
    }
}
