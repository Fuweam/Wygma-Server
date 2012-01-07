package com.fuweam.wygma.spec.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;

import com.fuweam.wygma.spec.net.BuffersUtils;
import com.fuweam.wygma.spec.net.msg.ConnectionResultMessage;

public class ConnectionResultCodec implements
        MessageCodec<ConnectionResultMessage>
{
    
    public byte getOpcode()
    {
        return 0x02;
    }
    
    public ConnectionResultMessage decode(ChannelBuffer buf)
    {
        ConnectionResultMessage msg = new ConnectionResultMessage();
        
        byte result = buf.readByte();
        String message = BuffersUtils.readString(buf);
        
        msg.setMessage(message);
        msg.setResult(result);
        
        return msg;
    }
    
    public void encode(ChannelBuffer buf, ConnectionResultMessage msg)
    {
        buf.writeByte(msg.getResult().getCode());
        BuffersUtils.writeString(buf, msg.getMessage());
    }
    
}
