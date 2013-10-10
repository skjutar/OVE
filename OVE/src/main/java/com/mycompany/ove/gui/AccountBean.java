/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.gui;


import com.mycompany.ove_model.Account;
import com.mycompany.ove_model.Model;
import com.mycompany.ove_model.ModelFactory;
import com.mycompany.ove_model.Person;
import com.mycompany.ove_model.UserRegistry;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
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

@Named("accountBean")
@SessionScoped
public class AccountBean implements Serializable{
  
    private String username;  
      
    private String password;  
    
    private Model model = ModelFactory.getModel("OVE_model_pu");
    
    private int idNumber;
   
   private String name;
   
   private String adress;
   
   private String telephoneNumber;
   
   private String emailAdress;
   
      
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
   
    
    public void login(ActionEvent actionEvent) {  
        //model = ModelFactory.getModel("OVE_pu");
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean loggedIn = false;  
        UserRegistry reg = getModel().getUserRegistry();
        Account a =  reg.getAccount(username, password);
        
        if(a!=null){
                loggedIn = true; 
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);  
                Person p = a.getPerson();
                setAdress(p.getAddress());
                setEmailAdress(p.getMail());
                setTelephoneNumber(p.getPhoneNbr());
                setIdNumber(p.getIdNumber());
                setName(p.getName());
            }
        
        else{  
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");  
        }  
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn);  
    }  

    /**
     * @return the model
     */
    public Model getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Model model) {
        this.model = model;
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
    
    
    
}  


