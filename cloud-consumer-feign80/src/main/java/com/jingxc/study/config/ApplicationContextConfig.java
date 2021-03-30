package com.jingxc.study.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextConfig {

    /**
     * NONE： 默认的，不显示任何日志
     * BASIC： 仅记录请求方法、URL、响应状态码以及执行时间
     * HEADERS：除了BASIC 中自定义的信息外，还有请求和响应的信息头
     * FULL： 除了HEADERS中定义的信息哇， 还有请求和响应的正文以及元数据
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 与yml效果相同
     * ribbon:
     *   # 指的是建立链接所用的时间，适用于网络状况正常的情况下，两端链接所用的时间
     *   ReadTimeout: 5000
     *   # 指的是建立链接后从服务器读取可用资源所用的时间
     *   ConectTimeout: 5000
     * @return
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(5000,5000,3);
    }
}
