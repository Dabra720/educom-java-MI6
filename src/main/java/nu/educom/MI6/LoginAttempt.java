package nu.educom.MI6;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="login_attempts")
public class LoginAttempt {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    @Column(name="service_nr")
    private int agent_id;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name="timestamp")
    private LocalDateTime loginDateTime = LocalDateTime.now();
    @Column(name="success")
    private boolean succesful;


    public LoginAttempt(int serviceNumber){
        agent_id = serviceNumber;
    }
    public LoginAttempt(int serviceNr, boolean success){
        agent_id=serviceNr;
        succesful=success;
    }
    public LoginAttempt(){

    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public int getServiceNumber(){
        return agent_id;
    }
    public void setLoginDateTime(LocalDateTime logDate){
        this.loginDateTime = logDate;
    }
    public LocalDateTime getLoginDateTime(){
        return loginDateTime;
    }

    public void setSuccesful(boolean succes){
        succesful = succes;
    }
    public boolean getSuccess(){
        return succesful;
    }


}
