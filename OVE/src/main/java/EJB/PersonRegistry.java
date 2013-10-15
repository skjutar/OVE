/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.AbstractDAO;
import Model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kristofferskjutar
 */
@Stateless  // A stateless EJB
@LocalBean
public class PersonRegistry  extends AbstractDAO<Person, Long>{
    
     private static final String PU = "database_pu";
     // This is JTA see persistence.xml
     @PersistenceContext(unitName = PU)
     private EntityManager emf;

    
    public PersonRegistry()
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
    
    public List<Person> getLinked(Long id)
    {
        em.getTransaction().begin();
        List <Person> list =  em.createQuery("select t from PERSON t where t.PERSON_ID = "+id).getResultList();
        em.close();
        return list;
    }
    
    
}

