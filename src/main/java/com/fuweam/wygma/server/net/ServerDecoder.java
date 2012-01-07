package com.fuweam.wygma.server.net;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.fuweam.wygma.spec.net.codec.MessageCodec;
import com.fuweam.wygma.spec.net.msg.Message;

public class ServerDecoder extends OneToOneDecoder
{
    private final Server server;
    
    public ServerDecoder(Server server)
    {
        this.server = server;
    }
    
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel,
            Object msg) throws Exception
    {
        Object message = msg;
        
        if (msg instanceof ChannelBuffer)
        {
            ChannelBuffer buf = (ChannelBuffer) msg;
            
            byte opcode = buf.readByte();
            
            MessageCodec<? extends Message> codec = server.getCodec().getCodec(
                    opcode);
            
            if (codec != null)
            {
                message = codec.decode(buf);
            }
        }
        
        return message;
    }
    
}
