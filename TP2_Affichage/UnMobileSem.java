import java.awt.*;
import javax.swing.*;

class UnMobileSem extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps = 50, sonCote = 40;
    boolean directionDroite = true;
    semaphoreBinaire semaphore;

    UnMobileSem(int telleLargeur, int telleHauteur, semaphoreBinaire semaphore) {
        super();
        this.saLargeur = telleLargeur;
        this.saHauteur = telleHauteur;
        this.semaphore = semaphore;
        setSize(telleLargeur, telleHauteur);
    }

    public void run() {
        while (true) {
            if (directionDroite) {
                sonDebDessin += sonPas;
            } else {
                sonDebDessin -= sonPas;
            }

            if (sonDebDessin >= saLargeur) {
                directionDroite = false;
            } else if (sonDebDessin <= 0) {
                directionDroite = true;
            }

            if (sonDebDessin >= saLargeur / 3 && sonDebDessin <= 2 * saLargeur / 3) {
                if (directionDroite) {
                    semaphore.syncWait();

                    while (sonDebDessin <= 2 * saLargeur / 3 && directionDroite) {
                        sonDebDessin += sonPas;
                        repaint();
                        try {
                            Thread.sleep(sonTemps);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    semaphore.syncSignal();

                } else {
                    semaphore.syncWait();

                    while (sonDebDessin >= saLargeur / 3 && !directionDroite) {
                        sonDebDessin -= sonPas;
                        repaint();
                        try {
                            Thread.sleep(sonTemps);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    semaphore.syncSignal();
                }
            }

            repaint();

            try {
                Thread.sleep(sonTemps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics telCG) {
        super.paintComponent(telCG);
        telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
    }
}
