/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import Model.School;
import EJB.SchoolRegistry;
import Model.Person;
import Model.Session;
import java.beans.*;
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
    
    public void setName(String s){
        name=s;
    }
    
    public void setAddress(String s){
        address=s;
    }
    
    public void setZip(int i){
        zip=i;
    }
    
    public void setCity(String s){
        city=s;
    }
    
    public String getName(){
        return name;
    }
    
    public String getAddress(){
        return address;
    }
    
    public int getZip(){
        return zip;
    }
    
    public String getCity(){
        return city;
    }
    
       public void create(ActionEvent event){
           System.out.println("Kom in i create");
       RequestContext context = RequestContext.getCurrentInstance(); 
       FacesMessage msg = null;
       boolean created = false; 
       if(!reg.getByName(name).isEmpty())
       {
           msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Creation Error", "School already exists!");  
       }       
       else  
       {
           List<Session> sessions=new ArrayList<Session>();
           List<Person> contacts=new ArrayList<Person>();
            created=true;
            School school = new School(name, address, zip, city, sessions, contacts);
            reg.add(school);
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Success", "School created");
       }
       
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("created", created);  
   }
 
}
