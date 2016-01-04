package com.newtouch.trans.core.lifecycle.database.transaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * 事务定义配置
 * 
 * @author dongfeng.zhang
 * @date 2013-07-09
 */
public class CustomTransactionDefinition implements TransactionDefinition {
	private String name = null;
	private int propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRED;
	private int isolationLevel = TransactionDefinition.ISOLATION_DEFAULT;
	private int timeout = TransactionDefinition.TIMEOUT_DEFAULT;
	private boolean readOnly = false;

	private Class<? extends Throwable>[] rollbackForClasses;
	private Class<? extends Throwable>[] noRollbackForClasses;

	private PlatformTransactionManager transactionManager;

	public CustomTransactionDefinition() {
		super();
	}

	public CustomTransactionDefinition(String name, int propagationBehavior, int isolationLevel, int timeout, boolean readOnly,
			Class<? extends Throwable>[] rollbackForClasses, Class<? extends Throwable>[] noRollbackForClasses,
			PlatformTransactionManager transactionManager) {
		super();
		this.name = name;
		this.propagationBehavior = propagationBehavior;
		this.isolationLevel = isolationLevel;
		this.timeout = timeout;
		this.readOnly = readOnly;
		this.rollbackForClasses = rollbackForClasses;
		this.noRollbackForClasses = noRollbackForClasses;
		this.transactionManager = transactionManager;
	}

	/**
	 * 用于监控事务的事务名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 事务传播行为
	 * 
	 * <pre>
	 * 	   	  PROPAGATION_MANDATORY:方法必须在一个现存的事务中进行，否则丢出异常 
	 *        PROPAGATION_NESTED:在一个嵌入的事务中进行 
	 *        PROPAGATION_NEVER:不应在事务中进行，如果有则丢异常 
	 *        PROPAGATION_NOT_SUPPORTED:不应再事务中进行，如果有就暂停现存的事务 
	 *        PROPAGATION_REQUIRED:支持现在的事务，如果没有就建立一个新的事务 
	 *        PROPAGATION_REQUIRES_NEW:建立一个新的事务，如果现存一个事务就暂停它 
	 *        PROPAGATION_SUPPORTS:支持现在的事务，如果没有就以非事务的方式执行
	 * </pre>
	 */
	public int getPropagationBehavior() {
		return propagationBehavior;
	}

	public void setPropagationBehavior(int propagationBehavior) {
		this.propagationBehavior = propagationBehavior;
	}

	/**
	 * <pre>
	 * 	 	  ISOLATION_DEFAULT:使用底层数据库预设的隔离层级 
	 *        ISOLATION_READ_COMMITTED:运行事务读取其他事务已经提交的数据字段，可以防止脏读问题 
	 *        ISOLATION_READ_UNCOMMITTED:运行事务读取其他并行事务还没有提交的数据，会发生脏读、非重复读、幻象读等问题 
	 *        ISOLATION_REPEATABLE_READ:要求多次读取的数据必须相同，除非事务本身更新数据，可以防止脏读、非重复读等问题 
	 *        ISOLATION_SERIALIZABLE:完整的隔离层级，防止所有问题，会锁定数据对应的表，有效率问题
	 * </pre>
	 * 
	 */
	public int getIsolationLevel() {
		return isolationLevel;
	}

	public void setIsolationLevel(int isolationLevel) {
		this.isolationLevel = isolationLevel;
	}

	/**
	 * 超时时间，缺省-1
	 */
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * 是否只读事务
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public Class<? extends Throwable>[] getRollbackForClasses() {
		return rollbackForClasses;
	}

	public void setRollbackForClasses(Class<? extends Throwable>[] rollbackForClasses) {
		this.rollbackForClasses = rollbackForClasses;
	}

	public Class<? extends Throwable>[] getNoRollbackForClasses() {
		return noRollbackForClasses;
	}

	public void setNoRollbackForClasses(Class<? extends Throwable>[] noRollbackForClasses) {
		this.noRollbackForClasses = noRollbackForClasses;
	}

}
