/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BB;


import Model.Account;
import Model.School;
import EJB.SchoolRegistry;
import EJB.UserRegistry;
import Model.Worker;
import EJB.WorkerRegistry;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.inject.Named;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.*;

/**
 *
 * @author kristofferskjutar
 */
@SessionScoped
@Named("menuBean")
public class MenuBean implements Serializable {
    
  
    
    @EJB
    private SchoolRegistry registry;
    
    
    @EJB
    private UserRegistry uReg;
    
    private ExpressionFactory factory =   FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
    private MethodExpression exp = factory.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), "#{loginBean.logout}", null, new Class[]{});
    
    
    

    
    public MenuModel getModel() {
        MenuModel model = new DefaultMenuModel();
        MenuItem menuItem = new MenuItem();
        menuItem.setValue("MyPage");
        menuItem.setOutcome("MyPage");
        menuItem.setId("MyPage");
        model.addMenuItem(menuItem);

   
        menuItem = new MenuItem();
        menuItem.setValue("Schools");
        menuItem.setOutcome("Schools");
        model.addMenuItem(menuItem);

        menuItem = new MenuItem();
        menuItem.setValue("Tutors");
        menuItem.setOutcome("Tutors");
        menuItem.setId("Tutors");
        model.addMenuItem(menuItem);
        String username =  (String)FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("username");
        Account a = uReg.getByName(username).get(0);
        if(a.getPerson().isAdmin())
        {
            menuItem = new MenuItem();
            menuItem.setValue("Admin");
            menuItem.setOutcome("Admin");
            model.addMenuItem(menuItem);
        }
       
       menuItem = new MenuItem();
       menuItem.setValue("Logout");
       menuItem.setId("Logout");
       menuItem.setActionExpression(exp);
       model.addMenuItem(menuItem);
        return model;
    }
    
}
