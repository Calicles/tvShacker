package vue;
import java.awt.event.*;

import manager.Controleur;

class ToucheListener implements KeyListener{
  
//  Classe qui lit les interactions clavier du joueur, l'observateur est controleur.
  
  private Controleur controleur;
  
  public ToucheListener(Controleur c){
    controleur= c;
  }
  public void keyReleased(KeyEvent e){
    if((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT ))
      initDplcJoueur();
    else if(e.getKeyCode() == KeyEvent.VK_SPACE)
      controleur.actionReleased();
  }
  public void keyTyped(KeyEvent e){
    
  }
  public void keyPressed(KeyEvent e){
    if(e.getKeyCode() == KeyEvent.VK_LEFT){
      controleur.enregistrerDplcJoueurGauche();
    }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
      controleur.enregistrerDplcJoueurDroit();
    }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
      controleur.actionPushed();
        
    }
  }
  private void initDplcJoueur(){
    controleur.initDplcJoueur();
  }
    
}
    