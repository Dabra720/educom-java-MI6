package nu.educom.MI6;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AgentModel {
    private DatabaseRepository repo;
//    private List<Agent> Agents = new ArrayList<>();
    private List<LoginAttempt> loginAttempts = new ArrayList<>();
    private Agent currentAgent;

    public AgentModel(DatabaseRepository repo){
        this.repo = repo;
    }

    public void printLoginAttempts(){
        for(LoginAttempt login:loginAttempts){
            System.out.println("Login Attempt: " + login.getLoginDateTime());
        }
    }

    public void setLoginAttemptList(){
        loginAttempts = repo.readFailedLoginAttempts(currentAgent);
    }
    public List getLoginAttemptList(){
        return loginAttempts;
    }

    public Double getTimeOut(){
//        Math.pow() // Machtsberekening
        double failedLoginAttempts = getLoginAttemptList().size();
        double defaultTimeOut = 60; // Seconden

        return defaultTimeOut * Math.pow(2, failedLoginAttempts);
    }

    public long authenticateAgent(){
        LoginAttempt lastLogin = getLastLoginAttempt();
        long timeOut = getTimeOut().longValue();
        LocalDateTime dateAfterTimeOut;
        try {
            dateAfterTimeOut = lastLogin.getLoginDateTime().plusSeconds(timeOut);
        }catch(Exception e){
            dateAfterTimeOut = LocalDateTime.now();
        }
        long diff = LocalDateTime.now().until(dateAfterTimeOut, ChronoUnit.SECONDS);
        System.out.println("Nu: " + LocalDateTime.now());
        System.out.println("Timeout:  " + dateAfterTimeOut);
        System.out.println("Verschil tussen nu en timout: " + diff);
        return diff;
    }
    public void storeLoginAttempt(LoginAttempt login){
        repo.insertLoginAttempt(login);
    }

    public LoginAttempt getLastLoginAttempt(){
        LoginAttempt login;
        try{
            login = loginAttempts.get(loginAttempts.size() -1);
        }catch(Exception e){
            return null;
        }

        return login;
    }

    public Agent validateServiceNumber(String serviceNr){
        Agent agent = null;
        int agent_id = Integer.parseInt(serviceNr);
            try{
                agent = getAgent(agent_id);
//                Agents.add(agent);
                System.out.println("Agent-ID: " + agent.getId());
            }catch(Exception e){
                System.out.printf("Error: " + e.getMessage());
                System.out.println("Not an existing number.");
            }
        return agent;
//        return repo.readAgentByServiceNumber(agent_id);
    }


    public Agent getAgent(int serviceNr){

        currentAgent = repo.readAgentByServiceNumber(serviceNr);
        if(currentAgent!=null){
            System.out.println("Current Agent is now: " + getFormattedServiceNumber());
            setLoginAttemptList();
        }else {
            System.out.println("No CURRENT AGENT available");
        }
        return currentAgent;
    }
    public Agent getCurrentAgent(){
        return currentAgent;
    }

    public String getFormattedServiceNumber(){
        return String.format("%03d",currentAgent.getServiceNumber());
    }
    public boolean isActive(){
        return currentAgent.getActive();
    }
    public boolean validatePass(String input, Agent agent) {
        System.out.println("Validating pass:");
        System.out.println("input: " + input);
        System.out.println("Pass: " + agent.getPassPhrase());
        return input.equals(agent.getPassPhrase());
    }
    public boolean validNumber(String input){
        if(isNumeric(input) && input.length() <= 3) {
            int serviceNr = Integer.parseInt(input);
            return serviceNr > 0 && serviceNr <= 956;
        } else{
            return false;
        }

    }
    public boolean isExpired(LocalDate date){
        Period diff = LocalDate.now().until(date);
        return diff.isNegative();
    }

    public boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
