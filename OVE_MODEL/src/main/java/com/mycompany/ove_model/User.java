/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove_model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * A class representing a User. 
 * 
 * A User is a person with a userName and a password. The username is unique.
 * 
 * @author lisastenberg
 */
@Entity
public class User {
    @OneToOne
    private Person person;
    private String userName;
    private String passWord;
    
    public User(Person person, String userName, String passWord) {
        this.person = person;
        this.userName = userName;
        this.passWord = passWord;
    }

    public Person getPerson() {
        return person;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.userName);
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
        final User other = (User) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "person=" + person + ", userName=" + userName + '}';
    }
}
