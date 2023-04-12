package nu.educom.MI6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Agent p = new Agent();
    System.out.println("Hello MI6 Agent");

    Scanner scanner = new Scanner(System.in);

    do{
      System.out.println("Please enter your service-number:");
//      try{
//        int serviceNumber = scanner.nextInt();
//        p.setServiceNumber(serviceNumber);
//      } catch(Exception e){
//        System.out.println("That is not a valid number, try again.");
//      }
      // nextLine gebruikt omdat anders de volgende nextLine niet werkt
      String input = scanner.nextLine();
      if(input.length() > 3){
        System.out.println("Number cannot have more than 3 characters, try again.");
        continue;
      }
      if(p.isNumeric(input)){
        p.setServiceNumber(Integer.parseInt(input));
        if(p.validateNumber()){
          System.out.println("That is not a valid number, try again.");
        }
      } else {
        System.out.println("That is not a number, try again.");
      }

    } while(p.validateNumber());


//    List<Agent> Agents = new ArrayList<Agent>(16);
//    if(isNumeric(input)){
//      Agents.add(new Agent(input));
//    }

    System.out.println("Hello agent " + p.getServiceNumber());


//    boolean valid = false;
//    do {
      System.out.println("Please enter the secret phrase:");
      String phrase = scanner.nextLine();
      if(p.validateSecretPhrase(phrase)){
//        valid = true;
        System.out.println("Welcome back agent " + p.getServiceNumber());
      } else{
        p.setBlackList(true);
        System.out.println("You have been blacklisted, agent " + p.getServiceNumber());
      }
//    } while (scanner.hasNextLine());


  }


}