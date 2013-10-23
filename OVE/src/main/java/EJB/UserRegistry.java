/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

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
public class UserRegistry extends AbstractDAO<Account, Long> implements Serializable {

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

    /**
     * Returns an account by searching for the accountName
     *
     * @param userName the Name which will be search for
     * @return the matching account or null if the account doesnt exist
     */
    public List<Account> getByName(String userName) {
        List<Account> found = new ArrayList<Account>();
        for (Account c : getRange(0, getCount())) {
            if (c.getUserName().equals(userName)) {
                found.add(c);
            }
        }
        return found;
    }

    /**
     * Returns the accounts related to a specific person
     *
     * @param personId The id of the person
     * @return A list of accounts
     */
    public Account getAccountRelatedToPerson(Long personId) {
        List<Account> found = new ArrayList<Account>();
        for (Account c : getRange(0, getCount())) {
            if (c.getPerson().getId().equals(personId)) {
                found.add(c);
            }
        }
        if (found.size() > 0) {
            return found.get(0);
        }
        return null;
    }

    /**
     * Returns an account by username and password
     *
     * @param username the account user name
     * @param password the account password
     * @return the account or null if the account didnt exist
     */
    public Account getAccount(String username, String password) {
        List<Account> list = em.createQuery("select t from Account t WHERE t.userName = '" + username + "' and t.passWord = '" + password + "'")
                .getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
