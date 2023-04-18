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

    public void setId(int id){
        this.id = id;
    }

    public void setLoginDateTime(LocalDateTime logDate){
        this.loginDateTime = logDate;
    }

    public void setSuccesful(boolean succes){
        succesful = succes;
    }



}
