/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.*;

/**
 *  Abstract class for worker and person
 * @author Gustav
 */

@MappedSuperclass
public abstract class AbstractPerson extends AbstractEntity
{
    protected Long idNumber;
    protected String name;
    protected String mail;
    protected String phoneNbr;
    protected String address;
    private String picUrl;
    private boolean isAdmin;

    public AbstractPerson() {
    }
    
    public AbstractPerson(Long idNumber, String name, String mail, String phoneNbr, String address) {
        this.idNumber = idNumber;
        this.name = name;
        this.mail = mail;
        this.phoneNbr = phoneNbr;
        this.address = address;
        this.picUrl="URL";
    }
        public AbstractPerson(String name, String mail, String phoneNbr, String address) {
        this.idNumber = Math.round(Math.random()*100000);
        this.name = name;
        this.mail = mail;
        this.phoneNbr = phoneNbr;
        this.address = address;
        this.picUrl="URL";
    }
    
    public AbstractPerson(Long id, Long idNumber, String name, String mail, String phoneNbr, String address) {
        super(id);
        this.idNumber = idNumber;
        this.name = name;
        this.mail = mail;
        this.phoneNbr = phoneNbr;
        this.address = address;
        this.picUrl="URL";
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

    /**
     * @return the picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * @param picUrl the picUrl to set
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    @Override
    public String toString() {
	return "AbstractPerson + {id=" + idNumber + ", name=" + name + ", mail="
	+ mail + ", phoneNbr=" + phoneNbr + ", address=" + address + ", isAdmin= " 
	+ isAdmin + "}";
    }
}
