package com.tan.spring.properties.priority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application {

    private void testMultEnv(Environment environment){
        String env = environment.getProperty("config.env");

        String whitelist = environment.getProperty("config.whitelist");
        String ratelimit = environment.getProperty("config.ratelimit");
        String total = environment.getProperty("config.total");
        String profile = environment.getProperty("config.profile");

        System.out.println("env: " + env);

        // read from application-biz.yml
        System.out.println("whitelist: " + whitelist);
        System.out.println("ratelimit: " + ratelimit);


        // 当配置文件 application.yml, application-dev.yml, application-pre.yml 三个文件都存在时，覆盖规则为
        // pre > dev > application.yml  （其中 pre>dev的原则是根据 spring.profile.active 中定义的顺序来的，最右边的优先级最高）
        // read from application-pre.yml
        System.out.println("total: " + total);

        // read from application-pre.yml
        System.out.println("profile: " + profile);
    }

    /**
     * 测试 config/application.yml 与 application.yml 的优先级情况
     * config/application.yml 优先
     */
    private void testFileSort(Environment environment){
        String source = environment.getProperty("source");
        System.out.println(source);
    }

    public Application(Environment environment){
//        testFileSort(environment);
        testMultEnv(environment);
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            SpringApplication.run(Application.class, "--spring.profiles.active=" + args[0] + ",pre");
        } else {
            SpringApplication.run(Application.class);
        }
    }

}
