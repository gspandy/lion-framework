/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: BaseDaoImpl.java 9552 2012-7-8 上午01:18:39 WangLijun$
 */
package com.newtouch.lion.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.dao.BaseDao;
import com.newtouch.lion.model.AuditEntity;
import com.newtouch.lion.model.BaseEntity;
import com.newtouch.lion.page.PageResult;
import com.newtouch.lion.session.AppContext;

/**
 * <p>
 * Title:数据存储类，用于处理数据
 * </p>
 * <p>
 * Description:该类支持SQL、HSQL等方式进行查询，用保存、查询、更新、分页等功能
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */

public abstract class BaseDaoImpl<T extends BaseEntity<PK>, PK> implements BaseDao<T, PK> {

	private static final long serialVersionUID = 840623395509475191L;
	/**日志*/
	protected final Logger log = LoggerFactory.getLogger(super.getClass());
	/**entityManager*/
	private EntityManager  entityManager;
	
	@PersistenceContext(unitName="entityUnit")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**实体类*/
	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		if (!(ParameterizedType.class.isAssignableFrom(super.getClass()
				.getGenericSuperclass().getClass())))
			return;
		Type[] actualTypeArguments = ((ParameterizedType) super.getClass()
				.getGenericSuperclass()).getActualTypeArguments();
		this.entityClass = (Class<T>) actualTypeArguments[0];
	}

	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	private void addAuditInfo(T obj) {
		if (obj instanceof AuditEntity) {
			AuditEntity<PK> obj1 = (AuditEntity<PK>) obj;
			if ((obj1.getId() == null)) {
				obj1.setCreatedById(AppContext.getUserInfo().getId());
				if (obj1.getCreatedDate() == null) {
					obj1.setCreatedDate(Calendar.getInstance().getTime());
				}
			}
			obj1.setUpdatedById(AppContext.getUserInfo().getId());
			obj1.setUpdatedDate(Calendar.getInstance().getTime());
		}
	}

	/*
	 * <p>Title: findById</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see com.lion.framework.dao.BaseDao#findById(java.lang.Long)
	 */


	public T findById(PK id) {
		return (T) this.entityManager.find(entityClass, id);
	}

	/*
	 * <p>Title: findAll</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.lion.framework.dao.BaseDao#findAll()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#getById(java.lang.Long)
	 */
	@Override
	public T getById(PK id) {
		return  this.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#findByIdNoWaitLock(java.lang.Long)
	 */

	public T findByIdNoWaitLock(PK id) {
		return ((T) this.entityManager.find(this.entityClass, id,LockModeType.PESSIMISTIC_READ));
	}

	/***
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
	 
		StringBuilder sb=new StringBuilder();
		sb.append("from ");
		sb.append(this.entityClass.getSimpleName());
		Query  query=this.entityManager.createQuery(sb.toString());
		return query.getResultList();
	}

	public void eagerLoad(T obj) {
		 this.entityManager.persist(obj);
	}

	public void evict(T obj) {
		this.entityManager.merge(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#find(com.lion.framework.model.
	 * BaseEntity )
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(T obj) {
		return (List<T>) this.entityManager.find(this.entityClass,obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#updateHQL(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public int updateHQL(String hql, Map<String, ?> params) {
	 
		Query query = this.entityManager.createQuery(hql);
		this.setParams(query, params);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(String queryName, Map<String, ?> params) {
	 
		Query query =this.entityManager.createNamedQuery(queryName,this.entityClass);
		this.setParams(query, params);
		return query.getResultList();
	}

	/*
	 * <p>Title: flush</p> <p>Description: </p>
	 * 
	 * @see com.lion.framework.dao.BaseDao#flush()
	 */

	public void flush() {
		this.entityManager.flush();
	}

	/*
	 * <p>Title: clear</p> <p>Description: </p>
	 * 
	 * @see com.lion.framework.dao.BaseDao#clear()
	 */

	public void clear() {
		this.entityManager.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#lock(com.lion.framework.model.
	 * BaseEntity , org.hibernate.LockMode)
	 */

	@Override
	public void lock(T entity, LockModeType lockModeType) {
		this.entityManager.lock(entity, lockModeType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#nativeQueryCountSQL(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public Long nativeQueryCountSQL(String countSql, Map<String, ?> params) {

		Query  query=this.entityManager.createNativeQuery(countSql);
		for(Entry<String,?> entry:params.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return (Long) query.getSingleResult();
	}

	 

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#nativeQuerySQL(java.lang.String,
	 * java.lang.String, java.util.Map, int, int)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageResult<T> nativeQuerySQL(String sql, String countSql,
			Map<String, ?> params, int offsetIndex, int pageSize) {
		PageResult<T> pr = new PageResult<T>();

		pr.setCurrentIndex(offsetIndex);
		pr.setPageSize(pageSize);

		if (countSql != null) {
			pr.setTotalCount(nativeQueryCountSQL(countSql, params));
		}

		if (pageSize != 0) {
			pr.setTotalPage((pr.getTotalCount() + pageSize - 1) / pageSize);
			pr.setCurrentPage((offsetIndex + pageSize) / pageSize);
		}
		Query query = this.entityManager.createQuery(sql);
		this.setParams(query, params);
		query.setFirstResult(offsetIndex);
		query.setMaxResults(pageSize);
		List content = query.getResultList();
		pr.setContent(content);
		return pr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#nativeUpdateSQL(java.lang.String,
	 * java.util.Map)
	 */

	@Override
	public int nativeUpdateSQL(String sql, Map<String, ?> params) {
		Query query = this.entityManager.createNativeQuery(sql);
	
		return Integer.valueOf(query.executeUpdate());
	}

	@SuppressWarnings("unchecked")
	public List<T> query(String hql, Map<String, ?> params) {

		Query query = this.entityManager.createQuery(hql);
		this.setParams(query,params);
		return query.getResultList();
	}

	/*
	 * <p>Title: query</p> <p>Description: </p>
	 * 
	 * @param hql
	 * 
	 * @param countHql
	 * 
	 * @param params
	 * 
	 * @param offsetIndex
	 * 
	 * @param pageSize
	 * 
	 * @return
	 * 
	 * @see com.lion.framework.dao.BaseDao#query(java.lang.String,
	 * java.lang.String, java.util.Map, int, int)
	 */

	@SuppressWarnings({ "unchecked" })
	public PageResult<T> query(String hql, String countHql,
			Map<String, ?> params, int offsetIndex, int pageSize) {
		PageResult<T> pageResult = new PageResult<T>();

		pageResult.setCurrentIndex(offsetIndex);
		pageResult.setPageSize(pageSize);

		if (countHql != null) {

			pageResult.setTotalCount(this.queryCount(countHql, params));
		}

		if (pageSize != 0) {
			pageResult.setTotalPage((pageResult.getTotalCount() + pageSize - 1)
					/ pageSize);
			pageResult.setCurrentPage((offsetIndex + pageSize) / pageSize);
		}
		Query query =entityManager.createQuery(hql);
		this.setParams(query, params);
		query.setFirstResult(offsetIndex);
		query.setMaxResults(pageSize);
		List<T> content = query.getResultList();
		pageResult.setContent(content);
		return pageResult;
	}

	/*
	 * <p>Title: queryCount</p> <p>Description: </p>
	 * 
	 * @param countHql
	 * 
	 * @param params
	 * 
	 * @return
	 * 
	 * @see com.lion.framework.dao.BaseDao#queryCount(java.lang.String,
	 * java.util.Map)
	 */

	public Long queryCount(final String countHql, final Map<String, ?> params) {
		Query query = entityManager.createQuery(countHql);
		this.setParams(query, params);
		return (Long) query.getSingleResult();

	}

	/**
	 * <p>
	 * Title: save
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param obj
	 * 

	 */

	public void save(T obj) {
		this.addAuditInfo(obj);
	    this.entityManager.persist(obj);
		obj.getId();
	}

	/**
	 * <p>
	 * Title: saveObjects
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param objs
	 * 

	 */

	public void saveObjects(Collection<T> objs) {
		for (T obj : objs) {
			addAuditInfo(obj);
		}
		this.entityManager.persist(objs);
	}


	public void mergeObject(T obj) {
		this.entityManager.merge(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#remove(com.lion.framework.model.
	 * BaseEntity)
	 */
	@Override
	public void remove(T obj) {
		this.entityManager.remove(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#removeObjects(java.util.Collection)
	 */
	@Override
	public void removeObjects(Collection<T> objs) {
		if(CollectionUtils.isEmpty(objs)){
			return;
		}
		for(T t:objs){
			this.remove(t);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#findEntityAll(java.lang.Class)
	 */
	@Override
	public List<T> findEntityAll(Class<T> clazz) {
		// return this.getCurrentSession().l
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#updateByNamedQuery(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public int updateByNamedQuery(String queryName, Map<String, ?> params) {
		Query query = this.entityManager.createNamedQuery(queryName,this.entityClass);
		this.setParams(query, params);
		return query.executeUpdate();
	}
	
	private void setParams(Query query,Map<String,?> params){
		if(CollectionUtils.isEmpty(params)){
			return;
		}
		for(Entry<String,?> entry:params.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#update(com.lion.framework.model.
	 * BaseEntity)
	 */
	@Override
	public void update(T obj) {
		this.addAuditInfo(obj);
		this.update(obj, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#update(com.lion.framework.model.
	 * BaseEntity, java.util.Date)
	 */
	@Override
	public void update(T obj, Date updateDate) {
		if(obj instanceof AuditEntity){
			this.updateEntity((AuditEntity<PK>) obj, updateDate);
		}else{
			this.mergeObject(obj);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#updateObjects(com.lion.framework
	 * .model.BaseEntity)
	 */
	@Override
	public void updateObjects(Collection<T> objs) {
		this.updateObjects(objs, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#updateObjects(com.lion.framework
	 * .model.BaseEntity, java.util.Date)
	 */
	@Override
	public void updateObjects(Collection<T> objs, Date updateDate) {
		for (Iterator<T> localIterator = objs.iterator(); localIterator
				.hasNext();) {
			T obj = localIterator.next();
			if(obj instanceof AuditEntity){
				this.updateEntity((AuditEntity<PK>) obj, updateDate);
			}else{
				this.mergeObject(obj);
			}
		}
	}

	/***
	 * 
	 * @param  obj
	 * @param updateDate
	 */
	private void updateEntity(AuditEntity<PK> obj, Date updateDate) {
		if (updateDate == null) {
			obj.setUpdatedDate(Calendar.getInstance().getTime());
		} else {
			obj.setUpdatedDate(updateDate);
		}
		this.entityManager.merge(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#getsSequenceNextval(java.lang.String)
	 */
	public long getsSequenceNextval(String sequenceName) {		 
		StringBuilder sb=new StringBuilder();
		sb.append("select ");
		sb.append(sequenceName);
		sb.append(".Nextval as seq from dual");
		return (Long) this.entityManager.createNativeQuery(sb.toString()).getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#getTables(java.lang.String,
	 * java.lang.String)
	 */
	public List<String> getTables(String schemaPattern, String tableNamePattern) {
		List<String> tables = new ArrayList<String>();

		// try {
		// Connection con = super.getSession().
		// //DatabaseMetaData meta = con.getMetaData();
		// ResultSet rs = meta.getTables(null, schemaPattern,
		// tableNamePattern, new String[] { "TABLE" });
		// while (rs.next())
		// tables.add(rs.getString(3));
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

		return tables;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lion.framework.dao.BaseDao#getColumns(java.lang.String)
	 */
	@Override
	public List<String> getColumns(String tableName) {
		List<String> columns = new ArrayList<String>();
		// String sql = "SELECT * FROM " + tableName + " WHERE 1 != 1";
		//
		// try {
		//
		// SQLQuery rs = super.getSession().createSQLQuery(sql);
		// ResultSetMetaData meta = rs.
		// int cols = meta.getColumnCount();
		// for (int i = 1; i <= cols; ++i)
		// columns.add(meta.getColumnName(i));
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

		return columns;
	}

}
