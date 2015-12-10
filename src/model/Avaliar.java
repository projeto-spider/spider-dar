/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sandro Bezerra
 */
@Entity
@Table(name = "avaliar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliar.findAll", query = "SELECT a FROM Avaliar a"),
    @NamedQuery(name = "Avaliar.findByIdAlternativa", query = "SELECT a FROM Avaliar a WHERE a.avaliarPK.idAlternativa = :idAlternativa"),
    @NamedQuery(name = "Avaliar.findByIdAvaliacao", query = "SELECT a FROM Avaliar a WHERE a.avaliarPK.idAvaliacao = :idAvaliacao"),
    @NamedQuery(name = "Avaliar.findByIdCriterio", query = "SELECT a FROM Avaliar a WHERE a.avaliarPK.idCriterio = :idCriterio"),
    @NamedQuery(name = "Avaliar.findByCreated", query = "SELECT a FROM Avaliar a WHERE a.created = :created"),
    @NamedQuery(name = "Avaliar.findByModified", query = "SELECT a FROM Avaliar a WHERE a.modified = :modified")})
public class Avaliar implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AvaliarPK avaliarPK;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    public Avaliar() {
    }

    public Avaliar(AvaliarPK avaliarPK) {
        this.avaliarPK = avaliarPK;
    }

    public Avaliar(AvaliarPK avaliarPK, Date created, Date modified) {
        this.avaliarPK = avaliarPK;
        this.created = created;
        this.modified = modified;
    }

    public Avaliar(int idAlternativa, int idAvaliacao, int idCriterio) {
        this.avaliarPK = new AvaliarPK(idAlternativa, idAvaliacao, idCriterio);
    }

    public AvaliarPK getAvaliarPK() {
        return avaliarPK;
    }

    public void setAvaliarPK(AvaliarPK avaliarPK) {
        this.avaliarPK = avaliarPK;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (avaliarPK != null ? avaliarPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliar)) {
            return false;
        }
        Avaliar other = (Avaliar) object;
        if ((this.avaliarPK == null && other.avaliarPK != null) || (this.avaliarPK != null && !this.avaliarPK.equals(other.avaliarPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Avaliar[ avaliarPK=" + avaliarPK + " ]";
    }
    
}
