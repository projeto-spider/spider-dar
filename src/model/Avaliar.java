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
@Table(name = "avaliar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliar.findAll", query = "SELECT a FROM Avaliar a"),
    @NamedQuery(name = "Avaliar.findById", query = "SELECT a FROM Avaliar a WHERE a.id = :id"),
    @NamedQuery(name = "Avaliar.findByNota", query = "SELECT a FROM Avaliar a WHERE a.nota = :nota"),
    @NamedQuery(name = "Avaliar.findByValor", query = "SELECT a FROM Avaliar a WHERE a.valor = :valor"),
    @NamedQuery(name = "Avaliar.findByCreated", query = "SELECT a FROM Avaliar a WHERE a.created = :created"),
    @NamedQuery(name = "Avaliar.findByModified", query = "SELECT a FROM Avaliar a WHERE a.modified = :modified")})
public class Avaliar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nota")
    private String nota;
    @Basic(optional = false)
    @Column(name = "valor")
    private int valor;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @JoinColumn(name = "idAlternativa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Alternativa idAlternativa;
    @JoinColumn(name = "idCriterio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Criterio idCriterio;

    public Avaliar() {
    }

    public Avaliar(Integer id) {
        this.id = id;
    }

    public Avaliar(Integer id, String nota, int valor, Date created, Date modified) {
        this.id = id;
        this.nota = nota;
        this.valor = valor;
        this.created = created;
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
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

    public Criterio getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(Criterio idCriterio) {
        this.idCriterio = idCriterio;
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
        if (!(object instanceof Avaliar)) {
            return false;
        }
        Avaliar other = (Avaliar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Avaliar[ id=" + id + " ]";
    }
    
}
