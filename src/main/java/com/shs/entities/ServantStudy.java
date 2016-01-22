/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shs.entities;

import java.io.Serializable;import com.shs.utils.database.Persistable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Youans
 */
@Entity
@Table(name = "servant_study")
@NamedQueries({
    @NamedQuery(name = "ServantStudy.findAll", query = "SELECT s FROM ServantStudy s")})
public class ServantStudy extends Persistable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ctime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctime;
    @JoinColumn(name = "study", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Study study;
    @JoinColumn(name = "servant_study_status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ServantStudyStatus servantStudyStatus;
    @JoinColumn(name = "servant", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Servant servant;

    public ServantStudy() {
    }

    public ServantStudy(Servant servant,Study study, ServantStudyStatus servantStudyStatus) {
        this.study = study;
        this.servant = servant;
        this.servantStudyStatus = servantStudyStatus;
    }

    public ServantStudy(Integer id) {
        this.id = id;
    }

    public ServantStudy(Integer id, Date ctime) {
        this.id = id;
        this.ctime = ctime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public ServantStudyStatus getServantStudyStatus() {
        return servantStudyStatus;
    }

    public void setServantStudyStatus(ServantStudyStatus servantStudyStatus) {
        this.servantStudyStatus = servantStudyStatus;
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
        if (!(object instanceof ServantStudy)) {
            return false;
        }
        ServantStudy other = (ServantStudy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sts.entities.ServantStudy[ id=" + id + " ]";
    }
    
}
