/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import EJB.WorkerRegistry;
import Model.Worker;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author lisastenberg
 */
@Named("tutorBean")
@SessionScoped
public class TutorBean implements Serializable{
    
    @EJB
    WorkerRegistry reg;
    
    private Worker selectedTutor;
    

    
    
    public TutorBean() {
    }

    public Worker getSelectedTutor() {
        return selectedTutor;
    }
    
    public void setSelectedTutor(Worker tutor) {
        selectedTutor = tutor;
    }
    
    public void updateTutor() {
	reg.update(selectedTutor);
    }
    
    public List<Worker> getTutors() {
        return reg.getRange(0, reg.getCount());
    }
}
