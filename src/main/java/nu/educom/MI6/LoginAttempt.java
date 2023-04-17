package nu.educom.MI6;

import java.util.Date;

public class LoginAttempt {
    private int id;
    private int agent_id;
    private Date loginDateTime;
    private boolean succesful;

    public LoginAttempt(int serviceNumber){
        agent_id = serviceNumber;
        loginDateTime = new Date();
    }




}
