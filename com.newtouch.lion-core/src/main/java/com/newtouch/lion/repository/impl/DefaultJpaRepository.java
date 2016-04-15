package com.newtouch.lion.repository.impl;


import com.newtouch.lion.entity.metedata.jpa.DefaultJpaEntityMetadata;
import com.newtouch.lion.entity.metedata.jpa.JpaEntityMetadata;
import com.newtouch.lion.page.Page;
import com.newtouch.lion.page.Pageable;
import com.newtouch.lion.query.NamedParams;
import com.newtouch.lion.query.QueryUtils;
import com.newtouch.lion.query.StringQuery;
import com.newtouch.lion.repository.JpaRepository;
import org.hibernate.SQLQuery;
import org.hibernate.internal.QueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author li.yu
 * @since 1.0
 */
public class DefaultJpaRepository<T, PK extends Serializable> implements JpaRepository<T, PK> {

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";

    @PersistenceContext
    private EntityManager em;

    private JpaEntityMetadata<T> entityMetadata;

    public DefaultJpaRepository() {
        if (!(ParameterizedType.class.isAssignableFrom(super.getClass()
                .getGenericSuperclass().getClass())))
            return;
        Type[] actualTypeArguments = ((ParameterizedType) super.getClass()
                .getGenericSuperclass()).getActualTypeArguments();
        this.entityMetadata = new DefaultJpaEntityMetadata<T, PK>((Class<T>) actualTypeArguments[0]);
    }

    protected Class<T> getDomainClass() {
        return entityMetadata.getJavaType();
    }

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity
     * @return the saved entity
     */
    public <S extends T> void save(S entity) {
        em.persist(entity);
    }

