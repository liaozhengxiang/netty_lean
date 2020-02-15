package com.liao.netty.thrift;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.haproxy.HAProxyProxiedProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import sun.misc.Cleaner;
import thrift.gen.Person;
import thrift.gen.PersonService;

public class ThriftClient {

    public static void main(String[] args) throws TException {

        TTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 9999));
        TProtocol protocol = new TCompactProtocol(transport);

        transport.open();

        PersonService.Client client = new PersonService.Client(protocol);
        Person person = client.getByUserName("张三");
        System.out.println(JSON.toJSONString(person));

        person.setUsername("李四");

        client.savePerson(person);

        transport.close();


    }
}
