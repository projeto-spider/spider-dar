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
@Table(name = "problema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Problema.findAll", query = "SELECT p FROM Problema p"),
    @NamedQuery(name = "Problema.findById", query = "SELECT p FROM Problema p WHERE p.id = :id"),
    @NamedQuery(name = "Problema.findByNome", query = "SELECT p FROM Problema p WHERE p.nome = :nome"),
    @NamedQuery(name = "Problema.findByCreated", query = "SELECT p FROM Problema p WHERE p.created = :created"),
    @NamedQuery(name = "Problema.findByModified", query = "SELECT p FROM Problema p WHERE p.modified = :modified")})
public class Problema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Lob
    @Column(name = "proposito")
    private String proposito;
    @Basic(optional = false)
    @Lob
    @Column(name = "planejamento")
    private String planejamento;
    @Basic(optional = false)
    @Lob
    @Column(name = "contexto")
    private String contexto;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @OneToMany(mappedBy = "idProblema")
    private List<Acessar> acessarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProblema")
    private List<Tarefa> tarefaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProblema")
    private List<Historico> historicoList;
    @JoinColumn(name = "idOrganizacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Organizacao idOrganizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProblema")
    private List<Alternativa> alternativaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProblema")
    private List<Criterio> criterioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problema")
    private List<Keyword> keywordList;

    public Problema() {
    }

    public Problema(Integer id) {
        this.id = id;
    }

    public Problema(Integer id, String nome, String proposito, String planejamento, String contexto, Date created, Date modified) {
        this.id = id;
        this.nome = nome;
        this.proposito = proposito;
        this.planejamento = planejamento;
        this.contexto = contexto;
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

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getPlanejamento() {
        return planejamento;
    }

    public void setPlanejamento(String planejamento) {
        this.planejamento = planejamento;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
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
    public List<Acessar> getAcessarList() {
        return acessarList;
    }

    public void setAcessarList(List<Acessar> acessarList) {
        this.acessarList = acessarList;
    }

    @XmlTransient
    public List<Tarefa> getTarefaList() {
        return tarefaList;
    }

    public void setTarefaList(List<Tarefa> tarefaList) {
        this.tarefaList = tarefaList;
    }

    @XmlTransient
    public List<Historico> getHistoricoList() {
        return historicoList;
    }

    public void setHistoricoList(List<Historico> historicoList) {
        this.historicoList = historicoList;
    }

    public Organizacao getIdOrganizacao() {
        return idOrganizacao;
    }

    public void setIdOrganizacao(Organizacao idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    @XmlTransient
    public List<Alternativa> getAlternativaList() {
        return alternativaList;
    }

    public void setAlternativaList(List<Alternativa> alternativaList) {
        this.alternativaList = alternativaList;
    }

    @XmlTransient
    public List<Criterio> getCriterioList() {
        return criterioList;
    }

    public void setCriterioList(List<Criterio> criterioList) {
        this.criterioList = criterioList;
    }

    @XmlTransient
    public List<Keyword> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<Keyword> keywordList) {
        this.keywordList = keywordList;
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
        if (!(object instanceof Problema)) {
            return false;
        }
        Problema other = (Problema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Problema[ id=" + id + " ]";
    }
    
}
