package com.github.yingzhuo.spring.auto.jmustache;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(JmustacheTemplateConfiguration.class)
public @interface EnableJmustacheTemplate {
}
