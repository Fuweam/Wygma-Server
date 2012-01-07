package com.fuweam.wygma.server.net;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

public class ServerPipelineFactory implements ChannelPipelineFactory
{
    private final Server server;
    
    public ServerPipelineFactory(Server server)
    {
        this.server = server;
    }
    
    public ChannelPipeline getPipeline() throws Exception
    {
        ChannelPipeline pipeline = Channels.pipeline();
        
        pipeline.addLast("decoder", new ServerDecoder(server));
        pipeline.addLast("encoder", new ServerEncoder(server));
        pipeline.addLast("handler", new ServerHandler(server));
        
        return pipeline;
    }
    
}
