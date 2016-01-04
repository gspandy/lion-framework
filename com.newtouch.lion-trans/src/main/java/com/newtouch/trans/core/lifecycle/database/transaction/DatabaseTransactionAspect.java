package com.newtouch.trans.core.lifecycle.database.transaction;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * 事务拦截器
 * 
 * @author dongfeng.zhang
 * @author 阳葵 添加事务回滚时，日志当中添加事务回滚的方法ID
 * 
 */
@Aspect
public class DatabaseTransactionAspect {
	private Logger logger = LoggerFactory.getLogger(DatabaseTransactionAspect.class);

	@Around("execution(* com.cpic.cmp.server.process.processunit..*.*(..)) && @annotation(com.cpic.cmp.server.core.lifecycle.database.transaction.EupTransactional)")
	public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		EupTransactional transactionalAnnotation = method.getAnnotation(EupTransactional.class);
		if (transactionalAnnotation == null) {
			return joinPoint.proceed();
		}

		final String value = transactionalAnnotation.value();
		PlatformTransactionManager transactionManager = Configuration.getDefaultTransactionManager();

		// 设置自定义的事务管理器
		String customTransactionManagerId = transactionalAnnotation.transactionManager();
		if (customTransactionManagerId != null && customTransactionManagerId.length() > 0) {
			transactionManager = (PlatformTransactionManager) Configuration.getBean(customTransactionManagerId);
		}

		final CustomTransactionDefinition transactionDefinition = new CustomTransactionDefinition(transactionalAnnotation.value(),
				transactionalAnnotation.propagation().value(), transactionalAnnotation.isolation().value(), transactionalAnnotation.timeout(),
				transactionalAnnotation.readOnly(), transactionalAnnotation.rollbackFor(), transactionalAnnotation.noRollbackFor(),
				transactionManager);

		TransactionTemplate txTemplate = new TransactionTemplate(transactionDefinition.getTransactionManager(), transactionDefinition);

		Object result = txTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus status) {
				Object val = null;
				try {
					val = joinPoint.proceed();
				} catch (Throwable e) {
					boolean rollback = true;
					Class<? extends Throwable>[] rollbackFors = transactionDefinition.getRollbackForClasses();
					Class<? extends Throwable>[] noRollbackFors = transactionDefinition.getNoRollbackForClasses();

					if (noRollbackFors != null) {
						for (int i = 0; i < noRollbackFors.length; i++) {
							if (e.getClass().isAssignableFrom(noRollbackFors[i])) {
								rollback = false;
								break;
							}
						}
					}
					if (rollbackFors != null) {
						for (int i = 0; i < rollbackFors.length; i++) {
							if (e.getClass().isAssignableFrom(rollbackFors[i])) {
								rollback = true;
								break;
							}
						}
					}

					if (rollback) {
						logger.error("database transaction error , transaction is rollback!,transactionId:{}",value, e);
						status.setRollbackOnly();
					}
					if(e instanceof RuntimeException ){
						throw (RuntimeException)e;
					}else{
						throw new RuntimeException(e);
					}
				}
				return val;
			}
		});
		return result;
	}

}
