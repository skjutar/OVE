package com.mycompany.ove_model;

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

    
protected EntityManagerFactory emf;
private  Class<T> clazz;


protected AbstractDAO(Class<T> clazz, String puName) {
        this.clazz = clazz;
        emf = Persistence.createEntityManagerFactory(puName);
  }
    
    
   
    public void add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Nulls not allowed");
        }
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try 
        {       
            em.persist(t);
            em.getTransaction().commit();
            
        } catch(Exception e)
        {
            System.out.println(e);
        } finally {
            if(em != null)
                em.close();
        }
    }

    
    public void remove(K id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            T t = em.getReference(clazz, id);
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            //TODO;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    
    public T update(T t) {
        EntityManager em = null;
        T result=null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            result = em.merge(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
           
        } finally {
            if (em != null) {
                em.close();
                return result;
            }
        }
        return result;
    }

    
    public T find(K id) {
      EntityManager em = emf.createEntityManager();
        T t = em.find(clazz, id);
        return t;
    }

    
    public List<T> getRange(int first, int nItems) {
        // From inclusive, to exclusive
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List <T> list =  em.createQuery("select t from " + clazz.getSimpleName()+ " t").getResultList();
        em.close();
        return list.subList(first, first + nItems);
    }
    
    
    public int getCount() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List <T> list =  em.createQuery("select t from " + clazz.getSimpleName()+ " t").getResultList();
        System.out.println(list.toString());
        em.close();
        return list.size();
    }
    
    

    
}
