/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import EJB.PersonRegistry;
import EJB.SchoolRegistry;
import Model.Person;
import java.io.Serializable;
import Model.School;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;

/**
 * Bean for the schoolPage.xhtml page.
 *
 * @author Malla
 */
@SessionScoped
@Named("schoolpage")
public class SchoolPageBean implements Serializable {

    @EJB
    private SchoolRegistry schoolreg;
    @EJB
    private PersonRegistry persreg;
    private School school;
    Person p;
    private List<Person> contacts;
    private String delete;
    private String check;

    /**
     * Initiates the School Page, decides which school it is to show, based on
     * the url.
     */
    @PostConstruct
    public void init() {
        delete = "false";
        Map<String, String> params = (Map<String, String>) FacesContext.
                getCurrentInstance().getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String param = params.get("school");
        school = schoolreg.getByName(param).get(0);
        System.out.println("* Skolinfo på första obj:" + school);
        p = new Person();
        school.getContactPersons().size(); //Only have this here because otherwise the contacts are not instansiated for some reason(?).
        contacts = school.getContactPersons();

    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School s) {
        this.school = s;
    }

    /**
     * Had issues with this bean, as it was problematic having it both
     * SessionScoped or RequestScoped. We therefore added the String check, for
     * which the getter checks if the attribute School is according to the field
     * in the URL 'school'. If not, it updates the attribute school. We know
     * that this is smelly code, but as we had troubles no matter how we tried
     * to fix the code in a nice way, we have this as a solution for now.
     *
     * @return check, which is always an empty String.
     */
    public String getCheck() {
        Map<String, String> params = (Map<String, String>) FacesContext.
                getCurrentInstance().getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String param = params.get("school");
        if (school == null || school.getName() != param) {
            List<School> skolor = schoolreg.getByName(param);
            school = schoolreg.getByName(param).get(0);
            school.getContactPersons().size(); //Only have this here because otherwise the contacts are not instansiated for some reason(?).
            contacts = school.getContactPersons();
        }
        return check;
    }

    public void setCheck(String s) {
        this.check = " ";
    }

    public List<Person> getContacts() {
        return contacts;
    }

    public void setContacts(List<Person> s) {
        this.contacts = s;
    }

    public Person getPerson() {
        return p;
    }

    public void setPerson(Person p) {
        this.p = p;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String b) {
        this.delete = b;
    }

    /**
     * Creates a new person and adds to the school. Method is currently NOT
     * checking for duplicates in the contact list. This is because the class
     * Person currently is not implemented to hold more than one field for
     * email, phone, address etc.
     *
     * @param event contains the information about the person to be added.
     */
    public void create(ActionEvent event) {
        school.getContactPersons().size();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean created = false;
        /*
         * Could implement to check if contact lready exists here, but as there
         * currently is no way to add a second number/email/address to an object
         * Person, we willnot implment this function now.
         */
        if (/*check if person already exists in registry*/false) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Creation Error", "Username already exists!");
        } else {
            created = true;
            Person newcontact = new Person(p.getName(), p.getMail(), p.getPhoneNbr(), p.getAddress());
            school.getContactPersons().size();
            school.getContactPersons().add(newcontact);
            schoolreg.update(school);
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Success", "Contact created");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("created", created);
    }

    /*
     * Function edits the information about the school and updates the database 
     * via the Schoolregistry
     */
    public void edit(ActionEvent event) {
        schoolreg.update(school);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("schoolpage.xhtml?school=" + school.getName());
        } catch (IOException ex) {
            Logger.getLogger(SchoolListBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Function removes the school from the database and redirects the user to 
     * the page schools.xhtml via faces-config.
     */
    public String removethis() {
        schoolreg.remove(school.getId());
        return "Schools";
    }

    public void removeContact(Long id) {
        System.out.println("------ REMOVING person with ID " + id);
        Person p = persreg.find(id);
        if (school.getContactPersons().remove(p)) {
            System.out.println("- removed contact from school -------");
            schoolreg.update(school);
            persreg.remove(id);
            System.out.println("- removed contact from db -------");
        }
        System.out.println("------ REMOVED TUTOR -------");
    }

    /*
     * Function called, when user wants to view detailed information about a 
     * contact person to the school.
     */
    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Name:" + ((Person) event.getData()).getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
