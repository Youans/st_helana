/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.dao;

import com.shs.utils.database.Persistable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Engy
 * @param <T>
 */
public interface BasicDao<T extends Persistable> {

    /**
     *
     * @param clazz
     * @param id
     * @return
     */
    public T load(Class<T> clazz, int id);

    public T load(Class<T> clazz, T t);

    /**
     *
     * @param clazz
     * @param t
     */
    public void delete(Class<T> clazz, T t);

    public void delete(Class<T> clazz, int t);

    public List<T> load(Class<T> clazz);

    /**
     *
     * @param t
     */
    public void persist(T t);

    /**
     *
     * @param t
     * @return
     */
    public T update(T t);

    /**
     *
     * @param query
     * @return
     */
    public Collection getByQuery(String query);

    public T save(T t);

    public List<T> loadEntities(Class<T> clazz);

    public List<T> loadEntities(Class<T> clazz, List<Integer> ids);

    public String getORCondition(String columnName, Set<Integer> ids);

    public long getCountOf(Class<T> clazz);
}
