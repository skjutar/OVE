
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.Person;
import Model.School;
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
 * @author Gustav & Malla
 */
@Stateless  // A stateless EJB
@LocalBean
public class SchoolRegistry extends AbstractDAO<School, Long> implements Serializable {

    private static final String PU = "database_pu";
    // This is JTA see persistence.xml
    @PersistenceContext(unitName = PU)
    private EntityManager em;

    public SchoolRegistry() {
        super(School.class);
    }

    @PostConstruct
    public void postContruct() {
        super.setEntitymanager(em);
    }

    /**
     * Gets schools by name
     *
     * @param name, which is searched for
     * @return list of schools with the name.
     */
    public List<School> getByName(String name) {
        //Checks if the string name contains any å, ä, ö
        if (name.contains("Ã")) {
            name = fixAAAEOE(name);
        }
        List<School> found = new ArrayList<School>();
        for (School c : getRange(0, getCount())) {
            if (c.getName().equals(name)) {
                found.add(c);

            }
        }
        return found;
    }

    /**
     * Fixes any Ã¥(å), Ã¤(ä), Ã¶(ö) characters in the parameter, and replaces
     * with the correct letter.
     *
     * @param name String which is to be 'fixed'.
     * @return the parameter name, with weird characters replaced.
     */
    private String fixAAAEOE(String name) {
        while (name.contains("Ã")) {
            int i = name.indexOf("Ã");
            StringBuilder oldsb = new StringBuilder(name);
            oldsb.deleteCharAt(i);

            char aaaeoe = ' ';
            if (oldsb.charAt(i) == '¥') {
                aaaeoe = 'å';
            } else if (oldsb.charAt(i) == '¤') {
                aaaeoe = 'ä';
            } else if (oldsb.charAt(i) == '¶') {
                aaaeoe = 'ö';
            }

            oldsb.setCharAt(i, aaaeoe);
            name = oldsb.toString();
        }
        return name;
    }

    public void addContact(School s, Person p) {
        System.out.println("***School before:" + s);
        s.getContactPersons().add(p);
        System.out.println("***School now:" + s);
    }
}