    /**
     * Saves all given entities.
     *
     * @param entities
     * @return the saved entities
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    public <S extends T> void save(Collection<S> entities) {
        if (entities == null || entities.isEmpty()) {
            return;
        }
        for (S entity : entities) {
            save(entity);
        }
    }



    public <S extends T> void update(S entity) {
        em.merge(entity);
    }

    public <S extends T> void update(Collection<S> entities) {
        if (entities == null || entities.isEmpty()) {
            return;
        }
        for(S entity : entities) {
            update(entity);
        }
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public T get(PK id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        Class<T> domainType = getDomainClass();
        return em.find(domainType, id);
    }

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public boolean exists(PK id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        return get(id) != null;
    }

    /**
     * Returns all instances of the type.
     *
     * @return altities
     */
    public List<T> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
        return em.createQuery(query).getResultList();
    }

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    public long count() {
        String countQueryString = QueryUtils.getCountQueryString(entityMetadata.getEntityName());
        return em.createQuery(countQueryString, Long.class).getSingleResult();
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    public void delete(PK id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);

        T entity = get(id);

        delete(entity);
    }

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null!");
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    public void delete(Collection<? extends T> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");

        for (T entity : entities) {
            delete(entity);
        }
    }

    /**
     * Deletes all entities managed by the repository.
     */
    public void deleteAll() {
        for (T element : findAll()) {
            delete(element);
        }
    }

    private void setQueryParams(Query query, NamedParams params) {
        if (params.isEmpty()) {
            return;
        }

        Map<String, Object> parameters = params.getParameters();
        for (String paramName : parameters.keySet()) {
            query.setParameter(paramName, parameters.get(paramName));
        }

    }

    /**
     * 返回对象为Object类型，不指定具体的对象
     * @param queryString
     * @return
     */
    public List<Object> findObject(String queryString) {
        return findObject(queryString, NamedParams.newParams());
    }
    /**
     * 返回对象为Object类型，不指定具体的对象
     * @param stringQuery
     * @return
     */
    public List<Object> findObject(StringQuery stringQuery){
        Assert.notNull(stringQuery, "StringQuery must not be null!");

        String query = stringQuery.getQuery();
        NamedParams params = stringQuery.getParams();
        return findObject(query, params);
    }



    public List<T> find(String queryString) {
        return find(queryString, NamedParams.newParams());
    }

    public List<T> find(StringQuery stringQuery) {
        Assert.notNull(stringQuery, "StringQuery must not be null!");

        String query = stringQuery.getQuery();
        NamedParams params = stringQuery.getParams();
        return find(query, params);
    }

    public <S> List<S>find(StringQuery stringQuery, Class<S> transformerClass) {
        Assert.notNull(stringQuery, "StringQuery must not be null!");

        String query = stringQuery.getQuery();
        NamedParams params = stringQuery.getParams();
        return find(query, params, transformerClass);
    }

    public List<Object> findObject(String queryString, NamedParams params) {
        Assert.notNull(queryString, "Query must not be null!");
        Assert.notNull(params, "NamedParams must not be null!");

        Query query = em.createQuery(queryString);
        setQueryParams(query, params);
        return query.getResultList();
    }

    public List<T> find(String queryString, NamedParams params) {
        Assert.notNull(queryString, "Query must not be null!");
        Assert.notNull(params, "NamedParams must not be null!");

        Query query = em.createQuery(queryString);
        setQueryParams(query, params);
        return query.getResultList();
    }

    public <S> List<S> find(String queryString, NamedParams params, Class<S> transformerClass) {
        Assert.notNull(queryString, "Query must not be null!");
        Assert.notNull(params, "NamedParams must not be null!");
        Assert.notNull(transformerClass, "Transformer Class must not be null");

        Query query = em.createQuery(queryString);
        setQueryParams(query, params);
        return query.unwrap(QueryImpl.class).setResultTransformer(Transformers.aliasToBean(transformerClass)).list();
    }


    public Page<T> find(String queryString, Pageable pageable) {
        return find(queryString, NamedParams.newParams(), pageable);
    }

    public Page<T> find (StringQuery stringQuery, Pageable pageable) {
        Assert.notNull(stringQuery, "StringQuery must not be null");

        String query = stringQuery.getQuery();
        NamedParams params = stringQuery.getParams();
        return find(query, params, pageable);
    }

    public Page<T> find(String queryString, NamedParams params, Pageable pageable) {
        Assert.hasText(queryString, "Query must has text!");

        String queryCount = QueryUtils.genCountQueryString(queryString);
        return find(queryString, queryCount, params, pageable);
    }

    public Page<T> find(String queryString, String queryCount, Pageable pageable) {
        return find(queryString, queryCount, NamedParams.newParams(), pageable);
    }

    public Page<T> find(StringQuery queryString, StringQuery queryCount, Pageable pageable) {
        Assert.notNull(queryString, "StringQuery must not be null!");
        Assert.notNull(queryCount, "StringQuery count must not be null!");

        String query = queryString.getQuery();
        String count = queryCount.getQuery();
        NamedParams params = queryString.getParams();
        return find(query, count, params, pageable);
    }

    public Page<T> find(String queryString, String queryCount, NamedParams params, Pageable pageable) {
        Assert.hasText(queryString, "Query must has text!");
        Assert.hasText(queryCount, "Query count must has text!");
        Assert.notNull(params, "QueryParams must not be null!");
        Assert.notNull(pageable, "PageRequest must not be null!");

        Query query = em.createQuery(queryString);

        setQueryParams(query, params);
        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult(pageable.getOffset());

        List<T> resultList = query.getResultList();

        Query countQuery = em.createQuery(queryCount);
        setQueryParams(countQuery, params);
        Long total = (Long) countQuery.getSingleResult();

        Page<T> page = new Page<T>(resultList, pageable,total);
        return page;
    }

    public void executeUpdate(String jpql) {
        executeUpdate(jpql, NamedParams.newParams());
    }

    public void executeUpdate(StringQuery stringQuery) {
        Assert.notNull(stringQuery, "StringQuery myst not be null");
        executeUpdate(stringQuery.getQuery(), stringQuery.getParams());
    }

    public void executeUpdate(String jpql, NamedParams params) {
        Assert.hasText(jpql, "JPQL must has text!");
        Assert.notNull(params, "Query Params must not be null!");

        Query query = em.createQuery(jpql);
        setQueryParams(query, params);
        query.executeUpdate();
    }


    public List<T> findBySql(String sql) {
        return findBySql(sql, NamedParams.newParams());
    }

    public List findObjectBySql(StringQuery stringQuery) {
        Assert.notNull(stringQuery, "StringQuery must not be null!");

        String query = stringQuery.getQuery();
        NamedParams params = stringQuery.getParams();
        return findBySql(query, params);
    }

    public List findBySql(StringQuery stringQuery, Class transformerClass) {
        Assert.notNull(stringQuery, "StringQuery must not be null!");

        String sql = stringQuery.getQuery();
        NamedParams params = stringQuery.getParams();

        Assert.notNull(params, "NamedParams must not be null!");

        Query query = em.createNativeQuery(sql);
        setQueryParams(query, params);
        return query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(transformerClass)).list();
    }


    public List<T> findBySql(StringQuery stringQuery) {
        Assert.notNull(stringQuery, "StringQuery must not be null!");

        String query = stringQuery.getQuery();
        NamedParams params = stringQuery.getParams();
        return findBySql(query, params);
    }

    public List findObjectBySql(String sql, NamedParams params) {
        Assert.notNull(sql, "Query must not be null!");
        Assert.notNull(params, "NamedParams must not be null!");

        Query query = em.createNativeQuery(sql);
        setQueryParams(query, params);
        return query.getResultList();
    }

    public List<T> findBySql(String sql, NamedParams params) {
        Assert.notNull(sql, "Query must not be null!");
        Assert.notNull(params, "NamedParams must not be null!");

        Query query = em.createNativeQuery(sql);
        setQueryParams(query, params);
        return query.getResultList();
    }

    public Page<T> findBySql(String sql, Pageable pageable) {
        return findBySql(sql, NamedParams.newParams(), pageable);
    }

    public Page<T> findBySql(StringQuery stringQuery, Pageable pageable) {
        Assert.notNull(stringQuery, "StringQuery must not be null!");

        String query = stringQuery.getQuery();
        NamedParams params = stringQuery.getParams();
        return findBySql(query, params, pageable);
    }

    public Page<T> findBySql(String sql, NamedParams params, Pageable pageable) {
        Assert.hasText(sql, "Sql must has text!");

        String queryCount = QueryUtils.genCountQueryString(sql);
        return findBySql(sql, queryCount, params, pageable);
    }

    public Page<T> findBySql(String sql, String countSql, Pageable pageable) {
        return findBySql(sql, countSql, NamedParams.newParams(), pageable);
    }

    public Page<T> findBySql(StringQuery queryString, StringQuery queryCount, Pageable pageable) {
        Assert.notNull(queryString, "StringQuery must not be null!");
        Assert.notNull(queryCount, "StringQuery count must not be null!");

        String query = queryString.getQuery();
        String count = queryCount.getQuery();
        NamedParams params = queryString.getParams();
        return findBySql(query, count, params, pageable);
    }

    public Page<T> findBySql(String sql, String countSql, NamedParams params, Pageable pageable) {
        Assert.hasText(sql, "Query must has text!");
        Assert.hasText(countSql, "Count sql must has text!");
        Assert.notNull(params, "QueryParams must not be null!");
        Assert.notNull(pageable, "PageRequest must not be null!");

        Query query = em.createNativeQuery(sql);

        setQueryParams(query, params);
        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult(pageable.getOffset());

        List<T> resultList = query.getResultList();

        Query countQuery = em.createNativeQuery(countSql);
        setQueryParams(countQuery, params);
        Long total = Long.valueOf(countQuery.getSingleResult().toString());

        Page<T> page = new Page<T>(resultList, pageable,total);
        return page;
    }

    public void executeUpdateBySql(String sql) {
        executeUpdateBySql(sql, NamedParams.newParams());
    }

    public void executeUpdateBySql(StringQuery stringQuery) {
        Assert.notNull(stringQuery, "StringQuery must not be null!");

        String query = stringQuery.getQuery();
        NamedParams params = stringQuery.getParams();

        executeUpdateBySql(query, params);
    }

    public void executeUpdateBySql(String sql, NamedParams params) {
        Assert.notNull(params, "Query params must not be null!");
        Assert.hasText(sql, "Sql must has text!");

        Query query = em.createNativeQuery(sql);
        setQueryParams(query, params);
        query.executeUpdate();
    }
}
