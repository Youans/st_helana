/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.web.action;

import com.shs.common.Cache;
import com.shs.service.ServantService;
import com.shs.web.Actor;
import java.util.Locale;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author youans
 */
public class BaseAction {

    @ManagedProperty(value = "#{cache}")
    @Getter
    @Setter
    protected Cache cache;

    @ManagedProperty(value = "#{servantService}")
    @Getter
    @Setter
    protected ServantService servantService;

}
