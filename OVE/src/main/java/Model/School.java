/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
    private int zip;
    private String city;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Person> contacts;
    @OneToOne(cascade = CascadeType.ALL)
    private Schedule schedule;

    public School() {
    }

    public School(String name, String address, int zip, String city, List<Session> sessions, List<Person> contacts) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.contacts = contacts;
        this.schedule = new Schedule(sessions);
    }

    public School(String name, String address, List<Person> contacts) {
        this.name = name;
        this.address = address;
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public List<Person> getContactPersons() {
        return contacts;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactPersons(List<Person> contactPersons) {
        this.contacts = contactPersons;
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
        return "School{" + "name=" + name + ", address=" + address + "Contacts:" + contacts + '}';
    }
}
