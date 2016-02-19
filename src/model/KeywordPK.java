/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Bleno Vale
 */
@Embeddable
public class KeywordPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "idForeignKey")
    private int idForeignKey;

    public KeywordPK() {
    }

    public KeywordPK(int id, int idForeignKey) {
        this.id = id;
        this.idForeignKey = idForeignKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdForeignKey() {
        return idForeignKey;
    }

    public void setIdForeignKey(int idForeignKey) {
        this.idForeignKey = idForeignKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idForeignKey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KeywordPK)) {
            return false;
        }
        KeywordPK other = (KeywordPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idForeignKey != other.idForeignKey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.KeywordPK[ id=" + id + ", idForeignKey=" + idForeignKey + " ]";
    }
    
}
