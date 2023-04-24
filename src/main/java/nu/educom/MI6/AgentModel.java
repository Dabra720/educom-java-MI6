package nu.educom.MI6;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AgentModel {
    private DatabaseRepository repo;
    private List<Agent> Agents = new ArrayList<>();
    private Agent currentAgent;

    public AgentModel(DatabaseRepository repo){
        this.repo = repo;
    }

    public void printAgentsList(){
        Agents = repo.readAllAgents();

        for (Agent agent:Agents ) {
            System.out.println("Service-nr: " + agent.getFormattedServiceNumber());
            System.out.println("Service-nr: " + agent.getPassPhrase());
            System.out.println("Service-nr: " + agent.getActive());
        }
    }
    public void printAgent(int serviceNr){
        System.out.println("Print agent: " + serviceNr);
        Agent agent = getAgent(serviceNr);
        System.out.println("Service-nr: " + agent.getFormattedServiceNumber());
        System.out.println("Service-nr: " + agent.getPassPhrase());
        System.out.println("Service-nr: " + agent.getActive());
    }

    public void printLoginAttempts(){
        ArrayList<LoginAttempt> loginAttempts = currentAgent.getLoginAttemptList();
        for(LoginAttempt login:loginAttempts){
            System.out.println("Login Attempt: " + login.getLoginDateTime());
        }
    }

    public Double getTimeOut(){
//        Math.pow() // Machtsberekening
        double failedLoginAttempts = currentAgent.getLoginAttemptList().size();
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
//        System.out.println("Nu: " + LocalDateTime.now());
//        System.out.println("Timeout:  " + dateAfterTimeOut);
//        System.out.println("Verschil tussen nu en timout: " + diff);
        return diff;
    }
    public void storeLoginAttempt(LoginAttempt login){
        repo.insertLoginAttempt(login);
    }

    public LoginAttempt getLastLoginAttempt(){
        LoginAttempt login;
        List<LoginAttempt> loginAttempts = repo.readFailedLoginAttempts(currentAgent);
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
//                System.out.println("Agent is added to the list: " + agent.getFormattedServiceNumber());
                Agents.add(agent);
            }catch(Exception e){
                System.out.printf("Error: " + e.getMessage());
                System.out.println("Not an existing number.");
            }
        return agent;
    }


    public Agent getAgent(int serviceNr){

        currentAgent = repo.readAgentByServiceNumber(serviceNr);
        if(currentAgent!=null){
            System.out.println("Current Agent is now: " + currentAgent.getFormattedServiceNumber());
            try{
                currentAgent.setLoginAttempts(repo.readFailedLoginAttempts(currentAgent));
            }catch(Exception e){
                System.out.println("No previous login attempts");
            }
        }else {
            System.out.println("No CURRENT AGENT available");
        }
        return getCurrentAgent();
    }
    public Agent getCurrentAgent(){
        return currentAgent;
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

    public boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
