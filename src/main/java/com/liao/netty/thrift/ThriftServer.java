package com.liao.netty.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.gen.PersonService;

public class ThriftServer {
    public static void main(String[] args) throws TTransportException {

        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(9999);

        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        THsHaServer.Args arg = new THsHaServer.Args(serverSocket).minWorkerThreads(4).maxWorkerThreads(8);
        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        System.out.println("Thrift Server Started!");
        server.serve();

    }
}
