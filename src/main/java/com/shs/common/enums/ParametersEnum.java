/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.common.enums;

import com.shs.entities.Servant;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Youans
 */
public enum ParametersEnum {

    SERVANT("sId", Servant.class);

    private ParametersEnum(String paramKey, Class clazz) {
        this.key = paramKey;
        this.clazz = clazz;
    }
    @Getter
    @Setter
    private String key;
    @Getter
    @Setter
    private Class clazz;

    public static ParametersEnum findByClass(Class clazz) {
        for (ParametersEnum param : values()) {
            System.out.println("clazz  " + clazz);
            if (true) {
                return param;
            }
        }
        return null;
    }
}
