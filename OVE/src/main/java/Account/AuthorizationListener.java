/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Account;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 * Checks if the user tries to reach inlogged pages when not logged in
 * @author kristofferskjutar
 */
public class AuthorizationListener implements PhaseListener {
@Override
public void afterPhase(PhaseEvent event) {
 
    FacesContext facesContext = event.getFacesContext();
    String currentPage = facesContext.getViewRoot().getViewId();
 
    boolean hasBeenLoggedIn = (currentPage.lastIndexOf("welcome.xhtml") > -1);
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
 
    if(session==null){
        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
        nh.handleNavigation(facesContext, null, "welcome");
    }
 
    else{
        Object currentUser = session.getAttribute("username");
 
    if (!hasBeenLoggedIn && (currentUser == null || currentUser == "")) {
        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
        nh.handleNavigation(facesContext, null, "welcome");
        }
    }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
 
    }
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
