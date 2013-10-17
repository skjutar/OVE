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
    
    private List<String> schoolNames;
    
    @PostConstruct
    public void init() {  
        setSchoolsforList(new ArrayList<School>());  
          
        //populateRandomCars(schoolsforList, 50);  
        setSchoolsforList(registry.getRange(0, registry.getCount()));
  
        allSchools = new SchoolDataModel(getSchoolsforList());  
        
        setSchoolNames();
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

    /**
     * @return the schoolsforList
     */
    public List<School> getSchoolsforList()
    {
        return schoolsforList;
    }

    /**
     * @param schoolsforList the schoolsforList to set
     */
    public void setSchoolsforList(List<School> schoolsforList)
    {
        this.schoolsforList = schoolsforList;
    }

    /**
     * @return the schoolNames
     */
    public List<String> getSchoolNames()
    {
        return schoolNames;
    }

    /**
     * @param schoolNames the schoolNames to set
     */
    public void setSchoolNames()
    {
        schoolNames = new ArrayList<String>();
        for(School s: schoolsforList)
        {
            if(!schoolNames.contains(s))
            {
                schoolNames.add(s.getName());
            }
       }
    }
    
} 