package Partie1;

public class Retirer extends Thread {
    BAL letterBox;

    public Retirer(BAL letterBox) {
        this.letterBox = letterBox;
    }

    public void run() {
        while(true) {
            String letter = letterBox.receive();

            if(letter.equals("q")) {
                System.out.println("Retire : Arrêt des tâches");
                break;
            }
        }
    }
}
