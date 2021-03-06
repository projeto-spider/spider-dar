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
@Table(name = "criterio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Criterio.findAll", query = "SELECT c FROM Criterio c"),
    @NamedQuery(name = "Criterio.findById", query = "SELECT c FROM Criterio c WHERE c.id = :id"),
    @NamedQuery(name = "Criterio.findByPeso", query = "SELECT c FROM Criterio c WHERE c.peso = :peso"),
    @NamedQuery(name = "Criterio.findByCreated", query = "SELECT c FROM Criterio c WHERE c.created = :created"),
    @NamedQuery(name = "Criterio.findByModified", query = "SELECT c FROM Criterio c WHERE c.modified = :modified")})
public class Criterio implements Serializable {
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
    @Column(name = "peso")
    private int peso;
    @Basic(optional = false)
    @Lob
    @Column(name = "justificativa")
    private String justificativa;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCriterio")
    private List<Avaliar> avaliarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCriterio")
    private List<Nota> notaList;

    public Criterio() {
    }

    public Criterio(Integer id) {
        this.id = id;
    }

    public Criterio(Integer id, String nome, int peso, String justificativa, Date created, Date modified) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.justificativa = justificativa;
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
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
    public List<Avaliar> getAvaliarList() {
        return avaliarList;
    }

    public void setAvaliarList(List<Avaliar> avaliarList) {
        this.avaliarList = avaliarList;
    }

    @XmlTransient
    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
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
        if (!(object instanceof Criterio)) {
            return false;
        }
        Criterio other = (Criterio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Criterio[ id=" + id + " ]";
    }
    
}
