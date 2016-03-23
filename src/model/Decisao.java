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
@Table(name = "decisao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Decisao.findAll", query = "SELECT d FROM Decisao d"),
    @NamedQuery(name = "Decisao.findByIdProblema", query = "SELECT d FROM Decisao d WHERE d.idProblema = :idProblema"),
    @NamedQuery(name = "Decisao.findById", query = "SELECT d FROM Decisao d WHERE d.id = :id"),
    @NamedQuery(name = "Decisao.findByDefinitivo", query = "SELECT d FROM Decisao d WHERE d.definitivo = :definitivo"),
    @NamedQuery(name = "Decisao.findByCreated", query = "SELECT d FROM Decisao d WHERE d.created = :created"),
    @NamedQuery(name = "Decisao.findByModified", query = "SELECT d FROM Decisao d WHERE d.modified = :modified")})
public class Decisao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idProblema")
    private int idProblema;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "definitivo")
    private boolean definitivo;
    @Lob
    @Column(name = "justificativa")
    private String justificativa;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modified")
    @Temporal(TemporalType.DATE)
    private Date modified;
    @JoinColumn(name = "idAlternativa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Alternativa idAlternativa;

    public Decisao() {
    }

    public Decisao(Integer id) {
        this.id = id;
    }

    public Decisao(Integer id, int idProblema, boolean definitivo, Date created, Date modified) {
        this.id = id;
        this.idProblema = idProblema;
        this.definitivo = definitivo;
        this.created = created;
        this.modified = modified;
    }

    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getDefinitivo() {
        return definitivo;
    }

    public void setDefinitivo(boolean definitivo) {
        this.definitivo = definitivo;
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

    public Alternativa getIdAlternativa() {
        return idAlternativa;
    }

    public void setIdAlternativa(Alternativa idAlternativa) {
        this.idAlternativa = idAlternativa;
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
        if (!(object instanceof Decisao)) {
            return false;
        }
        Decisao other = (Decisao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Decisao[ id=" + id + " ]";
    }
    
}
