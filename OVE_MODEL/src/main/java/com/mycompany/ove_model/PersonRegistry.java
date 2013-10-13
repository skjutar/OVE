/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove_model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author kristofferskjutar
 */
public class PersonRegistry  extends AbstractDAO<Person, Long>{
        
    public static PersonRegistry newInstance(String puName) {
        return new PersonRegistry(puName);
        
    }
    
    public PersonRegistry(String puName)
    {
        super(Person.class, puName);
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
    
    public List<Person> getLinked(Long id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List <Person> list =  em.createQuery("select t from PERSON t where t.PERSON_ID = "+id).getResultList();
        em.close();
        return list;
    }
    
    
}

