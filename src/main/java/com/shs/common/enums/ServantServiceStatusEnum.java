/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author youans
 */
public enum ServantServiceStatusEnum {

    CURRENT(1, "Current"), PERVIOUS(2, "Pervious"), PERFERED(3, "Perefered");
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;

    private ServantServiceStatusEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
