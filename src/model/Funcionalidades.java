/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bleno Vale
 */
@Entity
@Table(name = "funcionalidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionalidades.findAll", query = "SELECT f FROM Funcionalidades f"),
    @NamedQuery(name = "Funcionalidades.findById", query = "SELECT f FROM Funcionalidades f WHERE f.id = :id"),
    @NamedQuery(name = "Funcionalidades.findByNome", query = "SELECT f FROM Funcionalidades f WHERE f.nome = :nome")})
public class Funcionalidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @JoinTable(name = "possuir", joinColumns = {
        @JoinColumn(name = "idFuncionalidades", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idPerfil", referencedColumnName = "id")})
    @ManyToMany
    private List<Perfil> perfilList;

    public Funcionalidades() {
    }

    public Funcionalidades(Integer id) {
        this.id = id;
    }

    public Funcionalidades(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @XmlTransient
    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
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
        if (!(object instanceof Funcionalidades)) {
            return false;
        }
        Funcionalidades other = (Funcionalidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Funcionalidades[ id=" + id + " ]";
    }
    
}
