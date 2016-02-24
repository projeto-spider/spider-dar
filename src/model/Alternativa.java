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
 * @author Spider
 */
@Entity
@Table(name = "alternativa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alternativa.findAll", query = "SELECT a FROM Alternativa a"),
    @NamedQuery(name = "Alternativa.findById", query = "SELECT a FROM Alternativa a WHERE a.id = :id"),
    @NamedQuery(name = "Alternativa.findByCusto", query = "SELECT a FROM Alternativa a WHERE a.custo = :custo"),
    @NamedQuery(name = "Alternativa.findByTempo", query = "SELECT a FROM Alternativa a WHERE a.tempo = :tempo"),
    @NamedQuery(name = "Alternativa.findByCreated", query = "SELECT a FROM Alternativa a WHERE a.created = :created"),
    @NamedQuery(name = "Alternativa.findByModified", query = "SELECT a FROM Alternativa a WHERE a.modified = :modified")})
public class Alternativa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "custo")
    private String custo;
    @Basic(optional = false)
    @Column(name = "tempo")
    private String tempo;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @JoinColumn(name = "idProblema", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Problema idProblema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlternativa")
    private List<Decisao> decisaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alternativa")
    private List<Avaliar> avaliarList;

    public Alternativa() {
    }

    public Alternativa(Integer id) {
        this.id = id;
    }

    public Alternativa(Integer id, String nome, String descricao, String custo, String tempo, Date created, Date modified) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.tempo = tempo;
        this.created = created;
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCusto() {
        return custo;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
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

    public Problema getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(Problema idProblema) {
        this.idProblema = idProblema;
    }

    @XmlTransient
    public List<Decisao> getDecisaoList() {
        return decisaoList;
    }

    public void setDecisaoList(List<Decisao> decisaoList) {
        this.decisaoList = decisaoList;
    }

    @XmlTransient
    public List<Avaliar> getAvaliarList() {
        return avaliarList;
    }

    public void setAvaliarList(List<Avaliar> avaliarList) {
        this.avaliarList = avaliarList;
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
        if (!(object instanceof Alternativa)) {
            return false;
        }
        Alternativa other = (Alternativa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Alternativa[ id=" + id + " ]";
    }
    
}
