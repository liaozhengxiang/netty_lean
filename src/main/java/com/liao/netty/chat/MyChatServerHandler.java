package com.liao.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.StringUtil;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        //获取消息，并将消息广播到所有的channel去

        if (StringUtil.isNullOrEmpty(msg)) {
            System.out.println("消息内容为空");
            return;
        }


        Channel channel = ctx.channel();

        channelGroup.forEach(ch -> {
            if (ch == channel) {
                ch.writeAndFlush("自己say:" + msg + "\r\n");
            } else {
                ch.writeAndFlush(ch.remoteAddress() + " say:" + msg + "\r\n");
            }
        });

    }


    /**
     * 当有新的channel连接加入时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        //将上线信息广播到所有在线的channel
        channelGroup.writeAndFlush(channel.remoteAddress() + ":上线了\r\n");

        channelGroup.add(channel);
    }


    /**
     * 当有的channel下线或者移除时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush(channel.remoteAddress() + ":下线了\r\n");

        //无需手动移除 netty会帮我们自动移除
        //channelGroup.remove(channel);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端" + ctx.channel().remoteAddress() + ":上线了\r\n");
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端" + ctx.channel().remoteAddress() + ":下线了\r\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
