/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bleno Vale
 */
@Entity
@Table(name = "acessar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acessar.findAll", query = "SELECT a FROM Acessar a"),
    @NamedQuery(name = "Acessar.findByIdOrganizacao", query = "SELECT a FROM Acessar a WHERE a.acessarPK.idOrganizacao = :idOrganizacao"),
    @NamedQuery(name = "Acessar.findByIdProblema", query = "SELECT a FROM Acessar a WHERE a.acessarPK.idProblema = :idProblema"),
    @NamedQuery(name = "Acessar.findByIdUsuario", query = "SELECT a FROM Acessar a WHERE a.acessarPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "Acessar.findByIdPerfil", query = "SELECT a FROM Acessar a WHERE a.acessarPK.idPerfil = :idPerfil")})
public class Acessar implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AcessarPK acessarPK;
    @JoinColumn(name = "idOrganizacao", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Organizacao organizacao;
    @JoinColumn(name = "idPerfil", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Perfil perfil;
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Acessar() {
    }

    public Acessar(AcessarPK acessarPK) {
        this.acessarPK = acessarPK;
    }

    public Acessar(int idOrganizacao, int idProblema, int idUsuario, int idPerfil) {
        this.acessarPK = new AcessarPK(idOrganizacao, idProblema, idUsuario, idPerfil);
    }

    public AcessarPK getAcessarPK() {
        return acessarPK;
    }

    public void setAcessarPK(AcessarPK acessarPK) {
        this.acessarPK = acessarPK;
    }

    public Organizacao getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(Organizacao organizacao) {
        this.organizacao = organizacao;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acessarPK != null ? acessarPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acessar)) {
            return false;
        }
        Acessar other = (Acessar) object;
        if ((this.acessarPK == null && other.acessarPK != null) || (this.acessarPK != null && !this.acessarPK.equals(other.acessarPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Acessar[ acessarPK=" + acessarPK + " ]";
    }
    
}
