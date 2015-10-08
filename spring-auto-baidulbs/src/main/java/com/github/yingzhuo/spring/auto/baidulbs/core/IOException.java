package com.github.yingzhuo.spring.auto.baidulbs.core;

public class IOException extends RuntimeException {

    public IOException(java.io.IOException ex) {
        super(ex.getMessage(), ex);
    }
}
