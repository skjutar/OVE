/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.model;

import javax.persistence.Entity;

/**
 * A class representing a Person
 * 
 * The id number of a person is unique.
 * 
 * @author lisastenberg
 */
@Entity
public class Person extends AbstractPerson {


    public Person()
    {
        super();
    }
 
    public Person(Long idNumber, String name, String mail, String phoneNbr, String address)
    {
        super(idNumber,name,mail,phoneNbr,address);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = (int)(59 * hash + this.idNumber);
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        } else if(this == o) {
            return true;
        } else if(this.getClass() != o.getClass()) {
            return false;
        } else {
            Person tmp = (Person)o;
            return this.getIdNumber() == tmp.getIdNumber();
        }
    }

    @Override
    public String toString() {
        return "Person{" + "idNumber=" + idNumber + ", name=" + name + ", mail=" + mail + ", phoneNbr=" + phoneNbr + ", address=" + address + '}';
    }
    
}
