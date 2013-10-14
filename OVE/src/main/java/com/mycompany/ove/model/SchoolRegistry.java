/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.model;

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
     * @param name, which is searched for
     * @return list of schools with the name.
     */
    public List<School> getByName(String name) {
        List<School> found = new ArrayList<School>();
        for (School s : getRange(0, getCount())) {
            if (s.getName().equals(name)) {
                found.add(s);
            }
        }
        return found;
    }
}
