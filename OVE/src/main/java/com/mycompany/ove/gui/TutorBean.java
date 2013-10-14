/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.gui;

import com.mycompany.ove.model.Worker;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author lisastenberg
 */
@Named("tutorBean")
@SessionScoped
public class TutorBean implements Serializable{
    
    List<Worker> tutors;
    
    public TutorBean() {
        tutors = new LinkedList();
        
        tutors.add(new Worker(9001015050L, "Lisa", "lisa@mail.se", "0707777777", "Chalmers"));
        tutors.add(new Worker(9001015051L, "Madeleine", "madeleine@mail.se", "0707777778", "Chalmers"));
        tutors.add(new Worker(9001015052L, "Gustav", "gustav@mail.se", "0707777779", "Chalmers"));
        tutors.add(new Worker(9001015053L, "Kristoffer", "kristoffer@mail.se", "0707777776", "Chalmers"));
    }
    
    public Worker selectedTutor() {
        return null;
    }
    
    public List<Worker> getTutors() {
        return tutors;
    }
    
    public void addTutor(Worker tutor) {
        tutors.add(tutor);
    }
}
