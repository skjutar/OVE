/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.gui;


import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
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
        menuItem.setId("MyPage");
        model.addMenuItem(menuItem);
        
        Submenu submenu = new Submenu();
        submenu.setLabel("Scools");
        menuItem = new MenuItem();
        menuItem.setValue("School1");
        menuItem.setId("School1");
        submenu.getChildren().add(menuItem);
        
        model.addSubmenu(submenu);
        
        menuItem = new MenuItem();
        menuItem.setValue("Tutors");
        menuItem.setOutcome("Tutors");
        menuItem.setId("Tutors");
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
        ExpressionFactory factory =   FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
        MethodExpression exp = factory.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), "#{loginBean.logout}", null, new Class[]{});
       //MethodExpressionActionListener actionListener = new MethodExpressionActionListener(exp);
       menuItem = new MenuItem();
       menuItem.setValue("Logout");
       menuItem.setId("Logout");
       menuItem.setActionExpression(exp);
       model.addMenuItem(menuItem);
    }
    
    public MenuModel getModel() {
        return model;
    }
    
}
