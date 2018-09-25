package vue;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

/**
 * @author François Barthélemy
 *
 * Classe de service utilisée par la classe Afficheur.
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
 
 private BufferedImage image;
    public ImagePanel(BufferedImage i) {
 image=i;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); 
    }

}
