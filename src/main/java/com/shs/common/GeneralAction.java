/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Youans
 */
@ManagedBean
@RequestScoped
public class GeneralAction {

    public String getBaseUrl() {
        return "http://localhost:8084";
    }

}
