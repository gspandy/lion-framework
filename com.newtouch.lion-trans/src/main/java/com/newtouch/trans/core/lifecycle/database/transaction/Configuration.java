package com.newtouch.trans.core.lifecycle.database.transaction;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class Configuration implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private static boolean hasTransactionThrowException = false;
	private static PlatformTransactionManager defaultTransactionManager;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Configuration.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static boolean hasTransactionThrowException() {
		return hasTransactionThrowException;
	}

	public void setHasTransactionThrowException(boolean hasTransactionThrowException) {
		Configuration.hasTransactionThrowException = hasTransactionThrowException;
	}

	public static PlatformTransactionManager getDefaultTransactionManager() {
		return defaultTransactionManager;
	}

	public void setDefaultTransactionManager(PlatformTransactionManager defaultTransactionManager) {
		Configuration.defaultTransactionManager = defaultTransactionManager;
	}

}
