package com.fuweam.wygma.spec.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;

import com.fuweam.wygma.spec.net.msg.Message;

public interface MessageCodec<T extends Message>
{
    public byte getOpcode();
    
    public T decode(ChannelBuffer buf);
    
    public void encode(ChannelBuffer buf, T msg);
}
