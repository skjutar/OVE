/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author kristofferskjutar
 */
public class ModelFactory {
    
   public static Model getModel(String persistenceUnitName)
   {
      Model m = new Model(persistenceUnitName);
      // Person p = new Person(920129L, "Kristoffer Skjutar", "kristoffer.skjutar@hotmail.com", "0707434474", "Studiegången 16");
      // Account u = new Account(p, "admin", "admin");
      // m.getUserRegistry().add(u);
       return m;
   }
    
}