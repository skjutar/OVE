/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import EJB.WorkerRegistry;
import Model.Worker;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Gustav
 */
@Named("salaryBean")
@SessionScoped
public class SetSalaryBean implements Serializable
{
    @EJB
    private WorkerRegistry workerReg;
        
    public SetSalaryBean()
    {
      
    }
    
    public ArrayList<Worker> getSalary()
    {
        ArrayList<Worker> workerList = new ArrayList<Worker>();
        for(Worker w: workerReg.getRange(0, workerReg.getCount()))
        {
            //List of all workers in database
            workerList.add(w);
        }
        return workerList;    
    }
    //Save salaries
    public void update(RowEditEvent event)
    {
       //      update((Worker)event.getObject());
           
                   
          Worker w = ((Worker)event.getObject());
          
          RequestContext requestContext = RequestContext.getCurrentInstance();
        //  event.
     //     requestContext.addCallbackParam("rowIndex", event.getRowIndex());
        //  w.setSalary(1337);
          workerReg.update(w);
            // Long pNumber = ((Worker)event.getObject()).getIdNumber();
            // String name = ((Worker)event.getObject()).getName();
            // Worker w = workerReg.getById(pNumber);
        //     int salary = ((Worker)event.getObject()).getSalary();
      //       w.setSalary(salary);
           //  w.setSalary(salary);
     //        workerReg.update(w);
            
            FacesMessage msg = new FacesMessage("Salary is changed for: ", ((Worker) event.getObject()).getName()); 
            FacesMessage msg2 = new FacesMessage("The salary is :" , Long.toString(((Worker) event.getObject()).getSalary()));
            FacesContext.getCurrentInstance().addMessage(null, msg); 
            FacesContext.getCurrentInstance().addMessage(null, msg2);
    }
    public void onCancel(RowEditEvent event)
    {
        
    }
}
