package nu.educom.MI6;

import java.util.ArrayList;
import java.util.List;

public class AgentModel {
    private List<Agent> Agents = new ArrayList<>();
    private List<Integer> BlackList = new ArrayList<>();
    private String message;
    private Agent agent;

    public void setMessage(String msg){
        message = msg;
    }
    public String getMessage(){return message;}
    public Agent validateServiceNumber(String serviceNumber){

        if(validNumber(serviceNumber)){
            agent = new Agent(Integer.parseInt(serviceNumber));
//            addAgentList(agent);
            return agent;
        } else {
            setMessage("Wrong number");
            return null;
        }
    }

    public void addAgentList(Agent agent){
        Agents.add(agent);
    }
    public void addBlackList(int agent){
        BlackList.add(agent);
    }
    public void validatePass(String input) {
        String secretPhrase = "For ThE Royal QUEEN";
//        return input.equals(secretPhrase);

        agent = Agents.get(Agents.size());

        if(input.equals(secretPhrase)){
            // Login geslaagd
            // POP UP -> OptionPaneView?
            setMessage("Welcome Agent - " + agent.getFormattedServiceNumber());
        }else{
            // Login gefaald
            // Niet zeker of ik de int of de string nummer in deze lijst wil
            addBlackList(agent.getServiceNumber());
            setMessage("Blacklist Agent - " + agent.getFormattedServiceNumber());
        }

    }
    public boolean validNumber(String input){
        boolean valid = false;
        while(!valid){
            if(isNumeric(input)) {
                if (input.length() > 3) {
                    System.out.println("Number cannot have more than 3 characters, try again.");
                    break;
                }
                int serviceNumber = Integer.parseInt(input);
                if(serviceNumber < 1 || serviceNumber > 956){
                    System.out.println("That is not a valid number, try again.");
                    break;
                } else{
                    valid = true;
                }
            } else{
                System.out.println("That is not a number, try again.");
                break;
            }
        }
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
