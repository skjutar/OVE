package BB;

import EJB.SchoolRegistry;
import Model.School;
import CustomPrimefaceModel.SchoolDataModel;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Malla
 */
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
     *
     * @param event has the object from which the method can decide which school
     */
    public void onRowSelect(SelectEvent event) {
        String name = ((School) event.getObject()).getName();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("schoolpage.xhtml?school=" + name);
        } catch (IOException ex) {
            Logger.getLogger(SchoolListBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the schoolsforList
     */
    public List<School> getSchoolsforList() {
        return schoolsforList;
    }

    /**
     * @param schoolsforList the schoolsforList to set
     */
    public void setSchoolsforList(List<School> schoolsforList) {
        this.schoolsforList = schoolsforList;
    }

    /**
     * @return the schoolNames
     */
    public List<String> getSchoolNames() {
        return schoolNames;
    }

    /**
     * @param schoolNames the schoolNames to set
     */
    public void setSchoolNames() {
        schoolNames = new ArrayList<String>();
        for (School s : schoolsforList) {
            if (!schoolNames.contains(s)) {
                schoolNames.add(s.getName());
            }
        }
    }
}
