/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.gui;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.*;

/**
 *
 * @author kristofferskjutar
 */
@RequestScoped
@Named("menuBean")
public class MenuBean {
    
    private MenuModel model = new DefaultMenuModel();
    
    public MenuBean() {
        
        MenuItem menuItem = new MenuItem();
        menuItem.setValue("MyPage");
        menuItem.setOutcome("MyPage");
        model.addMenuItem(menuItem);
        
        Submenu submenu = new Submenu();
        submenu.setLabel("Scools");
        menuItem = new MenuItem();
        menuItem.setValue("school1");
        submenu.getChildren().add(menuItem);
        
        model.addSubmenu(submenu);
        
        menuItem = new MenuItem();
        menuItem.setValue("Tutors");
        menuItem.setOutcome("Tutors");
        model.addMenuItem(menuItem);
        String username =  (String)FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("username");
        if(username.equals("admin"))
        {
            menuItem = new MenuItem();
            menuItem.setValue("Admin");
            menuItem.setOutcome("Admin");
            model.addMenuItem(menuItem);
        }
    }
    
    public MenuModel getModel() {
        return model;
    }
    
}
