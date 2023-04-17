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

    public AgentModel getModel(){
        return model;
    }

    public void handleLogin() {
//        theView.showMessage("Welcome Agent");
        String serviceNumber = theView.getServiceNumber();
        String passPhrase = theView.getPassPhrase();

        Agent agent = model.validateServiceNumber(serviceNumber);
        model.validatePass(passPhrase);

    }

    public void handlePassword() {
        String passPhrase = theView.getPassPhrase();
    }
}
