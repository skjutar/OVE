/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import EJB.SchoolRegistry;
import java.beans.*;
import java.io.Serializable;
import Model.School;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Malla
 */
@SessionScoped
@Named("schoolpage")
public class SchoolPageBean implements Serializable {

    @EJB
    private SchoolRegistry registry;
    private School school;
    
    /**
     * Initiates the School Page, decides which school it is to show, 
     * based on the url.
     */
    @PostConstruct
    public void init() {

        Map<String, String> params = (Map<String, String>) FacesContext.
                getCurrentInstance().getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String param = params.get("school");
        school = registry.getByName(param).get(0);
    }

    public void setSchool(School s) {
        this.school = s;
    }

    public School getSchool() {
        return school;
    }
}
