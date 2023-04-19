package nu.educom.MI6;

import java.time.LocalDateTime;

public class LoginAttempt {
    private int id;
    private int agent_id;
    private LocalDateTime loginDateTime;
    private boolean succesful;

    public LoginAttempt(int serviceNumber){
        agent_id = serviceNumber;
    }
    public LoginAttempt(int serviceNr, boolean success){
        agent_id=serviceNr;
        succesful=success;
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
