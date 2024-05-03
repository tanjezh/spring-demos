package com.tan.spring.log.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author tanjezh
 * @create 2024-05-03 17:15
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    protected HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String hello() throws RemoteException {
        System.out.println("HelloServiceImpl hello method test data!");
        return "(HelloServiceImpl) Say Hello";
    }
}
