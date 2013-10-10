/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove_model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * A class representing a schedule, which is several sessions for a specific school. 
 * 
 * @author lisastenberg
 */
@Entity
public class Schedule extends AbstractEntity{
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Session> sessions;

    public Schedule() {
        sessions = new LinkedList();
    }
    
    public Schedule(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.sessions);
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
        final Schedule other = (Schedule) obj;
        if (!Objects.equals(this.sessions, other.sessions)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Schedule{" + "sessions=" + sessions + '}';
    }
    
}
