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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bleno Vale
 */
@Entity
@Table(name = "itemguia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemguia.findAll", query = "SELECT i FROM Itemguia i"),
    @NamedQuery(name = "Itemguia.findById", query = "SELECT i FROM Itemguia i WHERE i.id = :id"),
    @NamedQuery(name = "Itemguia.findByTipo", query = "SELECT i FROM Itemguia i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "Itemguia.findByCreated", query = "SELECT i FROM Itemguia i WHERE i.created = :created"),
    @NamedQuery(name = "Itemguia.findByModified", query = "SELECT i FROM Itemguia i WHERE i.modified = :modified")})
public class Itemguia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "definicao")
    private String definicao;
    @Basic(optional = false)
    @Lob
    @Column(name = "aplicacao")
    private String aplicacao;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @Lob
    @Column(name = "tipoDescricao")
    private String tipoDescricao;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @JoinColumn(name = "idGuia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Guia idGuia;

    public Itemguia() {
    }

    public Itemguia(Integer id) {
        this.id = id;
    }

    public Itemguia(Integer id, String definicao, String aplicacao, int tipo, String tipoDescricao, Date created, Date modified) {
        this.id = id;
        this.definicao = definicao;
        this.aplicacao = aplicacao;
        this.tipo = tipo;
        this.tipoDescricao = tipoDescricao;
        this.created = created;
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefinicao() {
        return definicao;
    }

    public void setDefinicao(String definicao) {
        this.definicao = definicao;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTipoDescricao() {
        return tipoDescricao;
    }

    public void setTipoDescricao(String tipoDescricao) {
        this.tipoDescricao = tipoDescricao;
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

    public Guia getIdGuia() {
        return idGuia;
    }

    public void setIdGuia(Guia idGuia) {
        this.idGuia = idGuia;
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
        if (!(object instanceof Itemguia)) {
            return false;
        }
        Itemguia other = (Itemguia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Itemguia[ id=" + id + " ]";
    }
    
}
