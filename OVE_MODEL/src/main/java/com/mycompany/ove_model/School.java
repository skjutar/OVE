/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove_model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 * A class representing a School.
 * 
 * The name of a school and the adress of a school is a unique pair. 
 *
 * @author lisastenberg
 */
@Entity
public class School extends AbstractEntity {
    
    private String name;
    private String address;
    @ManyToMany
    private List<Person> contactPersons;
    @OneToOne
    private Schedule schedule;

    public School() {
    }

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public School(String name, String address, List<Person> contactPersons) {
        this.name = name;
        this.address = address;
        this.contactPersons = contactPersons;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Person> getContactPersons() {
        return contactPersons;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactPersons(List<Person> contactPersons) {
        this.contactPersons = contactPersons;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.address);
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
        final School other = (School) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "School{" + "name=" + name + ", address=" + address + '}';
    }
    
}
