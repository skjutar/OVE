/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove_model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;






/**
 *
 * @author kristofferskjutar
 */
public class RegistryTest{
    
    static Model model;
    final static String TEST_PU = "OVE_model_pu";
  

    @Before // Run before each test
    public void before() {
        model = ModelFactory.getModel(TEST_PU);
    }
    
    @Test
    public void testUserRegistry()
    {
        //Person p = new Person(920129, "Kristoffer Skjutar", "kristoffer.skjutar@hotmail.com", "0707434474", "Studiegången 16");
        //Account u = new Account(p, "admin", "admin");
        //model.getUserRegistry().add(u);
        model.getUserRegistry().getAccount("admin", "admin");
        assertTrue(model.getUserRegistry().getByName("admin").size()==1);
    }
}
