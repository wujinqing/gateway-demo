package com.jin;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 自定义GatewayFilter， 方式一：继承AbstractGatewayFilterFactory
 * 这种方式需要用配置文件的方式使用
 *
 *
 * @author wu.jinqing
 * @date 2019年11月26日
 */
@Component
public class PreGatewayFilterFactory extends AbstractGatewayFilterFactory<PreGatewayFilterFactory.Config> {
    public PreGatewayFilterFactory() {
        super(Config.class);
    }

    /**
     * 提供Config中的字段名称及顺序，为了能够动态绑定
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
//            System.out.println("PreGatewayFilter, name:" + config.getName());
            return chain.filter(exchange);
        });
    }

    public static class Config{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
