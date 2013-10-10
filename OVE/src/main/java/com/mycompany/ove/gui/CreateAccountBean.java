/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.gui;

import com.mycompany.ove_model.Account;
import com.mycompany.ove_model.Model;
import com.mycompany.ove_model.ModelFactory;
import com.mycompany.ove_model.Person;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kristofferskjutar
 */
@RequestScoped
@Named("createAccountBean")
public class CreateAccountBean {

   private String username;
   
   private String password;
   
   private int idNumber;
   
   private String name;
   
   private String adress;
   
   private String telephoneNumber;
   
   private String emailAdress;
   
   private Model model;
   

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
    public int getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(int idNumber) {
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
       model = ModelFactory.getModel("OVE_model_pu");
       RequestContext context = RequestContext.getCurrentInstance(); 
       FacesMessage msg = null;
       boolean created = false; 
       if(!model.getUserRegistry().getByName(username).isEmpty())
       {
           msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Creation Error", "Username already exists!");  
       }       
       else  
       {
            created=true;
            Person p = new Person(idNumber, name, emailAdress, telephoneNumber, adress);
            Account a = new Account(p, username, password);
            model.getUserRegistry().add(a);
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

}
