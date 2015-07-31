package com.github.yingzhuo.spring.auto.chufasms;

public class ChufaException extends RuntimeException {

    public ChufaException(String message) {
        super(message);
    }

    public ChufaException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }

}
