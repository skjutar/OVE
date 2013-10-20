/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import EJB.WorkerRegistry;
import Model.Worker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *  Handles the salariesc for all workers
 * @author Gustav
 */
@Named("salaryBean")
@SessionScoped
public class SetSalaryBean implements Serializable
{
    @EJB
    private WorkerRegistry workerReg;
    
    private Worker w;
    private int newSalary;
    private ArrayList<Worker> workerList;
    
    public SetSalaryBean()
    {
      
    }
    
    /**
     * Iniates a list of all workers in the database
     */
    @PostConstruct
    public void postConstruct()
    {
     workerList = new ArrayList<Worker>();   
     for(Worker w: workerReg.getRange(0, workerReg.getCount()))
        {
            //List of all workers in database
            workerList.add(w);
        }
    }
    
    public ArrayList<Worker> getSalary()
    {
        return workerList;    
    }
    
    /**
     * Handles the user input if the admin would like to change a tutors salary
     * @param event 
     */
    public void onCellEdit(CellEditEvent event)
    {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        int dataTableIndex = event.getRowIndex();
        newSalary = (Integer) event.getNewValue();
        Worker w = workerList.get(dataTableIndex);
        w.setSalary(newSalary);
        workerReg.update(w);
        
        FacesMessage msg = new FacesMessage("Salary is changed for: ", w.getName()); 
            FacesMessage msg2 = new FacesMessage("The salary is :" , Integer.toString(newSalary));
            FacesContext.getCurrentInstance().addMessage(null, msg); 
            FacesContext.getCurrentInstance().addMessage(null, msg2);
        
    }
}
