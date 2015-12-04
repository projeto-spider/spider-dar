/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Spider
 */
@Embeddable
public class AvaliarPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idAlternativa")
    private int idAlternativa;
    @Basic(optional = false)
    @Column(name = "idAvaliacao")
    private int idAvaliacao;
    @Basic(optional = false)
    @Column(name = "idCriterio")
    private int idCriterio;

    public AvaliarPK() {
    }

    public AvaliarPK(int idAlternativa, int idAvaliacao, int idCriterio) {
        this.idAlternativa = idAlternativa;
        this.idAvaliacao = idAvaliacao;
        this.idCriterio = idCriterio;
    }

    public int getIdAlternativa() {
        return idAlternativa;
    }

    public void setIdAlternativa(int idAlternativa) {
        this.idAlternativa = idAlternativa;
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAlternativa;
        hash += (int) idAvaliacao;
        hash += (int) idCriterio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvaliarPK)) {
            return false;
        }
        AvaliarPK other = (AvaliarPK) object;
        if (this.idAlternativa != other.idAlternativa)
            return false;
        if (this.idAvaliacao != other.idAvaliacao)
            return false;
        if (this.idCriterio != other.idCriterio)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.AvaliarPK[ idAlternativa=" + idAlternativa + ", idAvaliacao=" + idAvaliacao + ", idCriterio=" + idCriterio + " ]";
    }
    
}
