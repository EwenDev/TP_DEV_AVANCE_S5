import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=1000, HAUT=250;
    
    public UneFenetre()
    {
	    super("Le mobile");
        Container leConteneur = getContentPane();
        sonMobile = new UnMobile(LARG, HAUT);
        leConteneur.add(sonMobile);
        Thread laTache = new Thread(sonMobile);
        laTache.start();
        setSize(LARG, HAUT);
        setVisible(true);
    }
}
