/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.AbstractDAO;
import Model.Account;
import java.io.Serializable;
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
public class UserRegistry extends AbstractDAO<Account, Long> implements Serializable{
    
    private static final String PU = "database_pu";
    // This is JTA see persistence.xml
    @PersistenceContext(unitName = PU)
    private EntityManager em;

    public UserRegistry() {
        super(Account.class);
    }
    
    @PostConstruct
    public void postContruct() {
        super.setEntitymanager(em);     
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
        List <Account> list =  em.createQuery("select t from Account t WHERE t.userName = '" +username+"' and t.passWord = '"+ password+"'")
        .getResultList();
        if(list.isEmpty())
            return null;
        
        return list.get(0);
    }
    
   
    
    
}

