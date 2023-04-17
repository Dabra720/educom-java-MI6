package nu.educom.MI6;

import java.util.ArrayList;
import java.util.List;

public class AgentModel {
    private List<Agent> Agents = new ArrayList<>();
    private List<Integer> BlackList = new ArrayList<>();
    private String message;

    public void printAgentsList(){
        for (Agent agent:Agents) {
            System.out.println("Agent: " + agent.getFormattedServiceNumber());
        }
    }
    public void setMessage(String msg){
        message = msg;
    }
    public String getMessage(){return message;}
    public Agent validateServiceNumber(String serviceNumber){

        if(!BlackList.contains(Integer.parseInt(serviceNumber))){
            Agent agent = new Agent(Integer.parseInt(serviceNumber));
            Agents.add(agent);
            return agent;
        } else {
//            setMessage("Wrong number");
            return null;
        }
    }


    public Agent getAgent(){
        return Agents.get(Agents.size()-1);
    }
    public void addAgentList(Agent agent){
        Agents.add(agent);
    }
    public void addBlackList(int agent){
        BlackList.add(agent);
    }

    public boolean validatePass(String input, Agent agent) {
//        String secretPhrase = "For ThE Royal QUEEN";
//        return input.equals(secretPhrase);

//        agent = Agents.get(Agents.size());

//        if(input.equals(agent.getPassPhrase())){
//            // Login geslaagd
//            // POP UP -> OptionPaneView?
//            setMessage("Welcome Agent - " + agent.getFormattedServiceNumber());
//        }else{
//            // Login gefaald
//            // Niet zeker of ik de int of de string nummer in deze lijst wil
//            addBlackList(agent.getServiceNumber());
//
//            setMessage("Blacklist Agent - " + agent.getFormattedServiceNumber());
//        }
        return input.equals(agent.getPassPhrase());
    }
    public boolean validNumber(String input){
        boolean valid = false;
//        while(!valid){
            if(isNumeric(input)) {
                if (input.length() > 3) {
                    System.out.println("Number cannot have more than 3 characters, try again.");
//                    break;
                }
                int serviceNumber = Integer.parseInt(input);
                if(serviceNumber < 1 || serviceNumber > 956){
                    System.out.println("That is not a valid number, try again.");
//                    break;
                } else{
                    valid = true;
                }
            } else{
                System.out.println("That is not a number, try again.");
//                break;
            }
//        }
        return valid;
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
