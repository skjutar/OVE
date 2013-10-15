/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.model;

import javax.persistence.*;

/**
 *
 * @author Gustav
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class AbstractPerson extends AbstractEntity
{
     protected Long idNumber;
    protected String name;
    protected String mail;
    protected String phoneNbr;
    protected String address;

    public AbstractPerson() {
    }
    
    public AbstractPerson(Long idNumber, String name, String mail, String phoneNbr, String address) {
        this.idNumber = idNumber;
        this.name = name;
        this.mail = mail;
        this.phoneNbr = phoneNbr;
        this.address = address;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public String getAddress() {
        return address;
    }
    
    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
