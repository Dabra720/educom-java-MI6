package nu.educom.MI6;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        IView view = new JPanelView();
//        DatabaseRepository repo = new DatabaseRepository();
        AgentModel model = new AgentModel();
        Presentor presentor = new Presentor(view, model);
        presentor.run();
    }




}

