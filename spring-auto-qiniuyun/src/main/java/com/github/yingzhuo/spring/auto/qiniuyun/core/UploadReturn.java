package com.github.yingzhuo.spring.auto.qiniuyun.core;

/**
 * 文件上传结果
 */
public final class UploadReturn {

    public long fsize;
    public String key;
    public String hash;
    public int width;
    public int height;

    @Override
    public String toString() {
        return "{" +
                "fsize=" + fsize +
                ", key='" + key + '\'' +
                ", hash='" + hash + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
