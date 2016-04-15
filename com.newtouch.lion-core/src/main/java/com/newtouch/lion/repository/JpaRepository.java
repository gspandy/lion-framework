package com.newtouch.lion.repository;



import java.io.Serializable;

/**
 * @author li.yu
 * @since 1.0
 */
public interface JpaRepository<T, PK extends Serializable> extends CrudRepository<T, PK> {

}
