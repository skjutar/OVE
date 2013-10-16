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
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
  
//import org.primefaces.examples.domain.Car;  
//import org.primefaces.examples.view.CarDataModel;  
@SessionScoped
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
  
    public void onRowSelect(SelectEvent event) {  
        //FacesMessage msg = new FacesMessage("School Selected", ((School) event.getObject()).getModel());  
  
        //FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
  
    public void onRowUnselect(UnselectEvent event) {  
        //FacesMessage msg = new FacesMessage("Car Unselected", ((Car) event.getObject()).getModel());  
  
        //FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
} 