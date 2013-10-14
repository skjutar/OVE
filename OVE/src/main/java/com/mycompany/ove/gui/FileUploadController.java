/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.gui;


import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
import javax.inject.Named;
  
import org.primefaces.model.UploadedFile;  
 
@Named("fileUploadC")
@SessionScoped
public class FileUploadController implements Serializable{  
  
    private UploadedFile file;  
  
    public UploadedFile getFile() {  
        return file;  
    }  
  
    public void setFile(UploadedFile file) {  
        this.file = file;  
    }  
  
    public void upload() {  
        if(file != null) { 
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        }  
        else
        {
            FacesMessage msg = new FacesMessage("NO PICHTURE UPLOADED");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        }
    }  
}  
              