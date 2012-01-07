package com.fuweam.wygma.spec.net.msg;

public class ConnectionResultMessage implements Message
{
    private Result result;
    private String message;
    
    public Result getResult()
    {
        return result;
    }
    
    public void setResult(byte code)
    {
        for (Result result : Result.values())
        {
            if (result.code == code)
            {
                setResult(result);
            }
        }
    }
    
    public void setResult(Result result)
    {
        this.result = result;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public static enum Result
    {
        SUCCESS(0x01), FAIL(0x02);
        
        private final byte code;
        
        Result(int code)
        {
            if (code > 255)
            {
                this.code = (byte) 255;
            }
            else
            {
                this.code = (byte) code;
            }
        }
        
        Result(byte code)
        {
            this.code = code;
        }
        
        public byte getCode()
        {
            return code;
        }
    }
}
