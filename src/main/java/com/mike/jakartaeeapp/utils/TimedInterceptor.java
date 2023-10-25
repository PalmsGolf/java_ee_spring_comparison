package com.mike.jakartaeeapp.utils;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@Timed
public class TimedInterceptor {

    @AroundInvoke
    public Object timeInvocation(InvocationContext ctx) throws Exception {
        //Context in which the interceptor execution is performed
        final Object result = ctx.proceed();
        final long start = System.currentTimeMillis();

        return result.toString() + "[Request is calculated in " + (System.currentTimeMillis() - start) + "ms]";
    }
}
