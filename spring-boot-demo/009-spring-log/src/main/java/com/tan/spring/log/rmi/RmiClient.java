package com.tan.spring.log.rmi;

import org.apache.logging.log4j.core.lookup.JndiLookup;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;

/**
 * @author tanjezh
 * @create 2024-05-03 19:01
 */
public class RmiClient {

    public static void basic() throws Exception{
        Registry registry = LocateRegistry.createRegistry(8181);
        HelloService helloService = (HelloService) registry.lookup("hello");
        System.out.println(helloService.hello());
    }

    public static void naming() throws Exception{
        String addr = "rmi://127.0.0.1:8181/hello";
        HelloService helloService = (HelloService) Naming.lookup("hello");
        String resp = helloService.hello();
        System.out.println(resp);
    }

    public static void injectTest() throws Exception{
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        hashtable.put(Context.PROVIDER_URL,"rmi://127.0.0.1:8181/hello");
        InitialContext initialContext = new InitialContext(hashtable);
        Object inject = initialContext.lookup("rmi://127.0.0.1:8181/inject");
        System.out.println(inject);
    }

    public static void main(String[] args) {
        JndiLookup jndiLookup = new JndiLookup();
        String lookup = jndiLookup.lookup("ldap://127.0.0.1:8080/fix.txt");
        System.out.println(lookup);
    }

}
