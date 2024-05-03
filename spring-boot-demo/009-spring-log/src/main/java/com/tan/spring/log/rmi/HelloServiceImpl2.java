package com.tan.spring.log.rmi;

import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;

/**
 * @author tanjezh
 * @create 2024-05-03 17:15
 */
public class HelloServiceImpl2 extends RemoteObject implements HelloService{


    @Override
    public String hello() throws RemoteException {
        System.out.println("HelloServiceImpl2 hello method test data!");
        return "(HelloServiceImpl2) Say Hello";
    }
}
