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
public enum ServantStudyStatusEnum {

    STUDYING(1, "Studying"), QUALIFIED(2, "Qualified"), Working(3, "Working");
@Getter
@Setter
    private int id;
@Getter
@Setter
    private String name;

    private ServantStudyStatusEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
