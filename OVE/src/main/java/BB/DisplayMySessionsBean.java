/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;

import EJB.SessionRegistry;
import EJB.UserRegistry;
import Model.Account;
import Model.Session;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;

/**
 *
 * @author kristofferskjutar
 */
@RequestScoped
@Named("SessionsSchedule")
public class DisplayMySessionsBean {

    @EJB
    private SessionRegistry sesReg;
    @EJB
    private UserRegistry userReg;
    private DefaultScheduleModel eventModel;

    public DisplayMySessionsBean() {
        eventModel = new DefaultScheduleModel();
    }

    /**
     * Load current users event to the scheduleModel
     */
    @PostConstruct
    public void init() {
        Long modelId = (Long) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("id");
        Account a = userReg.find(modelId);
        List<Session> sesList = sesReg.getWorkersSessions(a.getPerson().getId());

        for (Session s : sesList) {
            getEventModel().addEvent(new DefaultScheduleEvent(s.getSchool().getName() + "\n" + s.getStartTime() + "\n" + s.getEndTime(), s.getStartTime(), s.getEndTime()));
        }
    }

    /**
     * @return the eventModel
     */
    public DefaultScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(DefaultScheduleModel eventModel) {
        this.eventModel = eventModel;
    }
}
