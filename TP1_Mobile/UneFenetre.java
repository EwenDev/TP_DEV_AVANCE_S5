import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class UneFenetre extends JFrame
{
    UnMobile sonMobile1, sonMobile2;
    Thread laThread1, laThread2;
    JButton btnControl1, btnControl2;
    private final int LARG = 400, HAUT = 250;

    public UneFenetre()
    {
        Container leConteneur = getContentPane();
        leConteneur.setLayout(new GridLayout(2, 2));

        sonMobile1 = new UnMobile(LARG, HAUT);
        sonMobile2 = new UnMobile(LARG, HAUT);

        btnControl1 = new JButton("Stop");
        leConteneur.add(btnControl1);
        leConteneur.add(sonMobile1);

        laThread1 = new Thread(sonMobile1);
        laThread1.start();

        btnControl2 = new JButton("Stop");
        leConteneur.add(btnControl2);
        leConteneur.add(sonMobile2);

        laThread2 = new Thread(sonMobile2);
        laThread2.start();

        setSize(LARG, HAUT * 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        btnControl1.addActionListener(new ActionListener() {
            boolean isRunning1 = true;

            public void actionPerformed(ActionEvent e) {
                if (isRunning1) {
                    laThread1.suspend();
                    btnControl1.setText("Start");
                } else {
                    laThread1.resume();
                    btnControl1.setText("Stop");
                }
                isRunning1 = !isRunning1;
            }
        });

        btnControl2.addActionListener(new ActionListener() {
            boolean isRunning2 = true;

            public void actionPerformed(ActionEvent e) {
                if (isRunning2) {
                    laThread2.suspend();
                    btnControl2.setText("Start");
                } else {
                    laThread2.resume();
                    btnControl2.setText("Stop");
                }
                isRunning2 = !isRunning2;
            }
        });
    }
}
