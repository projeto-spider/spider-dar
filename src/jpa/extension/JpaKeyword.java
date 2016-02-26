/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extension;

import javax.persistence.EntityManagerFactory;
import jpa.KeywordJpaController;

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
}