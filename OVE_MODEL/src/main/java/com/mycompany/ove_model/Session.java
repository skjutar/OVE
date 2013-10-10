/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove_model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;

/**
 * A class representing a Session. 
 * 
 * A Session has a starttime and an endtime. It also have one or more tutors,
 * a number of students and possible a notation.
 * 
 * @author lisastenberg
 */
@Entity
public class Session extends AbstractEntity {
    
    //private GregorianCalendar startTime;
    //private GregorianCalendar endTime;
    private int nbrOfStudents;
    private List<Worker> tutors;
    private String notation;

    public Session() {
    }

    public Session(GregorianCalendar startTime, GregorianCalendar endTime, int nbrOfStudents, List<Worker> tutors) {
        //this.startTime = startTime;
        //this.endTime = endTime;
        this.nbrOfStudents = nbrOfStudents;
        this.tutors = tutors;
    }

  //  public GregorianCalendar getStartTime() {
   //     return startTime;
   // }

    //public GregorianCalendar getEndTime() {
    //    return endTime;
    //}

    public int getNbrOfStudents() {
        return nbrOfStudents;
    }

    public List<Worker> getTutors() {
        return tutors;
    }
    
    public String getNotation() {
        return notation;
    }

    public void setStartTime(GregorianCalendar startTime) {
       // this.startTime = startTime;
    }

    public void setEndTime(GregorianCalendar endTime) {
      //  this.endTime = endTime;
    }

    public void setNbrOfStudents(int nbrOfStudents) {
        this.nbrOfStudents = nbrOfStudents;
    }

    public void setTutors(List<Worker> tutors) {
        this.tutors = tutors;
    }
    
    public void setNotation(String notation) {
        this.notation = notation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
       // hash = 53 * hash + Objects.hashCode(this.startTime);
       // hash = 53 * hash + Objects.hashCode(this.endTime);
       // hash = 53 * hash + Objects.hashCode(this.tutors);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Session other = (Session) obj;
       // if (!Objects.equals(this.startTime, other.startTime)) {
        //    return false;
       // }
       // if (!Objects.equals(this.endTime, other.endTime)) {
        //    return false;
       // }
       // if (!Objects.equals(this.tutors, other.tutors)) {
        //    return false;
       // }
       return true;
    }
}
