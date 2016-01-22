/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.dao.impl;

import com.shs.dao.ServantDao;
import com.shs.entities.Servant;
import com.shs.utils.CollectionUtils;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Engy
 * @param <T>
 */
@Repository("servantDao")
public class ServantDaoImpl extends BasicDaoImpl<Servant> implements ServantDao {

    @Override
    public Servant loadServantByEmail(String email) {
        String queryStr = "Select s from Servant s where s.email=:email";
        Query query = getEntityManager().createQuery(queryStr).setParameter("email", email);
        return getSingleResult(query);
    }

    @Override
    public Servant loadServantByEmailAndPassword(String email, String password) {
        String queryStr = "Select s from Servant s where s.email=:email and s.password=:password";
        Query query = getEntityManager().createQuery(queryStr).setParameter("email", email).setParameter("password", password);
        return getSingleResult(query);
    }

    @Override
    public List<Servant> loadServantsForAdmin() {
        String queryStr = "Select s from Servant s order by s.id desc";
        Query query = getEntityManager().createQuery(queryStr);
        return CollectionUtils.<Servant>cast(query.getResultList());
    }
}
