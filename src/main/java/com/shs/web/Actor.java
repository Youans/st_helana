/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.web;

import com.shs.entities.Servant;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Youans
 */
@Scope(value = "session")
@Component(value = "actor")
public class Actor implements Serializable {

    @Getter
    @Setter
    private String localeCode = "en";
    @Getter
    @Setter
    private Locale locale = new Locale(localeCode);

    public void localChangeEvent(ValueChangeEvent e) {
        localeCode = e.getNewValue().toString();
        locale = new Locale(localeCode);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

    }

    public boolean isRtl() {
        return localeCode.equals("ar");
    }

    @PostConstruct
    public void init() {
        locale = new Locale(localeCode);
    }
    @Getter
    @Setter
    private Servant servant;
}
