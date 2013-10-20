/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import EJB.WorkerRegistry;
import Model.Worker;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lisastenberg
 */
@Named("addTutorBean")
@RequestScoped
public class AddTutorBean implements Serializable {
    
    @EJB
    private WorkerRegistry reg;
    
    private Long idNumber;
    private String name;
    private String mail;
    private String phoneNbr;
    private String address;
    private String picUrl;
    private int salary;
    
    public void addTutor() {
        //RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg;   
        Worker tutor =  reg.getTutor(idNumber);
        
        if(tutor == null){                
                tutor = new Worker(idNumber, name, mail, phoneNbr, address, salary);
		tutor.setPicUrl(picUrl);
                reg.add(tutor);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tutor added ", name); 
            }
        
        else{  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "This tutor already exists");  
        }  
        
        FacesContext.getCurrentInstance().addMessage(null, msg); 
	RequestContext.getCurrentInstance().reset("form:panel");
	
    }
    
    public Long getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public String getAddress() {
        return address;
    }
    
    public String getPicUrl() {
	return picUrl;
    }

    public int getSalary() {
        return salary;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPicUrl(String picUrl) {
	this.picUrl = picUrl;
    }
    
    public void setSalary(int salary) {
        this.salary = salary;
    }

 
    
 
}
