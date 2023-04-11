package nu.educom.MI6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello MI6 Agent");
    System.out.println("Please enter your service-number:");

    Scanner scanner = new Scanner(System.in);
//    String input = scanner.next();

    int serviceNumber = scanner.nextInt();

//    List<Agent> Agents = new ArrayList<Agent>(16);
//    if(isNumeric(input)){
//      Agents.add(new Agent(input));
//    }
    Agent p = new Agent(serviceNumber);



    System.out.println("Hello agent " + p.getServiceNumber());

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