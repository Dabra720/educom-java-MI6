package nu.educom.MI6;

import java.util.ArrayList;
import java.util.List;

public class AgentModel {
    List<Agent> Agents = new ArrayList<>();
    List<String> BlackList = new ArrayList<>();

    public void validateLogin(){

    }

    public boolean validatePass(String input) {
        String secretPhrase = "For ThE Royal QUEEN";
        return input.equals(secretPhrase);

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
