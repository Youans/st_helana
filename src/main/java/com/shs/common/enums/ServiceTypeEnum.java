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
public enum ServiceTypeEnum {

    EFTKAD(1, "Eftkad"), MADARS27D(2, "Madars27d"), MEDIA(3, "media"), NZAM(4, "nzam");
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;

    private ServiceTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
