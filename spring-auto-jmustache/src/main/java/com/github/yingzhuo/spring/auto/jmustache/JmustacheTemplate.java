package com.github.yingzhuo.spring.auto.jmustache;

import com.samskivert.mustache.Mustache;
import org.springframework.util.ClassUtils;

import java.io.*;

public class JmustacheTemplate {

    private String prefix = "";
    private String suffix = "";
    private String encoding = "UTF-8";

    public String render(String templateName, Object datas) {
        InputStream inputStream = ClassUtils.getDefaultClassLoader().getResourceAsStream(prefix + templateName + suffix);
        Reader reader;
        try {
            reader = new InputStreamReader(inputStream, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        String result = Mustache.compiler().compile(reader).execute(datas);
        close(reader);
        close(inputStream);
        return result;
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {closeable.close();} catch (IOException e) {}
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
