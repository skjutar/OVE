package com.mycompany.ove.gui;

import java.io.Serializable;  
import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
import java.util.UUID;  
import javax.ejb.EJB;
import javax.inject.Named;
  
import org.primefaces.event.SelectEvent;  
import org.primefaces.event.UnselectEvent; 
import com.mycompany.ove.model.SchoolRegistry;
import com.mycompany.ove.model.School;
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
  
        allSchools = new  SchoolDataModel(schoolsforList);  
    }  
  
    /*private void populateRandomCars(List<Car> list, int size) {  
        for(int i = 0 ; i < size ; i++)  
            list.add(new Car(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));  
    }*/  
  
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