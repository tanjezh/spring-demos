package com.tan.spring.beanorder.app.choose.sameclz;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tanjezh
 * @create 2024-05-30 22:23
 */
@Component
public class PrintDemoBean {

    @Resource(name = "logPrint")
    private IPrint print;

    /**
     * 如果下面的注解不指定name，则实例为logPrint
     */
    @Autowired
    @Resource(name = "consolePrint")
    private IPrint consolePrint;

    @Autowired
    private IPrint logPrint;

    @Autowired(required = false)
    private IPrint xxxPrint;

    @PostConstruct
    public void init() {
        print.print("expect logPrint for [print]");
        consolePrint.print(" expect consolePrint for [consolePrint]");
        logPrint.print("expect logPrint for [logPrint]");
        xxxPrint.print("expect logPrint for [xxxPrint]");
    }

}
