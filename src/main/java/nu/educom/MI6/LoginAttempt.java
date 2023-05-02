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
    private int serviceNumber;
//    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name="login_time")
    @Convert(converter = LocalDateConverter.class)
    private LocalDateTime loginDateTime = LocalDateTime.now();
    @Column(name="success")
    private boolean success;


    public LoginAttempt(int serviceNumber){
        this.serviceNumber = serviceNumber;
    }
    public LoginAttempt(int serviceNr, boolean success){
        serviceNumber =serviceNr;
        this.success =success;
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
        return serviceNumber;
    }
    public void setLoginDateTime(LocalDateTime logDate){
        this.loginDateTime = logDate;
    }
    public LocalDateTime getLoginDateTime(){
        return loginDateTime;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }
    public boolean getSuccess(){
        return success;
    }


}
