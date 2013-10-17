/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import EJB.SchoolRegistry;
import java.beans.*;
import java.io.Serializable;
import Model.School;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Malla
 */
@RequestScoped
@Named("schoolpage")
public class SchoolPageBean implements Serializable {

    @EJB
    private SchoolRegistry registry;
    private School school;

    /**
     * Initiates the School Page, decides which school it is to show, based on
     * the url.
     */
    @PostConstruct
    public void init() {
        System.out.println("*************************************************");
        System.out.println("**********In init() method in ScholPageBean******");
        System.out.println("*");
        Map<String, String> params = (Map<String, String>) FacesContext.
                getCurrentInstance().getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String param = params.get("school");
        System.out.println("* name of school in URL: " + param);
        System.out.println("* Number of schools in database :" + registry.getCount());
        List<School> skolor = registry.getByName(param);
        System.out.println("* All results when searched for " + param + ":   " + skolor);

        school = registry.getByName(param).get(0);
        System.out.println("* Skolinfo på första obj:" + school);
        System.out.println("*");
        System.out.println("**********Done in init() method******************");
        System.out.println("*************************************************");
    }

    public void setSchool(School s) {
        this.school = s;
    }

    public School getSchool() {
        return school;
    }
}
