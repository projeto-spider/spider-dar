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
public class AcessarPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idOrganizacao")
    private int idOrganizacao;
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "idPerfil")
    private int idPerfil;

    public AcessarPK() {
    }

    public AcessarPK(int idOrganizacao, int idUsuario, int idPerfil) {
        this.idOrganizacao = idOrganizacao;
        this.idUsuario = idUsuario;
        this.idPerfil = idPerfil;
    }

    public int getIdOrganizacao() {
        return idOrganizacao;
    }

    public void setIdOrganizacao(int idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOrganizacao;
        hash += (int) idUsuario;
        hash += (int) idPerfil;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcessarPK)) {
            return false;
        }
        AcessarPK other = (AcessarPK) object;
        if (this.idOrganizacao != other.idOrganizacao)
            return false;
        if (this.idUsuario != other.idUsuario)
            return false;
        if (this.idPerfil != other.idPerfil)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.AcessarPK[ idOrganizacao=" + idOrganizacao + ", idUsuario=" + idUsuario + ", idPerfil=" + idPerfil + " ]";
    }
    
}
