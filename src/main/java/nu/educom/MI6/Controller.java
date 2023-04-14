package nu.educom.MI6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller{


    //                String input = txtNumber.getText();
////                String input = JOptionPane.showInputDialog(frame, "Enter your service-number: ");
//
//                if(validateNumber(input)){
//                    input = convertNumber(input);
//                    if(!blackList.contains(input)){
//                        Agents.add(new Agent(input));
//                    }else{
//                        JOptionPane.showMessageDialog(null, "ACCESS DENIED!",
//                                "Error", JOptionPane.ERROR_MESSAGE);
//                        continue;
//                    }
//                    break;
//                }
//            }
//
//            Agent p = Agents.get(Agents.size()-1);
//            String input = JOptionPane.showInputDialog(frame, "Enter the secret passphrase:");
//            if(validatePass(input)){
//                p.setLoggedIn(true);
//                JOptionPane.showMessageDialog(null, "Welcome Agent " + p.getServiceNumber() +" \nYou are now logged in.",
//                        "Welcome", JOptionPane.PLAIN_MESSAGE);
//            } else{
//                System.out.println("Wrong password, you have been blacklisted.");
//
//                blackList.add(p.getServiceNumber());
//            }
//
//            check = true;
//            while(check){
//                String action = JOptionPane.showInputDialog(frame, """
//                        Choose your next action:\s
//                        (1)Log in   \s
//                        (2)Show all logged in members\s
//                        (3)Show all blacklisted members   \s
//                        (4)EXIT""");
//
//                switch (action) {
//                    case "1" ->
//                        //Brings you back to the start
//                            check = false;
//                    case "2" -> {
//                        System.out.println("Logged in MI6 Agents:");
//                        for (Agent Agent : Agents) {
//                            if (Agent.isLoggedIn()) {
//                                System.out.println("Number: " + Agent.getServiceNumber());
//                            }
//                        }
//                    }
//                    case "3" -> {
//                        System.out.println("Blacklisted MI6 Agents:");
//                        for (String Agent : blackList) {
//                            System.out.println("Number: " + Agent);
//                        }
//                    }
//                    case "4" -> {
//                        break mainloop;
////                        System.exit(0);
//                    }
//                    default -> System.out.println("Not a valid option.");

}
