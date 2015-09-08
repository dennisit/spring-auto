package com.github.yingzhuo.spring.auto.qiniuyun.core;

import java.io.IOException;
import java.io.InputStream;

public interface QiniuyunManager {

    /**
     * 上传文件 (公开方式)
     *
     * @param data 数据
     * @param key 数据key
     * @param overwrite 是否为覆盖上传 true时表示覆盖方式上传
     * @return 上传成功后资源URL
     * @throws IOException 操作失败抛出
     */
    public String upload(byte[] data, String key, boolean overwrite) throws IOException;

    /**
     * 上传文件 (公开方式)
     *
     * @param data 数据
     * @param key 数据key
     * @param overwrite 是否为覆盖上传 true时表示覆盖方式上传
     * @return 上传成功后资源URL
     * @throws IOException 操作失败抛出
     */
    public String upload(InputStream data, String key, boolean overwrite) throws IOException;

    /**
     * 资源重命名
     *
     * @param fromKey 原资源Key
     * @param toKey 新资源Key
     * @return 新的资源URL
     * @throws IOException 操作失败抛出
     */
    public String rename(String fromKey, String toKey) throws IOException;

    /**
     * 查看资源key是否存在
     *
     * @param key 待查看的资源
     * @return true时表示资源key存在
     * @throws IOException 操作失败时抛出
     */
    public boolean exists(String key) throws IOException;

    /**
     * 删除资源
     *
     * @param key 资源key
     * @throws IOException 操作失败时抛出
     */
    public void delete(String key) throws IOException;

    /**
     * 删除资源
     *
     * @param url 资源url
     * @throws IOException 操作失败时抛出
     */
    public void deleteByUrl(String url) throws IOException;
}
