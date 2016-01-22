/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.dao;

import com.shs.entities.Servant;
import java.util.List;

/**
 *
 * @author Engy
 * @param <T>
 */
public interface ServantDao extends BasicDao<Servant> {

    public Servant loadServantByEmail(String email);

    public Servant loadServantByEmailAndPassword(String email, String password);

    public List<Servant> loadServantsForAdmin();
    

}
