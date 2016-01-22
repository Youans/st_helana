/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.web.action;

import com.shs.entities.Servant;
import com.shs.service.ServantService;
import com.shs.utils.SpringUtils;
import com.shs.utils.log.SystemLogger;
import com.shs.web.Actor;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Youans
 */
@ManagedBean
@ViewScoped
public class LoginAction extends BaseEntityAction<Servant> {

    @ManagedProperty("#{servantService}")
    @Getter
    @Setter
    private ServantService servantService;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String msg;

    public LoginAction() {
        super(Servant.class);
    }

    public void login() {
        try {
            Actor actor = SpringUtils.getBean(Actor.class);
            Servant servant = servantService.loadServantByEmailAndPassword(email, password);
            if (servant == null) {
                msg = "Email or password is incorrect.";
            } else {
                actor.setServant(servant);
            }
        } catch (Exception e) {
            e.printStackTrace();
            SystemLogger.getLogger(getClass().getName()).error(null, e);
        }
    }

}
