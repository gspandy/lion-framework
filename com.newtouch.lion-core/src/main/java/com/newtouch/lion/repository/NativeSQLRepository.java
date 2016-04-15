package com.newtouch.lion.repository;



import com.newtouch.lion.page.Page;
import com.newtouch.lion.page.Pageable;
import com.newtouch.lion.query.NamedParams;
import com.newtouch.lion.query.StringQuery;

import java.io.Serializable;
import java.util.List;

/**
 * 加入符合 JpaTemplate 习惯方法
 *
 * @since 1.0
 * @author li.yu
 */
public interface NativeSQLRepository<T, ID extends Serializable> extends Repository {

    public List<T> findBySql(String sql);

    public List<T> findBySql(StringQuery stringQuery);

    public List<T> findBySql(String sql, NamedParams params);

    public Page<T> findBySql(String sql, Pageable pageable);

    public Page<T> findBySql(StringQuery stringQuery, Pageable pageable);

    public Page<T> findBySql(String sql, NamedParams params, Pageable pageable);

    public Page<T> findBySql(String sql, String countSql, Pageable pageable);

    public Page<T> findBySql(StringQuery queryString, StringQuery queryCount, Pageable pageable);

    public Page<T> findBySql(String sql, String countSql, NamedParams params, Pageable pageable);

    public void executeUpdateBySql(String sql);

    public void executeUpdateBySql(StringQuery stringQuery);

    public void executeUpdateBySql(String sql, NamedParams params);

}
