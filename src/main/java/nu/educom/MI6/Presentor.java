package nu.educom.MI6;

import javax.swing.*;

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
//            System.out.println("run() thread name: " + Thread.currentThread().getName());
            try {
                theView.triggerAskLogin();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        theView.close();
    }

    public AgentModel getModel(){
        return model;
    }
    public IView getTheView(){
        return theView;
    }

    public void handleLogin() {
//        theView.showMessage("Welcome Agent");
//        System.out.println("handleLogin thread name: " + Thread.currentThread().getName());
        String serviceNumber = theView.getServiceNumber();

        if(!model.validNumber(serviceNumber)){
            JOptionPane.showMessageDialog(null, "ENEMY!",
                    "Error", JOptionPane.ERROR_MESSAGE);
//            theView.showMessage("Enemy");
        }else{
            Agent agent = model.validateServiceNumber(serviceNumber);
            if(agent!=null){
                handlePassword();
            }else{
                theView.showMessage("ACCESS DENIED");
            }

        }


    }

    public void handlePassword() {
        String passPhrase = theView.getPassPhrase();
        model.printAgentsList();
        if(!model.validatePass(passPhrase, model.getAgent())){
            model.addBlackList(model.getAgent().getServiceNumber());
            theView.showMessage("You have been blacklisted");
        }else{
            theView.showMessage("Welcome agent " + model.getAgent().getFormattedServiceNumber());
        }
    }
}
