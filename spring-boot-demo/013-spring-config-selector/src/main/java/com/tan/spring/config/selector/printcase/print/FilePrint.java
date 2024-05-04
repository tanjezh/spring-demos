package com.tan.spring.config.selector.printcase.print;

/**
 * @author tanjezh
 * @create 2024-05-04 22:10
 */
public class FilePrint implements InterfacePrint{

    @Override
    public void print() {
        System.out.println("File print");
    }
}
