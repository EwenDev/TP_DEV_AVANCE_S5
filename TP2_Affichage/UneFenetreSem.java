import java.awt.*;
import javax.swing.*;

class UneFenetreSem extends JFrame {
    private final int LARG = 400, HAUT = 600;
    UnMobileSem mobile1, mobile2, mobile3, mobile4;
    semaphoreBinaire semaphore;

    public UneFenetreSem() {
        semaphore = new semaphoreBinaire(1);
        setLayout(new GridLayout(4, 1));

        mobile1 = new UnMobileSem(LARG, HAUT / 4, semaphore);
        add(mobile1);
        new Thread(mobile1).start();

        mobile2 = new UnMobileSem(LARG, HAUT / 4, semaphore);
        add(mobile2);
        new Thread(mobile2).start();

        mobile3 = new UnMobileSem(LARG, HAUT / 4, semaphore);
        add(mobile3);
        new Thread(mobile3).start();

        mobile4 = new UnMobileSem(LARG, HAUT / 4, semaphore);
        add(mobile4);
        new Thread(mobile4).start();

        setSize(LARG, HAUT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
