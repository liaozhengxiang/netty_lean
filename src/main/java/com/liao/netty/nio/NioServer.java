package com.liao.netty.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class NioServer {

    private static ConcurrentHashMap<String, SocketChannel> clientMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(9999));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                //接收客户端的连接，并监听客户端的内容读取事件
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    clientMap.put(UUID.randomUUID().toString(), client);

                    System.out.println(client + ":连接成功");
                } else if (selectionKey.isReadable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    //将读取到的客户端内容打印到控制台
                    if (client.read(byteBuffer) > 0) {
                        byteBuffer.flip();
                        System.out.println(String.valueOf(StandardCharsets.UTF_8.decode(byteBuffer).array()));
                    }
                    byteBuffer.clear();

                    //回写内容
                    byteBuffer.put("from server:收到了 \n".getBytes());
                    byteBuffer.flip();
                    client.write(byteBuffer);
                }

            }
            selectionKeys.clear();

        }


    }
}
