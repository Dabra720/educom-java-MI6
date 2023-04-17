package nu.educom.MI6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

class JPanelView extends JFrame implements IView, ActionListener{
    private JPanel mainWindow;
    private IPresentor presentor;
    private JLabel title = new JLabel();
    private JTextField numberTxt = new JTextField();
    private JTextField passTxt = new JTextField();

    private String serviceNumber;
    private String passPhrase;
    Object myLockObj = new Object();
    LinkedList<String> pendingActions = new LinkedList<>();
    String actionToDo;

    public JPanelView(){
        mainWindow = new JPanel();
        mainWindow.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        title.setText("Enter your number: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        mainWindow.add(title, c);

        JLabel numberLbl = new JLabel("Number: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        mainWindow.add(numberLbl, c);

        JLabel passLbl = new JLabel("Password:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        mainWindow.add(passLbl, c);

//        JTextField numberTxt = new JTextField();
        numberTxt.setColumns(13);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        mainWindow.add(numberTxt, c);

//        JTextField passTxt = new JTextField();
        passTxt.setColumns(13);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        mainWindow.add(passTxt, c);

        JButton submit = new JButton("Log in");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.PAGE_END;
        mainWindow.add(submit, c);

        submit.addActionListener(this);

        add(mainWindow);
        pack();

        setBounds(100, 100, 300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void showMessage(String msg) {
//        System.out.println(msg);
//        title.setText(msg);
        if(SwingUtilities.isEventDispatchThread()){
            title.setText(msg);
        }else{
            SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        title.setText(msg);
                    }
                }
            );
        }
    }


    @Override
    public void triggerAskLogin() throws InterruptedException {
        synchronized (myLockObj){
            while(pendingActions.isEmpty()){
                myLockObj.wait();
            }
            actionToDo = pendingActions.removeFirst();
        }
        System.out.println("Starting doing action: " + actionToDo);
    }

    @Override
    public String getServiceNumber() {
        return serviceNumber;
    }

    @Override
    public void triggerAskPassword() {

    }

    @Override
    public String getPassPhrase() {
        return passPhrase;
    }

    @Override
    public void close() {
        dispose();
    }

    public void addPresentorListener(IPresentor p){
        this.presentor = p;
    }

    public void actionPerformed(ActionEvent e){
        synchronized (myLockObj) {
            pendingActions.add(e.getActionCommand());
//            System.out.println("Eventname: " + e.getActionCommand() + " + " + e.getWhen());
//            System.out.println("actionPerformed thread name: " + Thread.currentThread().getName());
            serviceNumber = numberTxt.getText();
            passPhrase = passTxt.getText();
            if (!serviceNumber.isEmpty() && !passPhrase.isEmpty()) {
                presentor.handleLogin();
            } else {
                showMessage("Please fill all fields");
            }
            myLockObj.notify();
        }
    }
}
