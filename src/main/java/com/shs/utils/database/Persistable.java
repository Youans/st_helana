/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.utils.database;

import java.io.Serializable;
import com.shs.utils.database.Persistable;

/**
 * Simple interface for entities.
 *
 * @author Oliver Gierke - gierke@synyx.de
 * @param <PK> the type of the identifier
 */
public abstract class Persistable<PK extends Integer> implements Serializable {

    /**
     * Returns the id of the entity.
     *
     * @return the id
     */
    public abstract PK getId();

    /**
     * Returns if the {@code Persistable} is new or was persisted already.
     *
     * @return if the object is new
     */
    public boolean isNew() {
        return (getId() == null || getId().intValue() == 0);
    }
}
