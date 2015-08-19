package test;

import com.github.yingzhuo.spring.auto.datasource.DataSourceSwitchAdviceSupport;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceSwitchAdvice extends DataSourceSwitchAdviceSupport {

    @Override
    @Around("execution(public * com.mycompany.myapp.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return super.around(joinPoint);
    }
}
