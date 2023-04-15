package nu.educom.MI6;

class Presentor extends Thread implements IPresentor {
    IView theView;
    AgentModel model;
    boolean stopped;

    Presentor(IView view, AgentModel model){
        theView = view;
        view.addPresentorListener(this);
        this.model = model;
    }

    public void run(){
        while(!stopped){
            theView.triggerAskLogin();
            String serviceNr = theView.getServiceNumber();
            if(!model.validNumber(serviceNr)){
                theView.showMessage("Enemy");
            }
        }
    }

    public void handleLogin() {

    }

    public void handlePassword() {

    }
}
