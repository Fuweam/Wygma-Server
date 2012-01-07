package com.fuweam.wygma.spec.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;

import com.fuweam.wygma.spec.net.BuffersUtils;
import com.fuweam.wygma.spec.net.msg.ConnectionMessage;

public class ConnectionCodec implements MessageCodec<ConnectionMessage>
{
    
    public byte getOpcode()
    {
        return 0x01;
    }
    
    public ConnectionMessage decode(ChannelBuffer buf)
    {
        ConnectionMessage message = new ConnectionMessage();
        
        String username = BuffersUtils.readString(buf);
        String password = BuffersUtils.readString(buf);
        
        message.setUsername(username);
        message.setPassword(password);
        
        return message;
    }
    
    public void encode(ChannelBuffer buf, ConnectionMessage msg)
    {
        BuffersUtils.writeString(buf, msg.getUsername());
        BuffersUtils.writeString(buf, msg.getPassword());
    }
    
}
