package nu.educom.MI6;

//import java.awt.*;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="agents")
public class Agent {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    @Column(name="service_nr")
    private int serviceNumber;
    @Column(name="pass_phrase")
        private String passPhrase = "For ThE Royal QUEEN";
    @Column(name="active")
    private boolean active;
    @Temporal(TemporalType.DATE)
    @Column(name="license_to_kill_date")
    private Date license_to_kill_date = null;
//    private ArrayList loginAttemptList;

    public Agent(int serviceNumber){
        this.serviceNumber = serviceNumber;
//        this.serviceNumber = convertNumber(serviceNumber);
//        System.out.println("New agent created, servicenr: " + getFormattedServiceNumber());
    }

    public Agent(){

    }

    public int getId() {
        return id;
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

    public boolean getActive(){
        return this.active;
    }


    public void setLicenseToKill(Date date){
            license_to_kill_date = date;
    }

    public Date getLicense_to_kill(){
        return license_to_kill_date;
    }

    public int getServiceNumber(){
        return this.serviceNumber;
    }

//    public String getFormattedServiceNumber(){
//        return String.format("%03d",service_number);
//    }

    public ArrayList getLoginAttemptList(){
        ArrayList <LoginAttempt> loginAttemptList = new ArrayList<LoginAttempt>();
        return loginAttemptList;
    }
//    public void setLoginAttempts(ArrayList loginAttempts){
//        loginAttemptList = loginAttempts;
//    }

}
