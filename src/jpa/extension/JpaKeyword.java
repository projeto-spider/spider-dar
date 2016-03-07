/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extension;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import jpa.KeywordJpaController;
import model.Keyword;

/**
 *
 * @author Iuri Raiol
 */
public class JpaKeyword extends KeywordJpaController
{

    public JpaKeyword(EntityManagerFactory emf)
    {
        super(emf);
    }
    
    public List<Keyword> findKeywordsByIdProblema(int idProblema) 
    {
        EntityManager em = super.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try
        {
            List<Keyword> listKeywords = new ArrayList<>();
            
            tx.begin();
            listKeywords = em.createNamedQuery("Keyword.findByIdForeignKey",Keyword.class).
                                setParameter("idForeignKey", idProblema).
                                getResultList();
            
            return listKeywords;
        }
        finally
        {
            em.close();
        }
    }
    
    public void deleteAllKeywordsByIdProblema(int idProblema)
    {
        EntityManager em = super.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try
        {
            tx.begin();
            em.createQuery("DELETE FROM Keyword k WHERE k.problema.id = :idProb").
                    setParameter("idProb", idProblema).
                    executeUpdate();
            tx.commit();
        }
        finally
        {
            em.close();
        }
    }
}