package demo5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class App {
    private JScrollPane theScroller;
    private JFrame theFrame;
    private JButton theButton;
    private JTextArea theText;

    public void go() {
        theFrame = new JFrame("Swing Updates");
        theFrame.setLayout(new GridLayout(2,1));

        theButton = new JButton("Click Me");
        theText = new JTextArea("edit this");
        theScroller = new JScrollPane(theText);

        theButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button was clicked");
                System.out.println("UI thread name is: " + Thread.currentThread().getName());
            }
        });

        theFrame.add(theButton);
        theFrame.add(theScroller);

        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setBounds(10,10, 300, 200);
        theFrame.setVisible(true);
        System.out.println("Main thread name is: " + Thread.currentThread().getName());
        for (;;){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {
                    theText.append("\nTickle " + new Date());
                    System.out.println("UI thread name is: " + Thread.currentThread().getName());
                }
            });

        }
    }

    public static void main(String[] args) {
        new App().go();
    }


}
