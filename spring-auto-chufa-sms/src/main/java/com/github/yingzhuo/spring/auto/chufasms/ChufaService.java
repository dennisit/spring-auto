package com.github.yingzhuo.spring.auto.chufasms;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public final class ChufaService {

    final private Map<String, String> errorMsgMap;
    private String username;
    private String password;

    public ChufaService() {
        Map<String, String> map = new HashMap<>();
        map.put("-1", "提交接口错误");
        map.put("-3", "用户名或密码错误");
        map.put("-4", "短信内容和备案的模板不一致");
        map.put("-5", "签名不正确");
        map.put("-7", "余额不足");
        map.put("-8", "通道错误");
        map.put("-9", "无效号码");
        map.put("-10", "签名内容不符合长度");
        map.put("-11", "用户有效期过期");
        map.put("-12", "黑名单");
        map.put("-16", "请求过于频繁");
        map.put("-17", "非法IP地址");
        errorMsgMap = Collections.unmodifiableMap(map);
    }

    public void send(String phoneNumber, String message) throws ChufaException {
        String str = "";
        try {
            // 创建HttpClient实例
            HttpClient httpclient = new DefaultHttpClient();

            //构造一个post对象
            HttpPost httpPost = new HttpPost("http://h.1069106.com:1210/Services/MsgSend.asmx/SendMsg");
            //添加所需要的post内容
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("userCode", username));
            nvps.add(new BasicNameValuePair("userPass", password));
            nvps.add(new BasicNameValuePair("DesNo", phoneNumber));
            nvps.add(new BasicNameValuePair("Msg", message));
            nvps.add(new BasicNameValuePair("Channel", "0"));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instreams = entity.getContent();
                str = convertStreamToString(instreams);
            }

            Document doc = DocumentHelper.parseText(str); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            String root = rootElt.getText();
            if (root == null || "".equals(root)) {
                throw new ChufaException((String) null);
            }

            if (root.trim().startsWith("-")) {
                throw new ChufaException(errorMsgMap.get(root));
            }

        } catch (Exception e) {
            throw new ChufaException(e);
        }
    }


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException ignore) {
            // NOP
        } finally {
            try {
                is.close();
            } catch (IOException ignored) {
            }
        }
        return sb.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
