package com.github.yingzhuo.spring.auto.qiniuyun;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@SuppressWarnings("all")
public class QiniuyunService implements InitializingBean {

    private static final String URL_TEMPLATE = "http://%s/%s";

    private Auth auth;
    private String domain;
    private String defaultBucket;
    private String ak;
    private String sk;
    private BucketManager bucketManager;
    private UploadManager uploadManager;

    public QiniuyunService() {
        super();
    }

    public String upload(byte[] data, String key) throws IOException {
        return upload(data, null, key);
    }

    public String upload(byte[] data, String bucket, String key) throws IOException {
        return upload(data, bucket, key, true);
    }

    public String upload(byte[] data, String key, boolean overwrite) throws IOException {
        return upload(data, null, key, overwrite);
    }

    public String upload(byte[] data, String bucket, String key, boolean overwrite) throws IOException {
        final String effBucket = (bucket != null ? bucket : defaultBucket);
        key = getValidKey(key);
        Response response = uploadManager.put(data, key, createToken(effBucket, key, overwrite));
        UploadReturn ret = response.jsonToObject(UploadReturn.class);
        // LOGGER.debug("key={}, fsize={}, hash={}, width={}, height={}", ret.key, ret.fsize, ret.hash, ret.width, ret.height);
        return String.format(Locale.ENGLISH, URL_TEMPLATE, domain, key);
    }

    public void delete(String key) throws IOException {
        delete(null, key);
    }

    public void delete(String bucket, String key) throws IOException {
        key = getValidKey(key);
        final String effBucket = (bucket != null ? bucket : defaultBucket);
        bucketManager.delete(effBucket, key);
    }

    public void deleteByUrl(String resourceUrl) throws IOException {
        deleteByUrl(null, resourceUrl);
    }

    public void deleteByUrl(String bucket, String resourceUrl) throws IOException {
        if (resourceUrl == null) {
            return;
        }

        int searchStart = resourceUrl.startsWith("https://") ? 8 : 7;
        final int index =  resourceUrl.indexOf("/", searchStart);
        final String key = resourceUrl.substring(index + 1);
        delete(bucket, key);
    }

    public Set<String> getBuckets() throws IOException {
        String[] buckets = bucketManager.buckets();
        return new HashSet<>(Arrays.asList(buckets));
    }

    public String rename(String fromKey, String toKey) throws IOException {
        return rename(null, fromKey, toKey);
    }

    public String rename(String bucket, String fromKey, String toKey) throws IOException {
        fromKey = getValidKey(fromKey);
        toKey = getValidKey(toKey);
        final String effBucket = (bucket != null ? bucket : defaultBucket);

        bucketManager.rename(effBucket, fromKey, toKey);
        return String.format(Locale.ENGLISH, URL_TEMPLATE, domain, toKey);
    }

    public boolean exists(String key) throws IOException {
        return exists(null, key);
    }

    public boolean exists(String bucket, String key) throws IOException {
        key = getValidKey(key);
        final String effBucket = (bucket != null ? bucket : defaultBucket);
        try {
            FileInfo fileInfo = bucketManager.stat(effBucket, key);
            return fileInfo != null;
        } catch (QiniuException e) {
            return false;
        }
    }

    /* -------------------------------------------------------------------------- */

    private String createToken(String bucket, String key, boolean overwrite) {
        if (overwrite) {
            return auth.uploadToken(bucket, key);
        } else {
            return auth.uploadToken(bucket);
        }
    }

    private String getValidKey(String key) {
        Assert.hasText(key);

        if (key.startsWith("/")) {
            key = key.substring(1);
        }

        return key;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(domain);
        Assert.hasText(ak);
        Assert.hasText(sk);
        Assert.hasText(defaultBucket);
        auth = Auth.create(ak, sk);
        bucketManager = new BucketManager(auth);
        uploadManager = new UploadManager();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDefaultBucket() {
        return defaultBucket;
    }

    public void setDefaultBucket(String defaultBucket) {
        this.defaultBucket = defaultBucket;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

}
