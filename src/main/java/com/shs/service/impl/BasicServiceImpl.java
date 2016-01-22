/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.service.impl;

import com.shs.dao.BasicDao;
import com.shs.service.BasicService;
import com.shs.utils.database.Persistable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Youans
 * @param <T>
 */
@Service("basicService")
public class BasicServiceImpl<T extends Persistable> implements BasicService<T> {

    @Autowired
    private BasicDao<T> basicDao;

    @Override
    public T load(Class<T> clazz, int id) {
        return basicDao.load(clazz, id);
    }

    @Override
    public void delete(Class<T> clazz, int id) {
        basicDao.delete(clazz, id);
    }


    @Override
    public void save(T t) {
        basicDao.save(t);
    }

    @Override
    public long getCountOf(Class<T> clazz) {
        return basicDao.getCountOf(clazz);
    }

    @Override
    public List<T> loadAll(Class<T> clazz) {
        return basicDao.load(clazz);
    }
}
