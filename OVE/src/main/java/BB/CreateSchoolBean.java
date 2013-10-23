/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import Model.School;
import EJB.SchoolRegistry;
import Model.Person;
import Model.Session;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 * Creates a school Bean which handles the creation of a new school
 *
 * @author Malla
 */
@RequestScoped
@Named("createSchoolBean")
public class CreateSchoolBean implements Serializable {

    @EJB
    private SchoolRegistry reg;
    private String name;
    private String address;
    private int zip;
    private String city;

    /**
     * Sets name
     *
     * @param s the name which is set
     */
    public void setName(String s) {
        name = s;
    }

    /**
     * Sets address
     *
     * @param s the address which is set
     */
    public void setAddress(String s) {
        address = s;
    }

    /**
     * Sets numer
     *
     * @param i the number which is set
     */
    public void setZip(int i) {
        zip = i;
    }

    /**
     * Sets city
     *
     * @param s the city which is set
     */
    public void setCity(String s) {
        city = s;
    }

    /**
     * Gets the current value of the String name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current value of the String address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the current value of the Integer zip.
     */
    public int getZip() {
        return zip;
    }

    /**
     * Gets the current value of the String city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Is called upon by the view when a new school is being created. Creates a
     * school with the information given in the dialog and adds it to the
     * SchoolRegistry, which adds it to the database, if there does not exist a
     * school with that name already.
     *
     * @param event
     */
    public void create(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        boolean created = false;
        if (!reg.getByName(name).isEmpty()) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Creation Error", "School already exists!");
        } else {
            List<Session> sessions = new ArrayList<Session>();
            List<Person> contacts = new ArrayList<Person>();
            created = true;
            School school = new School(name, address, zip, city, sessions, contacts);
            reg.add(school);
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Success", "School created");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("created", created);
    }
}
