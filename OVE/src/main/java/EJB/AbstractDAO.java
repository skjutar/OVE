package EJB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A container for entities, base class for OrderBook, ProductCatalogue,
 * CustomerRegistry The fundamental common operations are here (CRUD).
 *
 * T is type for items in container K is type of id (primary key)
 *
 * @author hajo
 */
public abstract class AbstractDAO<T,K> {

    
protected EntityManager em;
private  Class<T> clazz;


protected AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
  }

protected EntityManager getEntityManager() {
        return em;
    }

    protected void setEntitymanager(EntityManager em) {
        this.em = em;
    }
    
    
   
    public void add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Nulls not allowed");
        }
        
        try 
        {       
            em.persist(t);
           
            
        } catch(Exception e)
        {
            System.out.println("Exception thrown in ABSTRACTDAO.add " +e);
        }
    }

    
    public void remove(K id) {
        try {          
            T t = em.getReference(clazz, id);
            em.remove(t);
        } catch (Exception ex) {
            //TODO;
        } finally {
            if (em != null) {
            }
        }
    }

    
    public T update(T t) {

        T result=null;
        try {
            result = em.merge(t);
        } catch (Exception ex) {
           
        } finally {
            if (em != null) {
                return result;
            }
        }
        return result;
    }

    
    public T find(K id) {
        T t = em.find(clazz, id);
        return t;
    }

    
    public List<T> getRange(int first, int nItems) {
        // From inclusive, to exclusive
        List <T> list =  em.createQuery("select t from " + clazz.getSimpleName()+ " t").getResultList();
        return list.subList(first, first + nItems);
    }
    
    
    public int getCount() {
        List <T> list =  em.createQuery("select t from " + clazz.getSimpleName()+ " t").getResultList();
        System.out.println("* ABSTRACTDAO.getCount() : " + list.size());
        return list.size();
    }
    
    

    
}
