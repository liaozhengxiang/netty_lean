package com.liao.netty.thrift;

import com.alibaba.fastjson.JSON;
import org.apache.thrift.TException;
import thrift.gen.DataException;
import thrift.gen.Person;
import thrift.gen.PersonService;

public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getByUserName(String userName) throws DataException, TException {
        System.out.println("getByUserName:" + userName);
        Person person = new Person();
        person.setAge(23);
        person.setMarried(false);
        person.setUsername(userName);
        return person;
    }

    @Override
    public void savePerson(Person person) throws TException {
        System.out.println("savePerson:" + JSON.toJSONString(person));
    }
}
