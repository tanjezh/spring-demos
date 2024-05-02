package com.tan.spring.bind.util;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.bind.*;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 手动实现配置加载配置类的方式
 * @author tanjezh
 * @create 2024-05-02 15:29
 */
@Component
public class BindHelper implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void bindInfo(){
        // 直接将前缀对应的配置，加载到指定的对象中
        Binder binder = Binder.get(environment);

        Mail mail = binder.bindOrCreate("demo.mail", Mail.class);
        System.out.println("mail = "+mail);

        Mail mail2 = binder.bindOrCreate("demo.mail2", Mail.class);
        System.out.println("mail2 = "+mail2);

        try {
            mail = binder.bind("demo.mail2", Mail.class).get();
            System.out.println("mail2 的 get 方式：" + mail2);
        } catch (NoSuchElementException e) {
            // 配置不存在会报错
            System.out.println(e.getMessage());
        }

        // 将配置绑定到Map中
        Map<String, DsConfig> dsConfigMap = binder.bind("demo.dynamic",
                Bindable.mapOf(String.class, DsConfig.class)).get();
        System.out.println("mapConfig = "+dsConfigMap);

        // 将配置绑定到list
        List<Proxy> proxyList = binder.bind("demo.proxy", Bindable.listOf(Proxy.class)).get();
        System.out.println("proxyList = "+proxyList);

        // 对配置进行解析
        String result = binder.bind("demo.enc.pwd", Bindable.of(String.class))
                .map(s -> new String(Base64.getDecoder().decode(s))).get();
//        System.out.println(new String(Base64.getEncoder().encode("谈".getBytes())));
        System.out.println("解码后的内容："+result);

        // 注册绑定过程回调
        String dec = binder.bind("demo.enc.pwd", Bindable.of(String.class), new BindHandler() {
            @Override
            public <T> Bindable<T> onStart(ConfigurationPropertyName name, Bindable<T> target, BindContext context) {
                System.out.println("要开始绑定了~" + name);
                return BindHandler.super.onStart(name, target, context);
            }

            @Override
            public Object onSuccess(ConfigurationPropertyName name, Bindable<?> target, BindContext context, Object result) {
                System.out.println("绑定成功！" + name + ", value: "+target.getValue()+ ", result: "+result);
                return BindHandler.super.onSuccess(name, target, context, result);
            }

            @Override
            public Object onCreate(ConfigurationPropertyName name, Bindable<?> target, BindContext context, Object result) {
                System.out.println("创建阶段：" + name+", value: "+target.getValue()+", result: "+result);
                return BindHandler.super.onCreate(name, target, context, result);
            }

            @Override
            public Object onFailure(ConfigurationPropertyName name, Bindable<?> target, BindContext context, Exception error) throws Exception {
                System.out.println("绑定失败了！！！" + name+" 失败消息："+error.getMessage());
                return BindHandler.super.onFailure(name, target, context, error);
            }

            @Override
            public void onFinish(ConfigurationPropertyName name, Bindable<?> target, BindContext context, Object result) throws Exception {
                System.out.println("绑定结束喽~" + name+", value: "+target.getValue()+", result: "+result);
                BindHandler.super.onFinish(name, target, context, result);
            }
        }).get();

        System.out.println("绑定回调： " + dec);
        System.out.println("---------------");

    }

    @Data
    public static class DsConfig {
        private String user;
        private String password;
    }

    @Data
    public static class Proxy {
        private String ip;
        private Integer port;
    }

    @Data
    public static class Mail {
        private String host;
        private String port;
        private String username;
        private String password;
        private String from;
    }

}
