/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bleno Vale
 */
@Entity
@Table(name = "guia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guia.findAll", query = "SELECT g FROM Guia g"),
    @NamedQuery(name = "Guia.findById", query = "SELECT g FROM Guia g WHERE g.id = :id"),
    @NamedQuery(name = "Guia.findByCaminhoGuia", query = "SELECT g FROM Guia g WHERE g.caminhoGuia = :caminhoGuia"),
    @NamedQuery(name = "Guia.findByCreated", query = "SELECT g FROM Guia g WHERE g.created = :created"),
    @NamedQuery(name = "Guia.findByModified", query = "SELECT g FROM Guia g WHERE g.modified = :modified")})
public class Guia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "caminhoGuia")
    private String caminhoGuia;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGuia")
    private List<Itemguia> itemguiaList;
    @JoinColumn(name = "idOrganizacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Organizacao idOrganizacao;

    public Guia() {
    }

    public Guia(Integer id) {
        this.id = id;
    }

    public Guia(Integer id, Date created, Date modified) {
        this.id = id;
        this.created = created;
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaminhoGuia() {
        return caminhoGuia;
    }

    public void setCaminhoGuia(String caminhoGuia) {
        this.caminhoGuia = caminhoGuia;
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

    @XmlTransient
    public List<Itemguia> getItemguiaList() {
        return itemguiaList;
    }

    public void setItemguiaList(List<Itemguia> itemguiaList) {
        this.itemguiaList = itemguiaList;
    }

    public Organizacao getIdOrganizacao() {
        return idOrganizacao;
    }

    public void setIdOrganizacao(Organizacao idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
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
        if (!(object instanceof Guia)) {
            return false;
        }
        Guia other = (Guia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Guia[ id=" + id + " ]";
    }
    
}
