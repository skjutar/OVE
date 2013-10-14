/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.model;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gustav
 */
public class SchoolRegistry extends AbstractDAO<School, Long> implements Serializable
{
     private static final String PU = "database_pu";
     // This is JTA see persistence.xml
     @PersistenceContext(unitName = PU)
     private EntityManager em;
     
     public SchoolRegistry()
     {
         super(School.class);
     }
     @PostConstruct
     public void postContruct() {
        super.setEntitymanager(em);     
    }    
}
