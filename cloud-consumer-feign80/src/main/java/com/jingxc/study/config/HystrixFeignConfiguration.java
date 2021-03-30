package com.jingxc.study.config;import com.netflix.hystrix.HystrixCommand;import com.netflix.hystrix.HystrixCommandGroupKey;import com.netflix.hystrix.HystrixCommandProperties;import feign.Feign;import feign.Target;import feign.hystrix.HystrixFeign;import feign.hystrix.SetterFactory;import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.context.annotation.Scope;import com.jingxc.study.service.FeignConsumerService;import java.lang.reflect.Method;@Configuration@ConditionalOnClass({ HystrixCommand.class, HystrixFeign.class })public class HystrixFeignConfiguration {    @Bean    @Scope("prototype")    @ConditionalOnMissingBean    @ConditionalOnProperty(name = "feign.hystrix.enabled", matchIfMissing = true)    public Feign.Builder feignHystrixBuilder() {        return HystrixFeign.builder().setterFactory(new SetterFactory() {            @Override            public HystrixCommand.Setter create(Target<?> target, Method method) {                return HystrixCommand.Setter                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(FeignConsumerService.class.getSimpleName()))// 控制 MessageService 下,所有方法的Hystrix Configuration                        .andCommandPropertiesDefaults(                                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(15000) // 超时配置                        );            }        });    }}