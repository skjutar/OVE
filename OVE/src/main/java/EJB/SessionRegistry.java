/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.AbstractDAO;
import Model.Session;
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
 * @author Gustav
 */
@Stateless  // A stateless EJB
@LocalBean
public class SessionRegistry extends AbstractDAO<Session, Long> implements Serializable
{
     private static final String PU = "database_pu";
     // This is JTA see persistence.xml
     @PersistenceContext(unitName = PU)
     private EntityManager em;
     
     public SessionRegistry()
     {
         super(Session.class);
     }
     @PostConstruct
     public void postContruct() {
        super.setEntitymanager(em);  
     }  
     
}