/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove_model;

/**
 *
 * @author kristofferskjutar
 */
public class Model {
    private String persistenceUnitName;
    private UserRegistry userRegistry;
    private PersonRegistry personRegistry;
    
    public Model(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
        userRegistry = UserRegistry.newInstance(persistenceUnitName);
        personRegistry = PersonRegistry.newInstance(persistenceUnitName);
    }

    
    public UserRegistry getUserRegistry() {
        return userRegistry;
    }
    
    public PersonRegistry getPersonRegistry() {
        return personRegistry;
    }

 
    
}
