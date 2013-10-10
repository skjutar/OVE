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
public final class UserRegistry extends AbstractDAO<Account, Long>{
    
    public static UserRegistry newInstance(String puName) {
        return new UserRegistry(puName);
        
    }
    
    public UserRegistry(String puName)
    {
        super(Account.class, puName);
    }
    
    
    public List<Account> getByName(String name) {
        List<Account> found = new ArrayList<Account>();
        for (Account c : getRange(0, getCount())) {
            if (c.getUserName().equals(name)) {
                found.add(c);
            }
        }
        return found;
    }
    
    public Account getAccount(String username, String password)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List <Account> list =  em.createQuery("select t from Account t WHERE t.userName = '" +username+"' and t.passWord = '"+ password+"'")
        .getResultList();
        em.close();
        if(list.isEmpty())
            return null;
        
        return list.get(0);
    }
    
   
    
    
}

