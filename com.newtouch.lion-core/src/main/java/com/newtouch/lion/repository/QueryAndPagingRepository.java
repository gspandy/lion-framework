package com.newtouch.lion.repository;



import com.newtouch.lion.page.Page;
import com.newtouch.lion.page.Pageable;
import com.newtouch.lion.query.NamedParams;
import com.newtouch.lion.query.StringQuery;

import java.io.Serializable;
import java.util.List;

/**
 * @author li.yu
 * @since 1.0
 */
public interface QueryAndPagingRepository<T, PK extends Serializable> extends CrudRepository<T, PK> {

    public List<T> find(String queryString);

    public List<T> find(StringQuery stringQuery);

    public List<T> find(String queryString, NamedParams params);

    public Page<T> find(String queryString, Pageable pageable);

    public Page<T> find(StringQuery stringQuery, Pageable pageable);

    public Page<T> find(String queryString, NamedParams params, Pageable pageable);

    public Page<T> find(String queryString, String queryCount, Pageable pageable);

    public Page<T> find(StringQuery queryString, StringQuery queryCount, Pageable pageable);

    public Page<T> find(String queryString, String queryCount, NamedParams params, Pageable pageable);





}
