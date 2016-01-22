/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.dao.impl;

import com.shs.common.exceptions.InvalidParameter;
import com.shs.dao.BasicDao;
import com.shs.entities.Servant;
import com.shs.utils.CollectionUtils;
import java.io.Serializable;
import com.shs.utils.database.Persistable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Engy
 * @param <T>
 */
@Repository("basicDao")
public class BasicDaoImpl<T extends Persistable> implements BasicDao<T>, Serializable {

    public static final String OR = " OR ";
    private final static Logger LOGGER = Logger.getGlobal();
    @PersistenceContext
    @Setter
    @Getter
    protected EntityManager entityManager;

    @Transactional
    @Override
    public T load(Class<T> clazz, int id) {
        return entityManager.find(clazz, id);
    }

    @Transactional
    @Override
    public T load(Class<T> clazz, T t) {
        return entityManager.find(clazz, t);
    }

    @Transactional
    @Override
    public List<T> load(Class<T> clazz) {

        String queryStr = "select t from " + clazz.getSimpleName() + " t";
        List list=entityManager.createQuery(queryStr).getResultList();
        System.out.println("List loadAll>>>");
        return list;
    }

    @Transactional
    @Override
    public void delete(Class<T> clazz, T t) {
        entityManager.remove(entityManager.merge(load(clazz, t)));
    }

    @Transactional
    @Override
    public void delete(Class<T> clazz, int id) {
        entityManager.remove(entityManager.merge(load(clazz, id)));
    }

    @Transactional
    @Override
    public void persist(T t) {
        entityManager.persist(t);
    }

    @Transactional
    @Override
    public T update(T t) {
        return entityManager.merge(t);
    }

    @Transactional
    @Override
    public Collection getByQuery(String query) {
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    @Override
    public T save(T t) {
        System.out.println(" persist   t " + ((Servant) t).getEmail());
        if (t.isNew()) {
            persist(t);
        } else {
            update(t);
        }
        return t;
    }

    public int getIntResult(Query query) {
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public long getLongResult(Query query) {
        return Long.parseLong(query.getSingleResult().toString());
    }

    public <T> T getSingleResult(Query query) {
        List<T> result = CollectionUtils.<T>cast(query.getResultList());
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public Query updateQueryLimit(Query query, int start, int limit) {
        try {
            if (start > 0) {
                query = query.setFirstResult(start);
            }
            if (limit > 0) {
                query = query.setMaxResults(limit);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return query;
    }

    public Query updateQueryLimit(Query query, int limit) {
        try {
            if (limit > 0) {
                query = query.setMaxResults(limit);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return query;
    }

    @Transactional
    @Override
    public List<T> loadEntities(Class<T> clazz) {
        try {
            String queryStr = "SELECT entity FROM {tableName} entity";
            return getEntityManager().createQuery(queryStr.replace("{tableName}", clazz.getSimpleName())).
                    getResultList();
        } catch (Exception ex) {

        }
        return null;
    }

    @Transactional
    @Override
    public List<T> loadEntities(Class<T> clazz, List<Integer> ids) {
        try {
            if (ids != null && !ids.isEmpty()) {
                String queryStr = "SELECT entity FROM {tableName} entity where {oredConditionFilter} order by entity.id desc".replace("{tableName}", clazz.getSimpleName()).replace("{oredConditionFilter}", getORCondition("entity.id", ids));
                return getEntityManager().createQuery(queryStr).getResultList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String getORCondition(String columnName, Set<Integer> ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer id : ids) {
            stringBuilder.append(columnName).append("=").append(String.valueOf(id)).append(OR);
        }
        return stringBuilder.substring(0, stringBuilder.lastIndexOf(OR));
    }

    public String getORCondition(String columnName, List<Integer> ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer id : ids) {
            stringBuilder.append(columnName).append("=").append(String.valueOf(id)).append(OR);
        }
        return stringBuilder.substring(0, stringBuilder.lastIndexOf(OR));
    }

    public String getStringsORCondition(String columnName, List<Integer> ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer id : ids) {
            stringBuilder.append(columnName).append("=").append("'").append(String.valueOf(id)).append("'").append(OR);
        }
        return stringBuilder.substring(0, stringBuilder.lastIndexOf(OR));
    }

    public long getLastLongId(long lastId) {
        if (lastId == 0) {
            lastId = Long.MAX_VALUE;
        }
        return lastId;
    }

    public long getMaxLongId(long maxId) {
        if (maxId == 0) {
            return 0;
        }
        return maxId;
    }

    @Transactional
    public void validateUpdateQuery(Query query) throws InvalidParameter {
        // if no record affected
        if (query.executeUpdate() == 0) {
            throw new InvalidParameter();
        }
    }

    @Override
    public long getCountOf(Class<T> clazz) {
        String queryStr = "Select count(*) from " + clazz.getSimpleName();
        return getSingleResult(getEntityManager().createQuery(queryStr));
    }

}
