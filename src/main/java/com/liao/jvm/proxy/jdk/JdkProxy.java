package com.liao.jvm.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * jdk 动态代理
 */
public class JdkProxy {

    public static void main(String[] args) {

//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        PersonService personService = new PersonServiceImpl();

        Class<? extends PersonService> clazz = personService.getClass();

        InvocationHandler personServiceProxy = new PersonServiceProxy(personService);
        PersonService o = (PersonService) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), personServiceProxy);

        o.test();
    }

}
