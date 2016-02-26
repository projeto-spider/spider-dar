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
@Table(name = "configuracoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuracoes.findAll", query = "SELECT c FROM Configuracoes c"),
    @NamedQuery(name = "Configuracoes.findById", query = "SELECT c FROM Configuracoes c WHERE c.id = :id"),
    @NamedQuery(name = "Configuracoes.findByEmail", query = "SELECT c FROM Configuracoes c WHERE c.email = :email"),
    @NamedQuery(name = "Configuracoes.findBySenha", query = "SELECT c FROM Configuracoes c WHERE c.senha = :senha"),
    @NamedQuery(name = "Configuracoes.findByServidor", query = "SELECT c FROM Configuracoes c WHERE c.servidor = :servidor"),
    @NamedQuery(name = "Configuracoes.findByPorta", query = "SELECT c FROM Configuracoes c WHERE c.porta = :porta"),
    @NamedQuery(name = "Configuracoes.findByTipoCript", query = "SELECT c FROM Configuracoes c WHERE c.tipoCript = :tipoCript"),
    @NamedQuery(name = "Configuracoes.findByCreated", query = "SELECT c FROM Configuracoes c WHERE c.created = :created"),
    @NamedQuery(name = "Configuracoes.findByModified", query = "SELECT c FROM Configuracoes c WHERE c.modified = :modified")})
public class Configuracoes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @Column(name = "servidor")
    private String servidor;
    @Basic(optional = false)
    @Column(name = "porta")
    private String porta;
    @Basic(optional = false)
    @Column(name = "tipoCript")
    private String tipoCript;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    public Configuracoes() {
    }

    public Configuracoes(Integer id) {
        this.id = id;
    }

    public Configuracoes(Integer id, String email, String senha, String servidor, String porta, String tipoCript, Date created, Date modified) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.servidor = servidor;
        this.porta = porta;
        this.tipoCript = tipoCript;
        this.created = created;
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getTipoCript() {
        return tipoCript;
    }

    public void setTipoCript(String tipoCript) {
        this.tipoCript = tipoCript;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuracoes)) {
            return false;
        }
        Configuracoes other = (Configuracoes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Configuracoes[ id=" + id + " ]";
    }
    
}
