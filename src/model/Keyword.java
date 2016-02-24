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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Spider
 */
@Entity
@Table(name = "keyword")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Keyword.findAll", query = "SELECT k FROM Keyword k"),
    @NamedQuery(name = "Keyword.findById", query = "SELECT k FROM Keyword k WHERE k.keywordPK.id = :id"),
    @NamedQuery(name = "Keyword.findByIdForeignKey", query = "SELECT k FROM Keyword k WHERE k.keywordPK.idForeignKey = :idForeignKey"),
    @NamedQuery(name = "Keyword.findByNome", query = "SELECT k FROM Keyword k WHERE k.nome = :nome"),
    @NamedQuery(name = "Keyword.findByCreated", query = "SELECT k FROM Keyword k WHERE k.created = :created"),
    @NamedQuery(name = "Keyword.findByModified", query = "SELECT k FROM Keyword k WHERE k.modified = :modified")})
public class Keyword implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KeywordPK keywordPK;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @JoinColumn(name = "idForeignKey", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Problema problema;

    public Keyword() {
    }

    public Keyword(KeywordPK keywordPK) {
        this.keywordPK = keywordPK;
    }

    public Keyword(KeywordPK keywordPK, String nome, Date created, Date modified) {
        this.keywordPK = keywordPK;
        this.nome = nome;
        this.created = created;
        this.modified = modified;
    }

    public Keyword(int id, int idForeignKey) {
        this.keywordPK = new KeywordPK(id, idForeignKey);
    }

    public KeywordPK getKeywordPK() {
        return keywordPK;
    }

    public void setKeywordPK(KeywordPK keywordPK) {
        this.keywordPK = keywordPK;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keywordPK != null ? keywordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Keyword)) {
            return false;
        }
        Keyword other = (Keyword) object;
        if ((this.keywordPK == null && other.keywordPK != null) || (this.keywordPK != null && !this.keywordPK.equals(other.keywordPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Keyword[ keywordPK=" + keywordPK + " ]";
    }
    
}
