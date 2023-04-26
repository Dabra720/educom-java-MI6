package nu.educom.MI6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
class Presentor extends Thread implements IPresentor {
    IView theView;
    AgentModel model;
    boolean running = true;

    Presentor(IView view, AgentModel model){
        theView = view;
        view.addPresentorListener(this);
        this.model = model;
    }

    public void run(){
        while(running){
            try {
                theView.triggerAskLogin();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
                if(model.authenticateAgent()<=0) { //Check for timout
                    handlePassword();
                }else{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    theView.showMessage("YOU ARE TIMED OUT! \nYou can try again at: " + LocalDateTime.now().plusSeconds(model.authenticateAgent()).format(formatter));
                }
            }
        }
    }

    public void handlePassword() {
        String passPhrase = theView.getPassPhrase();
        if(!model.validatePass(passPhrase, model.getCurrentAgent())||!model.isActive()){
            theView.showMessage("WRONG PASSWORD OR INACTIVE ACCOUNT \nTIMEOUT SET");
            model.storeLoginAttempt(new LoginAttempt(model.getCurrentAgent().getServiceNumber(), false));
        }else{
            model.printLoginAttempts();
            model.storeLoginAttempt(new LoginAttempt(model.getCurrentAgent().getServiceNumber(), true));
            String message = "Welcome agent " + model.getFormattedServiceNumber();
            if(model.getCurrentAgent().getLicense_to_kill()!=null){
                if(model.isExpired(model.getCurrentAgent().getLicense_to_kill())){
                    message += "\nYour license has expired since " + model.getCurrentAgent().getLicense_to_kill();
                }else{
                    message += ("\nYour license to kill expires: " + model.getCurrentAgent().getLicense_to_kill());
                }

            } else{
                message += "\nYou do not have a license to kill";
            }
            theView.showMessage(message);

        }
    }
}
