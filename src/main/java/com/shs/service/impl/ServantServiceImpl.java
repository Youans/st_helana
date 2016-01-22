/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.service.impl;

import com.shs.dao.ServantDao;
import com.shs.entities.Servant;
import com.shs.service.BasicService;
import com.shs.service.ServantService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Youans
 * @param <T>
 */
@Service("servantService")
public class ServantServiceImpl extends BasicServiceImpl<Servant> implements BasicService<Servant> ,ServantService{

    @Autowired
    private ServantDao servantDao;

    public Servant loadServantByEmail(String email) {
        return servantDao.loadServantByEmail(email);

    }

    public Servant loadServantByEmailAndPassword(String email, String password) {
        return servantDao.loadServantByEmailAndPassword(email, password);
    }
    public List<Servant> loadServantsForAdmin(){
        return servantDao.loadServantsForAdmin();
    }

}
