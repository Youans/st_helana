/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.web.action;

import com.shs.common.enums.ParametersEnum;
import com.shs.service.BasicService;
import com.shs.utils.JsfUtils;
import com.shs.utils.database.Persistable;
import com.shs.utils.log.SystemLogger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Youans
 * @param <T>
 */
@ManagedBean
public abstract class BaseEntityAction<T extends Persistable> extends BaseAction {

    @ManagedProperty("#{basicService}")
    @Getter
    @Setter
    private BasicService<T> basicService;

    @Setter
    protected T t;

    private Class<T> clazz;

    public BaseEntityAction(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T getEntity() {
        try {
            if (t == null) {
                if (getEntityId() != 0) {
                    t = basicService.load(clazz, getEntityId());
                }
                if (t == null) {
                    t = clazz.newInstance();
                }
            }
            return t;
        } catch (Exception e) {
            SystemLogger.getLogger(getClass().getName()).error(null, e);
        }
        return null;
    }

    public int getEntityId() {
        return JsfUtils.getIntParameter(ParametersEnum.findByClass(clazz).getKey());

    }

    public void save() {
        try {
            System.out.println("getEntity()  " + getEntity());
            basicService.save(getEntity());
        } catch (Exception e) {
            SystemLogger.getLogger(getClass().getName()).error(null, e);
        }
    }

}
