/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.model.DefaultScheduleEvent;

/**
 *
 * @author kristofferskjutar
 */
public class ScheduleEvent extends DefaultScheduleEvent {
    public String notation;
    public List<Worker> workerList;
    public String SchoolName;
    public int numberOfStudents;
    private Long modelId;

    public ScheduleEvent(String string, Date date, Date date0) {
         super(string, date, date0);        
    }
    public ScheduleEvent()
    {
        super();
    }

    /**
     * @return the notation
     */
    public String getNotation() {
        return notation;
    }

    /**
     * @param notation the notation to set
     */
    public void setNotation(String notation) {
        this.notation = notation;
    }

    /**
     * @return the workerList
     */
    public List<Worker> getWorkerList() {
        return workerList;
    }
    

    /**
     * @param workerList the workerList to set
     */
    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }

    /**
     * @return the SchoolName
     */
    public String getSchoolName() {
        return SchoolName;
    }

    /**
     * @param SchoolName the SchoolName to set
     */
    public void setSchoolName(String SchoolName) {
        this.SchoolName = SchoolName;
    }

    /**
     * @return the numberOfStudents
     */
    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    /**
     * @param numberOfStudents the numberOfStudents to set
     */
    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    /**
     * @return the modelId
     */
    public Long getModelId() {
        return modelId;
    }

    /**
     * @param modelId the modelId to set
     */
    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
    
    
    
}
