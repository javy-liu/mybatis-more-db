package org.oyach.mybatis.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 支持UseDataSource注解
 *
 * @author liuzhenyuan
 * @version Last modified 15/3/11
 * @since 0.0.1
 */
public class UseDataSourceSupport implements MethodInterceptor {
    private TransactionInterceptor advice;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TransactionAttribute transactionAttribute = advice.getTransactionAttributeSource().getTransactionAttribute(invocation.getMethod(), invocation.getClass());
        // 根据 readyOnly属性设置 自动选择
        TransactionSynchronizationManager.setCurrentTransactionReadOnly(transactionAttribute.isReadOnly());
        return invocation.proceed();
    }

    public TransactionInterceptor getAdvice() {
        return advice;
    }

    public void setAdvice(TransactionInterceptor advice) {
        this.advice = advice;
    }
}
