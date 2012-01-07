package com.fuweam.wygma.spec.net;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fuweam.wygma.spec.net.handler.MessageHandler;
import com.fuweam.wygma.spec.net.codec.MessageCodec;
import com.fuweam.wygma.spec.net.msg.Message;

public class Codec
{
    private final Map<Class<? extends Message>, MessageCodec<? extends Message>>   codecs   = new LinkedHashMap<Class<? extends Message>, MessageCodec<? extends Message>>();
    private final Map<Class<? extends Message>, MessageHandler<? extends Message>> handlers = new LinkedHashMap<Class<? extends Message>, MessageHandler<? extends Message>>();
    private final Map<Byte, Class<? extends Message>>                              opcodes  = new LinkedHashMap<Byte, Class<? extends Message>>();
    
    public <T extends Message, U extends MessageCodec<T>, V extends MessageHandler<T>> void register(
            Class<T> msgClass, Class<U> codecClass, Class<V> handlerClass)
            throws Exception
    {
        Constructor<U> codecConstructor = codecClass.getConstructor();
        U codec = codecConstructor.newInstance();
        
        Constructor<V> handlerConstructor = handlerClass.getConstructor();
        V handler = handlerConstructor.newInstance();
        
        byte opcode = codec.getOpcode();
        
        codecs.put(msgClass, codec);
        handlers.put(msgClass, handler);
        opcodes.put(opcode, msgClass);
    }
    
    public <T extends Message, U extends MessageCodec<T>> U getCodec(byte opcode)
    {
        return getCodec(opcodes.get(opcode));
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Message, U extends MessageCodec<T>> U getCodec(
            Class<T> clazz)
    {
        return (U) codecs.get(clazz);
    }
    
    public <T extends Message, U extends MessageHandler<T>> U getHandler(
            byte opcode)
    {
        return getHandler(opcodes.get(opcode));
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Message, U extends MessageHandler<T>> U getHandler(
            Class<T> clazz)
    {
        return (U) handlers.get(clazz);
    }
}
