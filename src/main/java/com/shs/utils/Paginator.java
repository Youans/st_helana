/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.utils;

import com.shs.utils.log.SystemLogger;
import com.shs.web.action.BaseAction;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Youans
 */
@ViewScoped
public abstract class Paginator<T> extends BaseAction implements Serializable {

    @Setter
    @Getter
    protected int start;
    @Setter
    @Getter
    private int cursor;
    @Getter
    @Setter
    protected int limit = 10;
    @Setter
    @Getter
    private boolean stopped = false;
    @Setter
    @Getter
    private List<T> updatedModel;
    @Setter
    @Getter
    private int maximumSize = -1;

    protected abstract void updateList();

    protected abstract List<T> getOriginalList();

    protected abstract void initList();

    public void nextListner() {
        try {

            int maxPages = (int) Math.ceil((float) getOriginalList().size() / limit);
            start++;

            if (start < maxPages) {
                cursor = start * limit;
            } else if (!stopped) {
                updateList();
                getOriginalList().addAll(updatedModel);
                stopped = updatedModel.isEmpty();

                if (stopped) {
                    maximumSize = start;
                    start--;
                }
                cursor = start * limit;
            } else {
                start--;
            }
        } catch (Exception e) {
            SystemLogger.getLogger(getClass().getName()).error(null, e);
        }
    }

    public void prevListner() {
        if (start != 0) {
            start--;
        }
        cursor = start * limit;
    }

    protected void resetList() {
        try {

            setStart(0);
            setStopped(false);
            setCursor(0);
            initList();
        } catch (Exception e) {
            SystemLogger.getLogger(getClass().getName()).error(null, e);
        }
    }

    public boolean hasNext() {
        return maximumSize == -1 ? true : maximumSize > (start + 1);
    }

    public boolean hasPrev() {
        return start != 0;
    }

}
