/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.gui;

import com.mycompany.ove.model.School;
import com.mycompany.ove.model.SchoolRegistry;
import com.mycompany.ove.model.Session;
import com.mycompany.ove.model.Worker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;

import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Gustav
 */
@Named("scheduleBean")
@RequestScoped
public class ScheduleBean implements Serializable
{
  //  @EJB
 //   private SchoolRegistry reg;
    
    private ScheduleModel eventModel; 
    private List<School> schoolList;

    private List<Session> sessionList;
    private List<Worker> workerList;
    private int numberOfStudents;
    private String notation;
    private int startTime,endTime;
      
    //Overide this to handle events,   comments and time 
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    public ScheduleBean() {  
        //Get a list of sessions from somewhere 
        sessionList = new ArrayList<Session>();
        workerList = new ArrayList<Worker>();
        eventModel = new DefaultScheduleModel();  
    //    schoolList =  reg.getRange(0, reg.getCount());
 //       createSessions(schoolList);
     //  schoolList.  //     eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));  
        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));  
        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));  
        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm())); 
        
    }  
 /*  private void createSessions(List<School> schoolList)
    {
        //list of schools
        for(School forSchool: schoolList)
       {    
           //Sessions for this school
         sessionList =  forSchool.getSchedule().getSessions();
         //List of sessions
         for(Session forSession: sessionList)
         {      //Create an new event for this session
             eventModel.addEvent(new DefaultScheduleEvewnt(forSchool.getName(),forSession.getStartTime()
                     ,forSession.getEndTime()));
                     forSession
         }
         
       }
    } */
    public Date getRandomDate(Date base) {  
        Calendar date = Calendar.getInstance();  
        date.setTime(base);  
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month  
          
        return date.getTime();  
    }  
      
    public Date getInitialDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);  
          
        return calendar.getTime();  
    }  
      
    public ScheduleModel getEventModel() {  
        return eventModel;  
    }  
      
    private Calendar today() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);  
        return calendar;  
    }  
      
    private Date previousDay8Pm() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);  
        t.set(Calendar.HOUR, 8);  
          
        return t.getTime();  
    }  
      
    private Date previousDay11Pm() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);  
        t.set(Calendar.HOUR, 11);  
          
        return t.getTime();  
    }  
      
    private Date today1Pm() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.HOUR, 1);  
          
        return t.getTime();  
    }  
      
    private Date theDayAfter3Pm() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);       
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.HOUR, 3);  
          
        return t.getTime();  
    }  
  
    private Date today6Pm() {  
        Calendar t = (Calendar) today().clone();   
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.HOUR, 6);  
          
        return t.getTime();  
    }  
      
    private Date nextDay9Am() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.AM_PM, Calendar.AM);  
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);  
        t.set(Calendar.HOUR, 9);  
          
        return t.getTime();  
    }  
      
    private Date nextDay11Am() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.AM_PM, Calendar.AM);  
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);  
        t.set(Calendar.HOUR, 11);  
          
        return t.getTime();  
    }  
      
    private Date fourDaysLater3pm() {  
        Calendar t = (Calendar) today().clone();   
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);  
        t.set(Calendar.HOUR, 3);  
          
        return t.getTime();  
    }  
      
    public ScheduleEvent getEvent() {  
        return event;  
    }  
  
    public void setEvent(ScheduleEvent event) {  
        this.event = event;  
    }  
      
    public void addEvent(ActionEvent actionEvent) {  
        if(event.getId() == null)  
            eventModel.addEvent(event);  
        else  
            eventModel.updateEvent(event);  
          
        event = new DefaultScheduleEvent();  
    }  
      //Trycker event dialogen
    public void onEventSelect(SelectEvent selectEvent) {  
        event = (ScheduleEvent) selectEvent.getObject();  
    }  
      
    public void onDateSelect(SelectEvent selectEvent) {  
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());  
    }  
      
    public void onEventMove(ScheduleEntryMoveEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }  
      
    public void onEventResize(ScheduleEntryResizeEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }  
      
    private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
}  

