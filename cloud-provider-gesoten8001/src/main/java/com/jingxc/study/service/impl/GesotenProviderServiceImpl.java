package com.jingxc.study.service.impl;import com.jingxc.study.service.GesotenProviderService;import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;import lombok.SneakyThrows;import org.springframework.stereotype.Service;import java.util.UUID;import java.util.concurrent.TimeUnit;@Servicepublic class GesotenProviderServiceImpl implements GesotenProviderService {    @Override    @SneakyThrows    @HystrixCommand(fallbackMethod = "payInfoTimeOutHandler", commandProperties = {            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")    })    public String providerHystrix(Integer id) {        System.out.println("进入服务端：8001========>"+System.currentTimeMillis()/1000);        int timeNumber = 11;        TimeUnit.SECONDS.sleep(timeNumber);        return "Hystrix test" + "\tid "+ id+"\n"+ UUID.randomUUID().toString();    }    public String payInfoTimeOutHandler(Integer id) {        System.out.println("服务端超时：8001========>"+System.currentTimeMillis()/1000);        return "服务端执行超时，调用服务降级方法～"+id;    }}