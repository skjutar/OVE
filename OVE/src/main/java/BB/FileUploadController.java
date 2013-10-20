/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
  
import org.primefaces.model.UploadedFile;  
 
/**
 * uploads an image from url to be used as profil picture
 * @author Gustav and Kristoffer Skjutar
 */
@Named("fileupload")
@SessionScoped
public class FileUploadController implements Serializable{  
  
    private UploadedFile file;  
  
    public UploadedFile getFile() {  
        return file;  
    }  
  
    public void setFile(UploadedFile file) {  
        this.file = file;  
    }  
  
    /**
     * prints out a specific message depending on if a correct url has been set 
     * when pushing the savebutton
     */
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
    
    /**
    * Handles the download of the url
    * @param event 
    */
    public void handleFileUpload(FileUploadEvent event) {
        try {
            File targetFolder = new File("/var/uploaded/images");
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    event.getFile().getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

    
    

              