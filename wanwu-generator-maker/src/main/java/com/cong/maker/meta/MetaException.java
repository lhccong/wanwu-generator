package com.cong.maker.meta;

/**
 * 元信息异常
 * @author Cong
 */
public class MetaException extends RuntimeException {
    public MetaException(String message) {
        super(message);
    }
    public MetaException(String message,Throwable cause) {
        super(message,cause);
    }
}
