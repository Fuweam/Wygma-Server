package com.fuweam.wygma.spec.net;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;

public class BuffersUtils
{
    public static String readString(ChannelBuffer buf)
    {
        return readString(buf, Charset.forName("UTF-8"));
    }
    
    public static String readString(ChannelBuffer buf, Charset charset)
    {
        int length = buf.readInt();
        return readString(buf, length, charset);
    }
    
    public static String readString(ChannelBuffer buf, int length)
    {
        return readString(buf, length, Charset.forName("UTF-8"));
    }
    
    public static String readString(ChannelBuffer buf, int length,
            Charset charset)
    {
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        
        String str = new String(bytes, charset);
        
        return str;
    }
    
    public static void writeString(ChannelBuffer buf, String str)
    {
        writeString(buf, str, Charset.forName("UTF-8"));
    }
    
    public static void writeString(ChannelBuffer buf, String str,
            Charset charset)
    {
        byte[] bytes = str.getBytes(charset);
        
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }
}
