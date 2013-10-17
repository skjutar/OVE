package BB;

import EJB.SchoolRegistry;
import Model.School;
import Model.SchoolDataModel;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SchoolListBean implements Serializable {

    @EJB
    private SchoolRegistry registry;

    private List<String> schoolNames;
    private SchoolDataModel allSchools;
    private List<School> schoolsforList;
    private School selectedSchool;

    @PostConstruct
    public void init() {
        schoolsforList = new ArrayList<School>();

        //populateRandomCars(schoolsforList, 50);  
        schoolsforList = registry.getRange(0, registry.getCount());

        allSchools = new SchoolDataModel(schoolsforList);
        
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

    /**
     * Navigates browser to the page of a specific school, where
     * @param event has the object from which the method can decide which school
     */
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("School Selected", ((School) event.getObject()).getName());
        String name=((School) event.getObject()).getName();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("schoolpage.xhtml?school="+name);
        } catch (IOException ex) {
            Logger.getLogger(SchoolListBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    /**
     * KAN TROLIGTVIS TAS BORT
     * @param event 
     */
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((School) event.getObject()).getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
