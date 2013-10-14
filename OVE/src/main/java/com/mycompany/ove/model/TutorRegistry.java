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
 * @author lisastenberg
 */
@Stateless  // A stateless EJB
@LocalBean
public class TutorRegistry extends AbstractDAO<Person, Long>{
        
     private static final String PU = "database_pu";
     // This is JTA see persistence.xml
     @PersistenceContext(unitName = PU)
     private EntityManager emf;

    
    public TutorRegistry()
    {
        super(Person.class);
    }
    
    @PostConstruct
    public void postContruct() {
        super.setEntitymanager(em);     
    }
    
    
    public List<Person> getByName(String name) {
        List<Person> found = new ArrayList<Person>();
        for (Person c : getRange(0, getCount())) {
            if (c.getName().equals(name)) {
                found.add(c);
            }
        }
        return found;
    }
    
    public Worker getTutor(Long id){
        List <Worker> list =  em.createQuery("select t from Worker t WHERE "
                + "t.id = '" + id + "'")
        .getResultList();
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
