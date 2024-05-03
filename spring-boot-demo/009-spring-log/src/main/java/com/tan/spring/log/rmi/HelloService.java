package com.tan.spring.log.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author tanjezh
 * @create 2024-05-03 17:13
 */
public interface HelloService extends Remote {

    String hello() throws RemoteException;

}
