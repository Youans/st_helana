/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.service;

import com.shs.utils.database.Persistable;
import java.util.List;

/**
 *
 * @author Engy
 */
// This service will be defined in baseAction and will be used wherever generic database action is performed like loadAllX or saveX
public interface BasicService<T extends Persistable> {

    public T load(Class<T> clazz, int id);

    public List<T> loadAll(Class<T> clazz);

    public void delete(Class<T> clazz, int id);

    /**
     *
     * @param p
     */
    public void save(T t);

    public long getCountOf(Class<T> clazz);

}
