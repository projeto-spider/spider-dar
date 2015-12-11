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
 * @author Bleno Vale
 */
@Embeddable
public class AcessarPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idOrganizacao")
    private int idOrganizacao;
    @Basic(optional = false)
    @Column(name = "idProblema")
    private int idProblema;
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private int idUsuario;

    public AcessarPK() {
    }

    public AcessarPK(int idOrganizacao, int idProblema, int idUsuario) {
        this.idOrganizacao = idOrganizacao;
        this.idProblema = idProblema;
        this.idUsuario = idUsuario;
    }

    public int getIdOrganizacao() {
        return idOrganizacao;
    }

    public void setIdOrganizacao(int idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOrganizacao;
        hash += (int) idProblema;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcessarPK)) {
            return false;
        }
        AcessarPK other = (AcessarPK) object;
        if (this.idOrganizacao != other.idOrganizacao) {
            return false;
        }
        if (this.idProblema != other.idProblema) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AcessarPK[ idOrganizacao=" + idOrganizacao + ", idProblema=" + idProblema + ", idUsuario=" + idUsuario + " ]";
    }
    
}
