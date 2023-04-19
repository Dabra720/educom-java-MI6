package nu.educom.MI6;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AgentModel {
    private DatabaseRepository repo;
    private List<Agent> Agents = new ArrayList<>();
    private List<Integer> BlackList = new ArrayList<>();
    private Agent currentAgent;

    public AgentModel(DatabaseRepository repo){
        this.repo = repo;
    }

    public void printAgentsList(){
        for (Agent agent:Agents) {
            System.out.println("Agent: " + agent.getFormattedServiceNumber());
        }
    }
    public void printAgent(int serviceNr){
        System.out.println("Print agent: " + serviceNr);
        Agent agent = getAgent(serviceNr);
        System.out.println("Service-nr: " + agent.getFormattedServiceNumber());
        System.out.println("Service-nr: " + agent.getPassPhrase());
        System.out.println("Service-nr: " + agent.isActive());
    }

    public int authenticateAgent(){
        LoginAttempt lastLogin = getLastLoginAttempt();

        return LocalDateTime.now().getSecond()  - lastLogin.getLoginDateTime().getSecond();
    }
    public void storeLoginAttempt(LoginAttempt login){
        repo.insertLoginAttempt(login);
    }

    public LoginAttempt getLastLoginAttempt(){
        LoginAttempt login;
        LinkedList<LoginAttempt> loginAttempts = repo.readAllFailedLoginAttempts(currentAgent);
        login = loginAttempts.getLast();

        return login;
    }

    public Agent validateServiceNumber(String serviceNr){
        Agent agent = null;
        int agent_id = Integer.parseInt(serviceNr);
        if(!BlackList.contains(agent_id)){
            try{
                agent = getAgent(agent_id);
                System.out.println("Agent is added to the list: " + agent.getFormattedServiceNumber());
                Agents.add(agent);
            }catch(Exception e){
                System.out.println("Not an existing number.");
            }
        }else{
            System.out.println("This agent is blacklisted");
        }
        return agent;
    }


    public Agent getAgent(int serviceNr){

        currentAgent = repo.readAgentByServiceNumber(serviceNr);
        if(currentAgent!=null){
            System.out.println("Current Agent is now: " + currentAgent.getFormattedServiceNumber());
        }else {
            System.out.println("No CURRENT AGENT available");
        }
        return getCurrentAgent();
//        return Agents.get(Agents.size()-1);
    }
    public Agent getCurrentAgent(){
//        System.out.println("Returning Current agent: " + currentAgent.getFormattedServiceNumber());
        return currentAgent;
    }
    public void addAgentList(Agent agent){
        Agents.add(agent);
    }
    public void addBlackList(int agent){
        System.out.println("Added to blacklist-Agent: " + agent);
        BlackList.add(agent);
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
