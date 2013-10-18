/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import Account.CreateAccountBean;
import EJB.PersonRegistry;
import EJB.SchoolRegistry;
import Model.Account;
import Model.Person;
import java.beans.*;
import java.io.Serializable;
import Model.School;
import Model.Worker;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.mail.MessagingException;
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

    public void create(ActionEvent event) {
        System.out.println("*************************************");
        System.out.println("*************************************");
        System.out.println("*************************************");
        System.out.println("* KOM IN I CREATE!");
        System.out.println("* Innan:                       ******");
        System.out.println(persreg.toString());
        System.out.println("* Utskrift klar:               ******");


        //model = ModelFactory.getModel("OVE_model_pu");
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean created = false;
        if (/*kolla om kontakten finns redan*/false) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Creation Error", "Username already exists!");
        } else {
            created = true;
            Person newcontact = new Person(p.getName(), p.getMail(), p.getMail(), "-");
            System.out.println("*************************************");
            System.out.println("*************************************");
            System.out.println("*************************************");
            System.out.println("*************************************");
            System.out.println("** Person:" + newcontact);

            //persreg.add(p);
            System.out.println("* Efter:                       ******");
            System.out.println(persreg.toString());
            System.out.println("* Utskrift klar:               ******");
            school.getContactPersons().add(newcontact);
            schoolreg.update(school);
            //registry.addContact(school, p);
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Success", "Contact created");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("created", created);
    }
}
