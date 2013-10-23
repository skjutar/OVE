/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.Worker;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    /**
     * returns a list of workers with a specific name
     * @param name The name to be searched for
     * @return the list of workers
     */
    public List<Worker> getByName(String name) {
        List<Worker> found = new ArrayList<Worker>();
        for (Worker c : getRange(0, getCount())) {
            if (c.getName().equals(name)) {
                found.add(c);
            }
        }
        return found;
    }
    /**
     * Returns a tutor with a specific id
     * @param id the id
     * @return the worker
     */
    public Worker getTutor(Long id){
        List <Worker> list =  em.createQuery("select t from Worker t WHERE "
                + "t.id = '" + id + "'")
        .getResultList();
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public List<Worker> getLinked(Long id)
    {
        em.getTransaction().begin();
        List <Worker> list =  em.createQuery("select t from Worker t where t.Worker_ID = "+id).getResultList();
        em.close();
        return list;
    }
    
   
   
    /**
     * Goes through the register and search for a specific worker with the same 
     * personal id number
     * @param id The personal id number which will be seached for
     * @return a list of all workers with that personal id number
     */
    public List<Worker> getByPNumber(Long id) {
        List<Worker> found = new ArrayList<Worker>();
        for (Worker c : getRange(0, getCount())) {
            if (c.getIdNumber().equals(id)) {
                found.add(c);
            }
        }
        return found;
    } 
    

}

