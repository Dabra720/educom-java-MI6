package nu.educom.MI6;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class FrameView extends JFrame {
    Object myLockObj = new Object();
    LinkedList<Action> pendingActions = new LinkedList<>();
    Action actionToDo;
    Action action;
    JTextField messageTxt;

    public void triggerLogin() throws InterruptedException {
        synchronized (myLockObj){

            while(pendingActions.isEmpty()){
                myLockObj.wait(1000);
            }
            actionToDo = pendingActions.removeFirst();
        }
        System.out.println("Starting doing action: " + actionToDo);
    }

    public void onButtonClicked(){
        synchronized (myLockObj){
            pendingActions.add(action);
            myLockObj.notify();

        }
    }

    public void showMessage(String msg){
        if(SwingUtilities.isEventDispatchThread()){
            messageTxt.setText(msg);
        }else{
            SwingUtilities.invokeLater(
                    new Runnable() {
                        @Override
                        public void run() {
                            messageTxt.setText(msg);
                        }
                    }
            );
        }
    }

}
