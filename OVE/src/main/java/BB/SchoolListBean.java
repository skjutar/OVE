package BB;

import EJB.SchoolRegistry;
import Model.School;
import Model.SchoolDataModel;
import java.io.Serializable;  
import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
import java.util.UUID;  
import javax.ejb.EJB;
import javax.inject.Named;
  
import org.primefaces.event.SelectEvent;  
import org.primefaces.event.UnselectEvent;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
  
//import org.primefaces.examples.domain.Car;  
//import org.primefaces.examples.view.CarDataModel;  
@RequestScoped
@Named("schoollist")  
public class SchoolListBean implements Serializable{  
    @EJB
    private SchoolRegistry registry;
    
    private SchoolDataModel allSchools;  
    
    private List<School> schoolsforList;  
      
    private School selectedSchool;  
  
    
    @PostConstruct
    public void init() {  
        schoolsforList = new ArrayList<School>();  
          
        //populateRandomCars(schoolsforList, 50);  
        schoolsforList=registry.getRange(0, registry.getCount());
  
        allSchools = new SchoolDataModel(schoolsforList);  
    }  
  
 
  
    public School getSelectedSchool() {  
        return selectedSchool;  
    }  
    public void setSelectedCar(School selectedSchool) {  
        this.selectedSchool = selectedSchool;  
    }  
  
    public ListDataModel<School> getAllSchools() {  
        return allSchools;  
    }  
  
    public String onRowSelect(SelectEvent event) {  
        /*FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/OVE/faces/jsf/schools/schoolpage.xhtml");*/
        System.out.println("HEJHEJ!");
        FacesMessage msg = new FacesMessage("School Selected", ((School) event.getObject()).getName());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
       /*         NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
        nh.handleNavigation(facesContext, null, "welcome");
        */
        return null;
        
    }  
  
    public void onRowUnselect(UnselectEvent event) {  
        //FacesMessage msg = new FacesMessage("Car Unselected", ((Car) event.getObject()).getModel());  
  
        //FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
} 