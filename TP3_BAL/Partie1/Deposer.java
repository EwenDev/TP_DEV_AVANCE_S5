package Partie1;

import java.awt.event.KeyListener;

public class Deposer extends Thread implements KeyListener {
    BAL letterBox;

    public Deposer(BAL letterBox) {
        this.letterBox = letterBox;
    }

    public void run() {
        while(true);
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {}

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        String letter = String.valueOf(e.getKeyChar());

        letterBox.send(letter);

        if(letter.equals("q")) {
            System.out.println("Dépose: Demande de fin de tâche");
            stop();
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {}
}
