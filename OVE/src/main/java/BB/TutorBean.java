/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import EJB.UserRegistry;
import EJB.WorkerRegistry;
import Model.Account;
import Model.Worker;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author lisastenberg
 */
@Named("tutorBean")
@SessionScoped
public class TutorBean implements Serializable {

    @EJB
    WorkerRegistry workerReg;
    @EJB
    UserRegistry userReg;
    private Worker selectedTutor;

    public TutorBean() {
    }

    public Worker getSelectedTutor() {
        return selectedTutor;
    }

    public void setSelectedTutor(Worker tutor) {
        selectedTutor = tutor;
    }

    public void removeTutor(Long id) {
        Account account = userReg.getAccountRelatedToPerson(id);
        if (account != null) {
            userReg.remove(account.getId());
        }
        workerReg.remove(id);
    }

    public List<Worker> getTutors() {
        return workerReg.getRange(0, workerReg.getCount());
    }

    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Name:" + ((Worker) event.getData()).getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
