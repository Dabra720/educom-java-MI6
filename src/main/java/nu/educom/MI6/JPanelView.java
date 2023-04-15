package nu.educom.MI6;

import javax.swing.*;

class JPanelView implements IView{
    private JPanel mainWindow;
    private IPresentor presentor;

    @Override
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void triggerAskLogin() {

    }

    @Override
    public String getServiceNumber() {
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
