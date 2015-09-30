package com.github.yingzhuo.spring.auto.chanzor;


import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChanzorService {

    private static final String URL = "http://sms.chanzor.com:8001/sms.aspx";

    private String account;
    private String password;

    /**
     * 发送短信息
     *
     * @param phoneNumber 电话号码
     * @param content     短信内容
     */
    public void send(String phoneNumber, String content) {
        List<String> phoneNumbers = new ArrayList<>(1);
        phoneNumbers.add(phoneNumber);
        send(phoneNumbers, content);
    }

    /**
     * 发送短消息
     *
     * @param phoneNumbers 电话号码
     * @param content      短信内容
     */
    public void send(Collection<String> phoneNumbers, String content) {

        try {
            Document document = Jsoup.connect(URL)
                    .data("action", "send")
                    .data("account", account)
                    .data("password", password)
                    .data("mobile", StringUtils.join(phoneNumbers, ','))
                    .data("content", new String(content.getBytes(), "UTF-8"))
                    .post();

            if (!"success".equalsIgnoreCase(document.select("returnstatus").text())) {
                final String message = document.select("message").text();
                throw new IOException(message);
            }

        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 查看剩余短信额度
     *
     * @return 剩余短信额度
     */
    public long overage() {
        try {
            Document document = Jsoup.connect(URL)
                    .data("action", "overage")
                    .data("account", account)
                    .data("password", password)
                    .post();

            if (!"success".equalsIgnoreCase(document.select("returnstatus").text())) {
                final String message = "无法获取剩余短信额度";
                throw new IOException(message);
            } else {
                return Long.parseLong(document.select("overage").text());
            }

        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
