package com.liao.jvm.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonServiceProxy implements InvocationHandler {

    private PersonService personService;

    public PersonServiceProxy(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("PersonServiceProxy before");
        Object result = method.invoke(personService, args);
        System.out.println("PersonServiceProxy after");
        return result;
    }
}
