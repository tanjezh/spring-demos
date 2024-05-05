package com.tan.spring.dynamic;

import com.tan.spring.dynamic.manual.ManualBean;
import com.tan.spring.dynamic.manual.ManualBeanRegisterUtil;
import com.tan.spring.dynamic.manual.ManualDIBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 手动注册bean的配置类，需要放在主程序类同一目录下，否则不能保证bean在被注入之前就初始化完毕
 *
 * @author tanjezh
 * @create 2024-05-05 15:49
 */
@Configuration
public class BeanAutoRegister {

    public BeanAutoRegister(ConfigurableApplicationContext context){
        System.out.println("BeanAutoRegister load time : "+System.currentTimeMillis());
        registerBean(context);
    }

    /**
     * 手动注册自定义bean
     * @param context
     */
    private void registerBean(ConfigurableApplicationContext context) {
        // 主动注册一个没什么依赖的Bean
        ManualBean manualBean = ManualBeanRegisterUtil.registerBean(context, "manualBean", ManualBean.class);
        manualBean.print("invoke by BeanAutoRegister");

        // manualDIBean 内部，依赖由Spring容器创建的OriginBean
        ManualDIBean manualDIBean = ManualBeanRegisterUtil.registerBean(context, "manualDIBean",
                ManualDIBean.class,"依赖OriginBean的ManualDIBean");
        manualDIBean.print("invoke by BeanAutoRegister");

    }

}
