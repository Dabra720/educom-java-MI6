package nu.educom.MI6;

//import java.awt.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Agent {
    private int id;
    private final int serviceNumber;
    private String passPhrase = "For ThE Royal QUEEN";
    private boolean active;
    private LocalDate license_to_kill_date = null;
    private ArrayList loginAttemptList;

    public Agent(int serviceNumber){
        this.serviceNumber = serviceNumber;
//        this.serviceNumber = convertNumber(serviceNumber);
        System.out.println("New agent created, servicenr: " + getFormattedServiceNumber());
    }
    public void setId(int id){
        this.id = id;
    }

    public String getPassPhrase(){
        return passPhrase;
    }
    public void setPassPhrase(String newPass){
        this.passPhrase = newPass;
    }

    public void setActive(boolean bool){
        this.active = bool;
    }
    public boolean isActive(){
        return this.active;
    }

    public void setLicenseToKill(LocalDate date){
            license_to_kill_date = date;
    }

    public int getServiceNumber(){
        return this.serviceNumber;
    }

    public String getFormattedServiceNumber(){
        return String.format("%03d",serviceNumber);
    }

    public ArrayList getLoginAttemptList(){
        return loginAttemptList;
    }
    public void setLoginAttempts(ArrayList loginAttempts){
        loginAttemptList = loginAttempts;
    }

}
