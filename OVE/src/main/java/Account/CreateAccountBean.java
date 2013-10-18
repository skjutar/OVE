/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Account;

import EJB.UserRegistry;
import Mail.MailBean;
import Model.Account;
import Model.Person;
import Model.Worker;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.constraints.Pattern;
import org.primefaces.context.RequestContext;

/**
 * Runned when a user creates an account
 * @author kristofferskjutar
 */
@RequestScoped
@Named("createAccountBean")
public class CreateAccountBean {

   @EJB
   private UserRegistry reg;
   
   @EJB
   private MailBean mailBean;
    
   private String username;
   
   private String password;
   
   private Long idNumber;

   private String name;
   
   private String adress;
   
   private String telephoneNumber;
   
   private String emailAdress;
   
   private String type="worker";
   
   //private Model model;
   

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the idNumber
     */
    public Long getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * @return the telephoneNumber
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * @param telephoneNumber the telephoneNumber to set
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * @return the emailAdress
     */
    public String getEmailAdress() {
        return emailAdress;
    }

    /**
     * @param emailAdress the emailAdress to set
     */
    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }
   
   public void create(ActionEvent event)
   {
       //model = ModelFactory.getModel("OVE_model_pu");
       RequestContext context = RequestContext.getCurrentInstance(); 
       FacesMessage msg = null;
       boolean created = false; 
       if(!reg.getByName(username).isEmpty())
       {
           msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Creation Error", "Username already exists!");  
       }       
       else  
       {
            created=true; 
            Worker p = new Worker(idNumber, name, emailAdress, telephoneNumber, adress);
            if(getType().equals("admin")){
                p.setAdmin(true);
            }
            else {
                p.setAdmin(false);
            }          
            Account a = new Account(p, username, password);
            reg.add(a);
           try {
               mailBean.sendEmail(emailAdress, a.getId());
           } catch (MessagingException ex) {
               Logger.getLogger(CreateAccountBean.class.getName()).log(Level.SEVERE, null, ex);
           }
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Success", "Account created");
       }
       
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("created", created);  
   }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
