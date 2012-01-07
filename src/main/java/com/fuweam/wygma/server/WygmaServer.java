package com.fuweam.wygma.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fuweam.wygma.server.net.Server;

public class WygmaServer
{
    private final Server          server;
    
    private final ExecutorService worker = Executors.newCachedThreadPool();
    
    public WygmaServer()
    {
        // Init Server
        server = new Server(this);
    }
    
    public void start()
    {
        // Starting server...
        server.start();
    }
    
    public void stop()
    {
        // Stopping server...
        server.stop();
        
        // Stopping worker...
        worker.shutdown();
    }
    
    public Server getServer()
    {
        return server;
    }
    
    public ExecutorService getWorker()
    {
        return worker;
    }
    
    public static void main(String[] args)
    {
        WygmaServer main = new WygmaServer();
        main.start();
    }
    
}
