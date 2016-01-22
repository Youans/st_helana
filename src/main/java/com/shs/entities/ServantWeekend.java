/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.entities;

import java.io.Serializable;
import com.shs.utils.database.Persistable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Youans
 */
@Entity
@Table(name = "servant_weekend")
@NamedQueries({
    @NamedQuery(name = "ServantWeekend.findAll", query = "SELECT s FROM ServantWeekend s")})
public class ServantWeekend extends Persistable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weekend")
    private int weekend;
    @JoinColumn(name = "servant", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Servant servant;

    public ServantWeekend() {
    }

    public ServantWeekend(Integer id) {
        this.id = id;
    }

    public ServantWeekend(Integer id, int weekend) {
        this.id = id;
        this.weekend = weekend;
    }

    public ServantWeekend(Servant servant, int weekend) {
        this.servant = servant;
        this.weekend = weekend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWeekend() {
        return weekend;
    }

    public void setWeekend(int weekend) {
        this.weekend = weekend;
    }

    public Servant getServant() {
        return servant;
    }

    public void setServant(Servant servant) {
        this.servant = servant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServantWeekend)) {
            return false;
        }
        ServantWeekend other = (ServantWeekend) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sts.entities.ServantWeekend[ id=" + id + " ]";
    }

}
