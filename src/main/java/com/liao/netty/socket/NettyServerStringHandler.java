package com.liao.netty.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class NettyServerStringHandler extends SimpleChannelInboundHandler<String> {

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("Ip:" + ctx.channel().remoteAddress() + " msg:" + msg);

        ByteBuf respMsg = Unpooled.copiedBuffer("server reciver uuid:" + UUID.randomUUID(), CharsetUtil.UTF_8);
        ctx.channel().writeAndFlush(respMsg);
    }

}
