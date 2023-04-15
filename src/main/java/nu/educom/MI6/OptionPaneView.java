package nu.educom.MI6;

class OptionPaneView implements IView{
    private IPresentor presentor;



    public void showMessage(String msg){
        System.out.println(msg);
    }

    @Override
    public void triggerAskLogin() {

    }

    public String getServiceNumber(){

        return null;
    }

    @Override
    public void triggerAskPassword() {

    }

    @Override
    public String getPassPhrase() {
        return null;
    }

    public void addPresentorListener(IPresentor p){
        this.presentor = p;
    }

    public void actionPerform(){
        this.presentor.handleLogin();
    }
}
