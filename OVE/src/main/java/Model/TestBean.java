/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import EJB.WorkerRegistry;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author Gustav
 */
@Named("testBean")
@SessionScoped
public class TestBean implements Serializable
{
   /* @EJB
    private SessionRegistry reg; */
    
    @EJB
    private WorkerRegistry reg;
    
    private Worker Ove;
    private List<Session> sessions;
    private List<Worker> workerList;
    
    public TestBean()
    {
        
    }
    public void addWorker(ActionEvent event)
    {   
        Ove = new Worker(1232312L,"Ove Sundberg", "ove@Sundberg.se", "34324234", "parkbänken 3");
        reg.add(Ove);
        FacesMessage msg = null; 
        if(reg.getCount()==1)
        {
            msg = new FacesMessage("Worker added");
        }
        else
        {
            msg = new FacesMessage("Couldnt add worker");
        }
    }
}
