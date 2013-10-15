/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gustav
 */
@Stateless  // A stateless EJB
@LocalBean
public class WorkerRegistry  extends AbstractDAO<Worker, Long>{
    
     private static final String PU = "database_pu";
     // This is JTA see persistence.xml
     @PersistenceContext(unitName = PU)
     private EntityManager em;

    
    public WorkerRegistry()
    {
        super(Worker.class);
    }
    
    @PostConstruct
    public void postContruct() {
        super.setEntitymanager(em);     
    }
    
    
    public List<Worker> getByName(String name) {
        List<Worker> found = new ArrayList<Worker>();
        for (Worker c : getRange(0, getCount())) {
            if (c.getName().equals(name)) {
                found.add(c);
            }
        }
        return found;
    }
    
    public List<Worker> getLinked(Long id)
    {
        em.getTransaction().begin();
        List <Worker> list =  em.createQuery("select t from Worker t where t.Worker_ID = "+id).getResultList();
        em.close();
        return list;
    }
    
    
}
