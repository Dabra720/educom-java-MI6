package nu.educom.MI6;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Presentor extends Thread implements IPresentor {
    IView theView;
    AgentModel model;
    boolean running = true;

    Presentor(IView view, AgentModel model){
        theView = view;
        view.addPresentorListener(this);
        this.model = model;
    }
    public AgentModel getModel(){
        return model;
    }
    public IView getTheView(){
        return theView;
    }

    public void run(){
        while(running){
//            System.out.println("run() thread name: " + Thread.currentThread().getName());
            try {
                theView.triggerAskLogin();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("After asklogin Loop");
        }
        theView.close();
    }

    public void handleLogin() {
        String serviceNumber = theView.getServiceNumber();

        if(!model.validNumber(serviceNumber)){
            theView.showMessage("Enemy");
        }else{
            Agent agent = model.validateServiceNumber(serviceNumber);
            if(agent==null){
                theView.showMessage("ACCESS DENIED");
            }else{
                System.out.println("Seconds since last login:" + model.authenticateAgent());
                if(model.authenticateAgent()<=0) { //Check for timout
                    handlePassword();
                }else{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    theView.showMessage("YOU ARE TIMED OUT! \nYou can try again at: " + LocalDateTime.now().plusSeconds(model.authenticateAgent()).format(formatter));
                }
            }
        }
    }
    public void test(){
        System.out.println("Authenticate agent:");
        Agent agent = model.getAgent(2);
        System.out.println("Tijdverschil: " + model.authenticateAgent());
    }

    public void handlePassword() {
        String passPhrase = theView.getPassPhrase();
//        model.printAgentsList();
        if(!model.validatePass(passPhrase, model.getCurrentAgent())||!model.isActive()){
//            model.addBlackList(model.getCurrentAgent().getServiceNumber());
            theView.showMessage("WRONG PASSWORD, TIMEOUT SET");
            model.storeLoginAttempt(new LoginAttempt(model.getCurrentAgent().getServiceNumber(), false));
        }else{
            model.printLoginAttempts();
            model.storeLoginAttempt(new LoginAttempt(model.getCurrentAgent().getServiceNumber(), true));
            theView.showMessage("Welcome agent " + model.getCurrentAgent().getFormattedServiceNumber());

        }
    }
}
