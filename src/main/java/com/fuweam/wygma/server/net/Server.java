package com.fuweam.wygma.server.net;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.fuweam.wygma.server.WygmaServer;
import com.fuweam.wygma.spec.net.Codec;

public class Server
{
    public final static int       PORT  = 6543;
    
    private final WygmaServer     main;
    
    private final ServerBootstrap bootstrap;
    private final Codec           codec = new Codec();
    
    public Server(WygmaServer main)
    {
        this.main = main;
        
        bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()));
        
        bootstrap.setPipelineFactory(new ServerPipelineFactory(this));
    }
    
    public void start()
    {
        bootstrap.bind(new InetSocketAddress(PORT));
    }
    
    public void stop()
    {
        
    }
    
    public WygmaServer getMain()
    {
        return main;
    }
    
    public ServerBootstrap getBootstrap()
    {
        return bootstrap;
    }
    
    public Codec getCodec()
    {
        return codec;
    }
}
