package io.github.xudaojie.jdoc.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by xdj on 2017/5/9.
 */
@Aspect
public class LogInterceptor {

    // 意思是返回值任意 参数任意
    @Before("execution(* put(..))")
    public void before() {
        System.out.println("Update Before");
    }

    @After("execution(* put(..))")
    public void after() {
        System.out.println("Update After");
    }

    @AfterReturning("execution(* put(..))")
    public void afterReturning() {
        System.out.println("Update afterReturning");
    }
}
