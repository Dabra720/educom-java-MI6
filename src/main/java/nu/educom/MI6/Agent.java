package nu.educom.MI6;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Agent {
    private int id;
    private final int serviceNumber;
    private String passPhrase = "For ThE Royal QUEEN";
    private boolean active;
    private LocalDate license_to_kill_date;
//    private List<LoginAttempt> loginAttemptList;

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


}
