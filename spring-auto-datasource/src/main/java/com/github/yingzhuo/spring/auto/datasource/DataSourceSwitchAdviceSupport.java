package com.github.yingzhuo.spring.auto.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public abstract class DataSourceSwitchAdviceSupport {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceSwitchAdviceSupport.class);

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        DataSourceConfig annotation = findAnnotation(joinPoint, DataSourceConfig.class);
        if (annotation != null) {
            LOGGER.debug("数据源设置: name={}", annotation.value());
            DataSourceRemoter remoter = DataSourceRemoter.getInstance();
            remoter.set(annotation.value());
        }

        return joinPoint.proceed();
    }

    /*
     * 反射获得标注 查找顺序如下
     *   1. 实现类上方法
     *   2. 实现类类型
     *   3. 接口方法上
     *   4. 接口类型
     */
    private <T extends Annotation> T findAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationType) throws Throwable {
        final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        final Class<?> implCls = joinPoint.getTarget().getClass();

        T anno;

        Method implMethod = implCls.getMethod(method.getName(), method.getParameterTypes());
        anno = implMethod.getDeclaredAnnotation(annotationType);
        if (anno != null) {
            return anno;
        }

        anno = implCls.getAnnotation(annotationType);
        if (anno != null) {
            return anno;
        }

        Class<?>[] interfaces = implCls.getInterfaces();
        for (Class<?> interfaceCls : interfaces) {
            Method ifaceMethod;

            try {
                ifaceMethod = interfaceCls.getMethod(method.getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                continue;
            } catch (SecurityException e) {
                continue;
            }

            anno = ifaceMethod.getAnnotation(annotationType);
            if (anno != null) {
                return anno;
            }

            anno = interfaceCls.getAnnotation(annotationType);
            if (anno != null) {
                return anno;
            }
        }

        return null;
    }
}
