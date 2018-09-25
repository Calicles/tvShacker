package vue;
import java.awt.*;
import javax.swing.*;

//Cette classe est invoquée dans l'affficheur pour créer un panel, qui, à la base devait être beaucoup plus riche.
// Mais l'avancé du projet a réduit son utilisation.

@SuppressWarnings("serial")
class PanelBas extends JPanel{
  
  private Font font = new Font("courier", Font.BOLD, 20);
  private JLabel info=new JLabel();
  private final String text= "Tire: SPACE     Click On!";
  
  public PanelBas(){
    info.setForeground(Color.white);
    info.setBackground(Color.black);
    info.setFont(font);
    info.setHorizontalAlignment(JLabel.CENTER);
    info.setVisible(true);
    info.setText(text);
    this.add(info);
    this.setBackground(Color.black);
    this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
                                                      BorderFactory.createLoweredBevelBorder()));
  }
  public void informer(String msg){
    info.setText(msg);
  }
  public void resetInformation(){
    info.setText(text);
  }

}