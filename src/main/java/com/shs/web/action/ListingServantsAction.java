/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.web.action;

import com.shs.entities.Servant;
import com.shs.utils.Paginator;
import com.shs.utils.log.SystemLogger;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Youans
 */
@ManagedBean
@ViewScoped
public class ListingServantsAction extends Paginator<Servant> {

    @Getter
    @Setter
    private List<Servant> servants;

    public void init() {
        initList();
        setLimit(20);
    }

    @Override
    protected void updateList() {
        setUpdatedModel(getServantService().loadServantsForAdmin());
    }

    @Override
    protected List<Servant> getOriginalList() {
        return servants;
    }

    @Override
    protected void initList() {
        servants = getServantService().loadServantsForAdmin();

    }

    public void deleteServantListner(Servant servant) {
        try {
            servants.remove(servant);
            getServantService().delete(Servant.class, servant.getId());
        } catch (Exception e) {
            SystemLogger.getLogger(getClass().getName()).error(null, e);
        }
    }

}
