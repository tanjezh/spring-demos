package com.tan.spring.config.selector.printcase.print;

/**
 * @author tanjezh
 * @create 2024-05-04 22:07
 */
public class ConsolePrint implements InterfacePrint{

    @Override
    public void print() {
        System.out.println("控制台输出");
    }

}
