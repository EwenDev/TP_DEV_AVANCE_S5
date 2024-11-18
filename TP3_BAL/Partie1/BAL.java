package Partie1;

import javax.swing.JTextArea;

public class BAL {
    JTextArea textLeft;
    JTextArea textRight;
    String depotLettre = null;

    public BAL(JTextArea textLeft, JTextArea textRight) {
        this.textLeft = textLeft;
        this.textRight = textRight;
    }

    public synchronized void send(String lettre) {
        try {
            while(depotLettre != null) {
                wait();
            }

            System.out.println("Dépose: " + lettre);
            textLeft.append("Dépose: " + lettre + "\n");
            depotLettre = lettre;
            notify();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized String receive() {
        try {
            while (depotLettre == null) {
                wait();
            }
            String lettre = depotLettre;

            depotLettre = null;
            notify();

            System.out.println("Retire: " + lettre);
            textRight.append("Retire: " + lettre + "\n");
            return lettre;
        }
        catch(InterruptedException e) {
            e.printStackTrace();
            return "echec";
        }
    }
}
