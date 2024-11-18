package Partie1;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

class Main {
    public static void main(String[] args) {

        JTextArea textLeft = new JTextArea();
        JTextArea textRight = new JTextArea();

        textLeft.setEditable(false);
        textRight.setEditable(false);
        
        JScrollPane leftScrollPane = new JScrollPane(textLeft);
        JScrollPane rightScrollPane = new JScrollPane(textRight);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScrollPane, rightScrollPane);
        splitPane.setDividerLocation(500);

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        frame.add(splitPane, BorderLayout.CENTER);
    
        BAL bal = new BAL(textLeft, textRight);
        Retirer retirer = new Retirer(bal);
        Deposer deposer = new Deposer(bal);

        frame.addKeyListener(deposer);
        frame.setFocusable(true); 
        frame.setFocusTraversalKeysEnabled(false); 
        frame.setVisible(true);

        retirer.start();
        deposer.start();

        while(deposer.isAlive()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }
}