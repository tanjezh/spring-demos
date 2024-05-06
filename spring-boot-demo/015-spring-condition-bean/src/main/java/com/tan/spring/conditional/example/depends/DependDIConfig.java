package com.tan.spring.conditional.example.depends;

import com.tan.spring.conditional.example.depends.bean.DependBean;
import com.tan.spring.conditional.example.depends.bean.LoadIfBeanNotExist;
import com.tan.spring.conditional.example.depends.bean.LoadIfDependBeanExist;
import com.tan.spring.conditional.example.depends.clz.DependClz;
import com.tan.spring.conditional.example.depends.clz.LoadIfDependClzExist;
import com.tan.spring.conditional.example.depends.clz.LoadIfDependClzNotExist;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjezh
 * @create 2024/5/6 11:01
 */
@Configuration
public class DependDIConfig {

    @Bean
    public DependBean dependBean(){
        return new DependBean("DependBean load");
    }

    /**
     * 只有当 DependBean 存在时，才会创建bean: `LoadIfDependBeanExist`
     *
     */
    @Bean
    @ConditionalOnBean(name = "dependBean")
    public LoadIfDependBeanExist loadIfDependBeanExist(){
        return new LoadIfDependBeanExist("loadIfDependBeanExist");
    }

    @Bean
    @ConditionalOnMissingBean(name = "notExistBean")
    public LoadIfBeanNotExist loadIfBeanNotExist(){
        return new LoadIfBeanNotExist("LoadIfBeanNotExist");
    }

    /**
     * 当引用了 {@link DependClz} 类之后，才会创建bean： `LoadIfClzExists`
     *
     */
    @Bean
//    @ConditionalOnClass(name = "dependClz")
    @ConditionalOnClass(DependClz.class)
    public LoadIfDependClzExist loadIfDependClzExist(){
        return new LoadIfDependClzExist("loadIfDependClzExist");
    }

    /**
     * 当系统中没有 com.tan.spring.conditional.example.depends.clz.DependClz 类时，才会创建这个 bean
     *
     */
    @Bean
    @ConditionalOnMissingClass("com.tan.spring.conditional.example.depends.clz.DependClz")
    public LoadIfDependClzNotExist loadIfDependClzNotExist(){
        return new LoadIfDependClzNotExist("loadIfDependClzNotExist");
    }

}
