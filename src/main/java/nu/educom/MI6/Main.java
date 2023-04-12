package nu.educom.MI6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
      List<String> blackList = new ArrayList<>();
      List<Agent> Agents = new ArrayList<>();
      Scanner scanner = new Scanner(System.in);
      boolean check;

      mainloop:
      do{
          while(true){
              System.out.println("Enter your service-number: ");
              String input = scanner.nextLine();

              if(validateNumber(input)){
                  input = convertNumber(input);
                  if(!blackList.contains(input)){
                      Agents.add(new Agent(input));
                  }else{
                      System.out.println("This agent is blacklisted");
                      continue;
                  }
                  break;
              }
          }

//          while(true){
              System.out.println("Enter the secret passphrase:");
              Agent p = Agents.get(Agents.size()-1);
              String input = scanner.nextLine();
              if(validatePass(input)){
                  p.setLoggedIn(true);
                  System.out.println("Welcome Agent " + p.getServiceNumber());
                  System.out.println("You are now logged in.");
              } else{
                  System.out.println("Wrong password, you have been blacklisted.");

                  blackList.add(p.getServiceNumber());
              }
//              break;
//          }

          check = true;
          while(check){
              System.out.println("Choose your next action:");
              System.out.println("(1)Log in    (2)Show all logged in members    (3)Show all blacklisted members    (4)EXIT");
              String action = scanner.nextLine();

              switch (action) {
                  case "1" ->
                      //Brings you back to the start
                          check = false;
                  case "2" -> {
                      System.out.println("Logged in MI6 Agents:");
                      for (Agent Agent : Agents) {
                          if (Agent.isLoggedIn()) {
                              System.out.println("Number: " + Agent.getServiceNumber());
                          }
                      }
                  }
                  case "3" -> {
                      System.out.println("Blacklisted MI6 Agents:");
                      for (String Agent : blackList) {
                          System.out.println("Number: " + Agent);
                      }
                  }
                  case "4" -> {
                      break mainloop;
                  }
                  default -> System.out.println("Not a valid option.");
              }

          }

      } while (true);
      System.out.println("EXITING THE PROGRAM!");
  }

  public static boolean validateNumber(String input){
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

  public static boolean validatePass(String input){
      String secretPhrase = "For ThE Royal QUEEN";
      return input.equals(secretPhrase);

  }
    public static String convertNumber(String serviceNumber){

        int serNum = Integer.parseInt(serviceNumber);
        if(serNum < 100 && serNum > 9){
            serviceNumber = "0" + serNum;
        }else if(serNum < 10){
            serviceNumber = "00" + serNum;
        }
        return serviceNumber;
    }
    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}