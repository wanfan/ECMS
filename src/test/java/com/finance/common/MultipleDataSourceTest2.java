package com.finance.common;

import com.finance.dao.MySqlMapper;
import com.finance.dao.MySqlMapper2;
import com.finance.datasource.DynamicDataSourceContextHolder;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by zt on 2016/10/5.
 */
@Component
@Aspect
public class MultipleDataSourceTest2 {

    @Around("execution(* com.finance.service..*.*(..))")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        if (jp.getTarget() instanceof MySqlMapper) {
            DynamicDataSourceContextHolder.setCustomerType("master");
        } else if (jp.getTarget() instanceof MySqlMapper2) {
            DynamicDataSourceContextHolder.setCustomerType("slave");
        }
        return jp.proceed();
    }
}
