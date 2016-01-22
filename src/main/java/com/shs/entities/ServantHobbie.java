/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shs.entities;

import java.io.Serializable;import com.shs.utils.database.Persistable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Youans
 */
@Entity
@Table(name = "servant_hobbie")
@NamedQueries({
    @NamedQuery(name = "ServantHobbie.findAll", query = "SELECT s FROM ServantHobbie s")})
public class ServantHobbie extends Persistable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "servant")
    private int servant;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Servant servant1;
    @JoinColumn(name = "hobbie", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hobbie hobbie;

    public ServantHobbie() {
    }

    public ServantHobbie(Integer id) {
        this.id = id;
    }

    public ServantHobbie(Integer id, int servant) {
        this.id = id;
        this.servant = servant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getServant() {
        return servant;
    }

    public void setServant(int servant) {
        this.servant = servant;
    }

    public Servant getServant1() {
        return servant1;
    }

    public void setServant1(Servant servant1) {
        this.servant1 = servant1;
    }

    public Hobbie getHobbie() {
        return hobbie;
    }

    public void setHobbie(Hobbie hobbie) {
        this.hobbie = hobbie;
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
        if (!(object instanceof ServantHobbie)) {
            return false;
        }
        ServantHobbie other = (ServantHobbie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sts.entities.ServantHobbie[ id=" + id + " ]";
    }
    
}
