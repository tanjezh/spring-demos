package com.tan.spring.aop;

import com.tan.spring.aop.demo.DemoBean;
import com.tan.spring.aop.demo2.AnoDemoBean;
import com.tan.spring.aop.factory.ProxyFactoryDemoService;
import com.tan.spring.aop.order.InnerDemoBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private DemoBean demoBean;

    private AnoDemoBean anoDemoBean;

    private InnerDemoBean innerDemoBean;

    private ProxyFactoryDemoService proxyFactoryDemoService;

    public Application(DemoBean demoBean, AnoDemoBean anoDemoBean, InnerDemoBean innerDemoBean,
                       ProxyFactoryDemoService proxyFactoryDemoService){
        this.demoBean = demoBean;
        this.anoDemoBean = anoDemoBean;
        this.innerDemoBean = innerDemoBean;

        this.proxyFactoryDemoService = proxyFactoryDemoService;
        this.proxyFactoryDemoService.testShow();


//        innerDemoBean();
        anoDemoBean();
//        doDemoBeanMethod();
    }

    private void innerDemoBean() {
        System.out.println("result: " + innerDemoBean.print());
    }

    private void anoDemoBean() {
        System.out.println(">>>>>>>" + anoDemoBean.randomUUID(System.currentTimeMillis()));
//        anoDemoBean.scopeUUID(System.currentTimeMillis());
    }

    private void doDemoBeanMethod(){
        System.out.println(">>>> " + demoBean.getUUID(System.currentTimeMillis()));
    }

//    @SuppressWarnings("unchecked")
//    public Application(Environment environment) {
//        final String KEY = "alarm.title";
//        System.out.println("old title ---> " + environment.getProperty(KEY));
//
//
//        OriginTrackedValue value = (OriginTrackedValue) ((Map) ((StandardEnvironment) environment).getPropertySources()
//                .get("applicationConfig: [classpath:/application.yml]").getSource()).get(KEY);
//        OriginTrackedValue newVal = OriginTrackedValue.of("newTitle", value.getOrigin());
//
//        ((Map) ((StandardEnvironment) environment).getPropertySources()
//                .get("applicationConfig: [classpath:/application.yml]").getSource()).put(KEY, newVal);
//
//        System.out.println("new title ----> " + environment.getProperty(KEY));
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
