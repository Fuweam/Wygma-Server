package com.fuweam.wygma.server.net;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.fuweam.wygma.spec.net.codec.MessageCodec;
import com.fuweam.wygma.spec.net.msg.Message;

public class ServerEncoder extends OneToOneEncoder
{
    private final Server server;
    
    public ServerEncoder(Server server)
    {
        this.server = server;
    }
    
    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel,
            Object msg) throws Exception
    {
        Object encoded = msg;
        
        if (msg instanceof Message)
        {
            Message message = (Message) msg;
            Class<? extends Message> clazz = message.getClass();
            
            MessageCodec<Message> codec = server.getCodec().getCodec(clazz);
            
            if (codec != null)
            {
                ChannelBuffer buf = ChannelBuffers.dynamicBuffer();
                buf.writeByte(codec.getOpcode());
                
                codec.encode(buf, message);
                
                encoded = buf;
            }
        }
        
        return encoded;
    }
    
}
