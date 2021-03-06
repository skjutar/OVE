/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 * A class representing a Session.
 *
 * A Session has a starttime and an endtime. It also have one or more tutors, a
 * number of students and possible a notation.
 *
 * @author lisastenberg
 */
//@Embeddable
@Entity
public class Session extends AbstractEntity {

    // private GregorianCalendar startTime;
    //  private GregorianCalendar endTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date startTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date endTime;
    private int nbrOfStudents;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Worker> tutors;
    private String notation;
    @ManyToOne
    private School school;

    public Session() {
    }

    public Session(Date startTime, Date endTime, int nbrOfStudents, List<Worker> tutors) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.nbrOfStudents = nbrOfStudents;
        this.tutors = tutors;
    }

    public Session(School s, Date startTime, Date endTime, int nbrOfStudents, List<Worker> tutors, String notation) {
        this.school = s;
        this.startTime = startTime;
        this.endTime = endTime;
        this.nbrOfStudents = nbrOfStudents;
        this.tutors = tutors;
        this.notation = notation;
    }

    public Session(School s, Long id, Date startTime, Date endTime, int nbrOfStudents, List<Worker> tutors, String notation) {
        super(id);
        this.school = s;
        this.startTime = startTime;
        this.endTime = endTime;
        this.nbrOfStudents = nbrOfStudents;
        this.tutors = tutors;
        this.notation = notation;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getNbrOfStudents() {
        return nbrOfStudents;
    }

    public List<Worker> getTutors() {
        return tutors;
    }

    public String getNotation() {
        return notation;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

        return true;
    }

    /**
     * @return the school
     */
    public School getSchool() {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(School school) {
        this.school = school;
    }
}
