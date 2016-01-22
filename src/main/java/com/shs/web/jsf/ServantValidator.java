/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.web.jsf;

import com.shs.entities.Servant;
import com.shs.service.ServantService;
import com.shs.utils.SpringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Michael
 */
@FacesValidator(value = "ServantValidator")
public class ServantValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object email) throws ValidatorException {
        ServantService servantService = SpringUtils.getBean(ServantService.class);
        Servant servant = servantService.loadServantByEmail((String) email);
        if (servant != null && !servant.getEmail().equals(email.toString())) {
            throw new ValidatorException(new FacesMessage("Email is already registered"));
        }
    }

}
