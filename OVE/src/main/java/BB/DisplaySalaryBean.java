package BB;  
  
import EJB.SessionRegistry;
import EJB.UserRegistry;
import Model.Account;
import Model.Session;
import Model.Worker;
import java.io.Serializable;  
import java.util.ArrayList;  
import java.util.Calendar;
import java.util.List;  
import java.util.UUID;  
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
  
//import org.primefaces.examples.domain.Car;  

@Named("carouselBean")
@RequestScoped
public class DisplaySalaryBean implements Serializable {  
 
    private static String[] staticMonths;
        static {  
        staticMonths = new String[12];  
        staticMonths[0] = "January";  
        staticMonths[1] = "Febuary";  
        staticMonths[2] = "Mars";  
        staticMonths[3] = "April";  
        staticMonths[4] = "May";  
        staticMonths[5] = "June";  
        staticMonths[6] = "July";  
        staticMonths[7] = "August";  
        staticMonths[8] = "September";  
        staticMonths[9] = "October"; 
        staticMonths[10] = "November";
        staticMonths[11] = "December";
        }
      
    @EJB
    private SessionRegistry sesReg;
      
    @EJB
    private UserRegistry userReg;
    
    private List<Month> months;
      
    public DisplaySalaryBean() {  
        months = new ArrayList<Month>();    
    }  
    
    /**
     * Loads a users monthly working hours, number of events and salary
     */
    @PostConstruct
    public void init()
    {
        Long modelId =  (Long)FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("id");
        Account a = userReg.find(modelId);
        List<Session> sessions = sesReg.getWorkersSessions(a.getPerson().getId());
        int salaryPerHour = ((Worker)a.getPerson()).getSalary();
        
        for(Session s : sessions)
        {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(s.getStartTime().getTime());
            String month = staticMonths[c.get(Calendar.MONTH)];
            boolean contains=false;
            
            
            Calendar startTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            startTime.setTimeInMillis(s.getStartTime().getTime());
            endTime.setTimeInMillis(s.getEndTime().getTime());
            int timeToRemove = startTime.get(Calendar.HOUR_OF_DAY);
            timeToRemove=-timeToRemove;
            endTime.add(Calendar.HOUR_OF_DAY, timeToRemove);
            
            
            for(Month m : getMonths())
            {
                if(m.getMonth().equals(month))
                {
                    contains=true;
                    int hours =  m.getHours();
                    hours+=endTime.get(Calendar.HOUR_OF_DAY);
                    m.setHours(hours);
                    int noOfSessions = m.getNoOfSessions();
                    noOfSessions++;
                    m.setNoOfSessions(noOfSessions);
                    m.setSalary(salaryPerHour*m.getHours());
                }
            }
            if(contains==false)
            {
               int hours=endTime.get(Calendar.HOUR_OF_DAY);
               Month newMonth = new Month(month, 1, hours, salaryPerHour*hours);
                getMonths().add(newMonth);
               
            }
            contains=false;
            
        }
        
    }

    /**
     * @return the months
     */
    public List<Month> getMonths() {
        return months;
    }

    /**
     * @param months the months to set
     */
    public void setMonths(List<Month> months) {
        this.months = months;
    }
      
  
  
   

   
    
    public class Month{
        private int salary;
        private int noOfSessions;
        private int hours;
        private String month;
        public Month(String month,int noOfSessions,int hours, int salary){
            this.salary=salary;
            this.hours=hours;
            this.noOfSessions=noOfSessions;
            this.month=month;
        }

       
        public int getSalary() {
            return salary;
        }

        
        public int getNoOfSessions() {
            return noOfSessions;
        }

        
        public int getHours() {
            return hours;
        }

     
        public String getMonth() {
            return month;
        }

        /**
         * @param salary the salary to set
         */
        public void setSalary(int salary) {
            this.salary = salary;
        }

        /**
         * @param noOfSessions the noOfSessions to set
         */
        public void setNoOfSessions(int noOfSessions) {
            this.noOfSessions = noOfSessions;
        }

        /**
         * @param hours the hours to set
         */
        public void setHours(int hours) {
            this.hours = hours;
        }

        /**
         * @param month the month to set
         */
        public void setMonth(String month) {
            this.month = month;
        }
        
        
    }
} 