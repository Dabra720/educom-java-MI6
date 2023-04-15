package nu.educom.MI6;

class ConsoleView implements IView{

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void triggerAskLogin() {

    }

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

    @Override
    public void addPresentorListener(IPresentor p) {

    }
}
