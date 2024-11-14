import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class UneFenetre extends JFrame
{
    UnMobile sonMobile;
    Thread laTache;
    JButton sonBouton;
    private final int LARG = 400, HAUT = 250;

    public UneFenetre()
    {
        sonMobile = new UnMobile(LARG, HAUT);
        add(sonMobile, BorderLayout.CENTER);

        sonBouton = new JButton("Stop");
        add(sonBouton, BorderLayout.SOUTH);

        setSize(LARG, HAUT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        laTache = new Thread(sonMobile);
        laTache.start();

        sonBouton.addActionListener(new ActionListener() {
            boolean isRunning = true;

            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    laTache.suspend();
                    sonBouton.setText("Start");
                } else {
                    laTache.resume();
                    sonBouton.setText("Stop");
                }
                isRunning = !isRunning;
            }
        });
    }
}
