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
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
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
        System.out.println("* Number of schools in database :" + schoolreg.getCount());
        List<School> skolor = schoolreg.getByName(param);
        System.out.println("* All results when searched for " + param + ":   " + skolor);

        school = schoolreg.getByName(param).get(0);
        System.out.println("* Skolinfo på första obj:" + school);
        p = new Person();

        System.out.println("*");
        System.out.println("**********Done in init() method******************");
        System.out.println("*************************************************");
    }

    public School getSchool() {
        init();
        return school;
    }

    public void setSchool(School s) {
        this.school = s;
    }

    public Person getPerson() {
        return p;
    }

    public void setPerson(Person p) {
        this.p = p;
    }

    /**
     * Creates a new person and adds to the school.
     * Method is currently NOT checking for duplicates in the contact list. This
     * is because the class Person currently is not implemented to hold more
     * than one field for email, phone, address etc.
     * @param event contains the information about the person to be added.
     */
    public void create(ActionEvent event) {
        System.out.println("*");
        System.out.println("*************************************");
        System.out.println("* IN CREATE FUNCTION!               *");
        System.out.println("*                                   *");
        school.getContactPersons().size(); //Only have this here because otherwise the contacts are not instansiated for some reason(?).
        System.out.println("* Schools contacts before:          *");
        System.out.println(school.getContactPersons().toString());

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
            Person newcontact = new Person(p.getName(), p.getMail(), p.getMail(), "-");
            System.out.println("*");
            System.out.println("* Person to add:" + newcontact);
            System.out.println("*");
            System.out.println("* School we add to:" + school);
            System.out.println("*");
            System.out.println("* Number of contacts in school:" + school.getContactPersons().size()); //HÄR initieras listan med kontaktpersoner. 
            System.out.println("*");
            System.out.println("* Schools contacts after:           *");
            System.out.println(school.getContactPersons().toString());
            school.getContactPersons().add(newcontact);
            schoolreg.update(school);
            //registry.addContact(school, p);
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Success", "Contact created");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("created", created);
        System.out.println("* DONE WITH CREATE FUNCTION!        *");
        System.out.println("*************************************");
        System.out.println("*");

    }
}
