package entity;
import type.Animee;

class Alien extends Animee{
  
//  classe d'enemis, ils devront bouger en suivant un pattern, de gauche à droite, atteindre le bord
//  descendre et changer de direction, selon un cycle. Chaque Alien largue une bombe.
  
  @SuppressWarnings("unused")
private Alien suivant;
  @SuppressWarnings("unused")
private Tir tir;
  @SuppressWarnings("unused")
private Image explosion;
  
  private int tvDebutX;
  private int tvFinX;
  private int yJoueur;
  
  
  public Alien(String src, String srcTir, int positionX, int positionY, int tvD, int tvF, int yJ){
    super(src, positionX, positionY);
    tvDebutX= tvD;
    tvFinX= tvF;
    yJoueur= yJ;
    seDeplaceDe(1,0);
    tir = new Tir(srcTir);
  }
  
  public Alien(int xDepart, int yDepart, Alien suiv){
    super("images/alien.png", xDepart, yDepart);
    suivant= suiv;
  }
  public Alien (int xDepart, int yDepart){
    super("images/alien.png", xDepart, yDepart);
    suivant= null;
  }
  
  public void deplacerAlien() throws GameOverException{
    if(x+dplcX > tvDebutX && (x+dplcX+width) < tvFinX){
      x += dplcX;
      y += dplcY;
    }else if(x+dplcX <=tvDebutX){
      seDeplaceDe(1, 15);
      x += dplcX;
      y += dplcY;
      if((y+height) >= yJoueur)
        throw new GameOverException();
      seDeplaceDe(1,0);
    }else if ((x+width+dplcX) >= tvFinX){
      seDeplaceDe(-1, 15);
      x += dplcX;
      y += dplcY;
      if((y+height) >= yJoueur)
        throw new GameOverException();
      seDeplaceDe(-1,0);
    }
  }
  
}
