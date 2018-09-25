package level;
import entity.Joueur;
import type.Toile;

abstract class Niveau{
// Super classe des Niveaux, j'ai choisi une classe abstraite et non une interface pour implémenter certaines méthodes.
  
  protected Toile arrierePlan;
  protected Toile copie;
  protected Joueur joueur;
  
  protected String titre;
  protected boolean inGame;
  protected boolean tvOn;
  protected int boardWidth;
  protected int boardHeight;
  protected int tvDebutX;
  protected int tvDebutY;
  protected int tvWidth;
  protected int tvHeight;
  
  public Point[][] peindre(){ // cré le tableau de pixel pour chaque niveau
    return copie.getImage(); // j'ai choisi de copier et non de recréer des pixels à chaque boucle par économie.
  }
  
  public boolean isInGame(){return inGame;}
  public String getTitre(){return titre;}

// gère l'ennemi
  public void ennemiTire(){}
  public void deplacerEnnemi() throws GameOverException{}
  
  
// adapte l'affichage selon les boutons du jeu
  public void tvOn(){
    tvOn=true;
  }
  public void tvOff(){
    tvOn=false;
  }
  
  public void gameOver(GameOverException goe){
    titre= goe.getMsg();
    inGame=false;
  }
  
  // déplacement, tir...
  public void deplacerJoueur(){}
  public void tire(){}
  public void enregistrerDplcJoueurGauche(){}
  public void enregistrerDplcJoueurDroit(){}
  public void initDplcJoueur(){}
  public void actionPushed(){}
  public void actionReleased(){}
  public void checkDying() throws GameOverException{}
  
}