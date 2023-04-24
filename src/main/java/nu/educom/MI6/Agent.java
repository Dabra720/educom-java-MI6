package nu.educom.MI6;

//import java.awt.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table( name="agents")
public class Agent {
    private int id;
    private final int service_number;
    private String pass_phrase = "For ThE Royal QUEEN";
    private boolean active;

    private Date license_to_kill_date = null;
    private ArrayList loginAttemptList;
    private String formattedServiceNumber;

    public Agent(int serviceNumber){
        this.service_number = serviceNumber;
//        this.serviceNumber = convertNumber(serviceNumber);
//        System.out.println("New agent created, servicenr: " + getFormattedServiceNumber());
    }

    @Id
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    @Column(name="pass_phrase")
    public String getPassPhrase(){
        return pass_phrase;
    }
    public void setPassPhrase(String newPass){
        this.pass_phrase = newPass;
    }

    public void setActive(boolean bool){
        this.active = bool;
    }
    @Column(name="active")
    public boolean getActive(){
        return this.active;
    }

    public void setLicenseToKill(Date date){
            license_to_kill_date = date;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="license_to_kill_date")
    public Date getLicense_to_kill(){
        return license_to_kill_date;
    }

    public int getServiceNumber(){
        return this.service_number;
    }

    public void setFormattedServiceNumber(){
        formattedServiceNumber = String.format("%03d",service_number);
    }
    public String getFormattedServiceNumber(){
        return String.format("%03d",service_number);
    }

    public ArrayList getLoginAttemptList(){
        return loginAttemptList;
    }
    public void setLoginAttempts(ArrayList loginAttempts){
        loginAttemptList = loginAttempts;
    }

}
