package com.liao.netty.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettySocketClient {
    public static void main(String[] args) throws Exception {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();


        try {

            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new NettyClientInitlizer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();


        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
