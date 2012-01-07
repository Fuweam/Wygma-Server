package com.fuweam.wygma.spec.net.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import com.fuweam.wygma.spec.net.msg.Message;

public interface MessageHandler<T extends Message>
{
    public void handle(T msg, ChannelHandlerContext ctx, MessageEvent e);
}
