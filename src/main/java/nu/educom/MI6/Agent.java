package nu.educom.MI6;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;

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
    @Column(name="license_to_kill_date")
    private LocalDate license_to_kill_date = null;

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


    public void setLicenseToKill(LocalDate date){
            license_to_kill_date = date;
    }

    public LocalDate getLicense_to_kill(){
        return license_to_kill_date;
    }

    public int getServiceNumber(){
        return this.serviceNumber;
    }

}
