package com.fuweam.wygma.server.net;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.fuweam.wygma.spec.net.handler.MessageHandler;
import com.fuweam.wygma.spec.net.msg.Message;

public class ServerHandler extends SimpleChannelUpstreamHandler
{
    private final Server server;
    
    public ServerHandler(Server server)
    {
        super();
        this.server = server;
    }
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
            throws Exception
    {
        if (e.getMessage() instanceof Message)
        {
            Message message = (Message) e.getMessage();
            
            MessageHandler<Message> handler = server.getCodec().getHandler(
                    message.getClass());
            
            if (handler != null)
            {
                Handler command = new Handler(ctx, e, message, handler);
                server.getMain().getWorker().execute(command);
            }
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
            throws Exception
    {
        
    }
    
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
            throws Exception
    {
        
    }
    
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx,
            ChannelStateEvent e) throws Exception
    {
        
    }
    
    public static class Handler implements Runnable
    {
        private final ChannelHandlerContext   ctx;
        private final MessageEvent            e;
        private final Message                 message;
        private final MessageHandler<Message> handler;
        
        public Handler(ChannelHandlerContext ctx, MessageEvent e,
                Message message, MessageHandler<Message> handler)
        {
            this.ctx = ctx;
            this.e = e;
            this.message = message;
            this.handler = handler;
        }
        
        public void run()
        {
            handler.handle(message, ctx, e);
        }
        
    }
}
