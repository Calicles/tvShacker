package level;
import entity.EnnemiAvecScroll;
import entity.JoueurAvecScroll;
import type.Image;

public class NiveauAventure extends Niveau{
// Niveau ou l'action est de profil, à terme j'aimerai faire un jeu pointer cliquer de type Lucas arts.
  
  private JoueurAvecScroll joueur;
  private EnnemiAvecScroll ennemi;
  private Image decors;
  
  public NiveauAventure(String t, Pixel[][] arrPlan, String dec, String srcJoueur, 
                      Image[] scrollJoueur, String srcTirJoueur,
                      String srcEnnemi, Image[] scrollEnnemi, Image mortEnnemi){
    titre= t;
    arrierePlan= new Toile(arrPlan);
    decors= new Image(dec);
    copie= new Toile(arrPlan);
    boardWidth= arrierePlan.getWidth();
    boardHeight= arrierePlan.getHeight();
    tvDebutX= 110;
    tvDebutY= 110;
    tvWidth= tvDebutX + decors.getWidth();
    tvHeight= tvDebutY + decors.getHeight();
    tvOn=true;
    joueur= new JoueurAvecScroll(srcJoueur, scrollJoueur, srcTirJoueur, (tvDebutX + decors.getWidth()/4), 
                                 tvHeight - (tvHeight/5), tvDebutX, tvWidth, tvDebutY);
    ennemi= new EnnemiAvecScroll(srcEnnemi, tvDebutX+ decors.getWidth()/3+100, tvHeight - (tvHeight/5)-10, tvDebutX, 
                                 tvDebutY, scrollEnnemi, mortEnnemi);
    inGame=true;
  }

  public Point[][] peindre(){
    int deltaX=0;
    int deltaY=0;
    Point[][] scroll= joueur.updateImage();
    Point[][] scrollEnnemi= ennemi.updateImage();
    
    if(tvOn){
      for(int i=tvDebutX;i<tvWidth;i++){
        for(int j=tvDebutY;j<tvHeight;j++){
          
          if(chevaucheJoueur(i,j, scroll)){
            deltaX= (i - joueur.getX());
            deltaY= (j - joueur.getY());
            if(scroll[deltaX][deltaY].getAlpha()!=0)
              arrierePlan.copier(i,j, scroll[deltaX][deltaY].getAlpha(), 
                                 scroll[deltaX][deltaY].getRed(),
                                 scroll[deltaX][deltaY].getGreen(), 
                                 scroll[deltaX][deltaY].getBlue());
            else{
              deltaX= (i - tvDebutX);
              deltaY= (j - tvDebutY);
              arrierePlan.copier(i,j, decors.getPoint(deltaX,deltaY).getAlpha(), 
                                 decors.getPoint(deltaX,deltaY).getRed(), 
                                 decors.getPoint(deltaX,deltaY).getGreen(), 
                                 decors.getPoint(deltaX,deltaY).getBlue());
            }
          }else if(chevaucheEnnemi(i,j, scrollEnnemi)){
            deltaX= (i - ennemi.getX());
            deltaY= (j - ennemi.getY());
            if(scrollEnnemi[deltaX][deltaY].getAlpha()!=0){ 
              if(chevaucheTir(i,j)){
                joueur.tirMort();
                ennemi.dying();
              }
              arrierePlan.copier(i,j, scrollEnnemi[deltaX][deltaY].getAlpha(), 
                                 scrollEnnemi[deltaX][deltaY].getRed(),
                                 scrollEnnemi[deltaX][deltaY].getGreen(), 
                                 scrollEnnemi[deltaX][deltaY].getBlue());
            }else if(chevaucheTir(i,j) && ennemi.getImage()[deltaX][deltaY].getAlpha()==0){
              deltaX= (i - joueur.getXTir());
              deltaY= (j - joueur.getYTir());
              if(joueur.getImageTir()[deltaX][deltaY].getAlpha()!=0)
                arrierePlan.copier(i,j, joueur.getImageTir()[deltaX][deltaY].getAlpha(), 
                                   joueur.getImageTir()[deltaX][deltaY].getRed(), 
                                   joueur.getImageTir()[deltaX][deltaY].getGreen(), 
                                   joueur.getImageTir()[deltaX][deltaY].getBlue());
            }else{
              deltaX= (i - tvDebutX);
              deltaY= (j - tvDebutY);
              arrierePlan.copier(i,j, decors.getPoint(deltaX,deltaY).getAlpha(), 
                                 decors.getPoint(deltaX,deltaY).getRed(), 
                                 decors.getPoint(deltaX,deltaY).getGreen(), 
                                 decors.getPoint(deltaX,deltaY).getBlue());
            }
          }else if(chevaucheTir(i,j)){
            deltaX= (i - joueur.getXTir());
            deltaY= (j - joueur.getYTir());
            if(joueur.getImageTir()[deltaX][deltaY].getAlpha()!=0)
              arrierePlan.copier(i,j, joueur.getImageTir()[deltaX][deltaY].getAlpha(), 
                                 joueur.getImageTir()[deltaX][deltaY].getRed(), 
                                 joueur.getImageTir()[deltaX][deltaY].getGreen(), 
                                 joueur.getImageTir()[deltaX][deltaY].getBlue());
          }else{
            deltaX= (i- tvDebutX);
            deltaY= (j-tvDebutY);
            arrierePlan.copier(i,j, decors.getPoint(deltaX,deltaY).getAlpha(), 
                               decors.getPoint(deltaX,deltaY).getRed(), 
                               decors.getPoint(deltaX,deltaY).getGreen(), 
                               decors.getPoint(deltaX,deltaY).getBlue());
          }
        }
      }
      return arrierePlan.getImage();
    }else 
      return copie.getImage();
  }
// Utilisée comme detecteur pour savoir quoi peindre
  private boolean chevaucheJoueur(int i, int j, Point[][] tab){
    return ((i>= joueur.getX() && i<= (joueur.getX() + (tab.length -1))) && 
            (j >= joueur.getY() && j<= (joueur.getY()+(tab[0].length-1))));
  }
  private boolean chevaucheEnnemi(int i, int j, Point[][] tab){
    return ((i>= ennemi.getX() && i<= (ennemi.getX()+(tab.length-1))) && 
            (j >= ennemi.getY() && j<= (ennemi.getY()+(tab[0].length-1))));
  }
  private boolean chevaucheTir(int i, int j){
    if(tirVisible())
      return ((i>= joueur.getXTir() && i<= (joueur.getXTir()+(joueur.getWidthTir()-1))) && 
              (j >= joueur.getYTir() && j<= (joueur.getYTir()+(joueur.getHeightTir()-1))));
    else
      return false;
  }
//-----------------------------------------------------
  public void deplacerEnnemi() throws GameOverException{
 
  }
  public void checkDying() throws GameOverException{
    ennemi.checkDying();
  }
  
  private boolean tirVisible(){
    return joueur.tirVisible();
  }

 //tir de joueur.
    public void actionPushed(){
    joueur.actionPushed();
    }
    public void actionReleased(){
      joueur.actionReleased();
    }
    public void tire(){
      joueur.tire();
    }
    public void deplacerJoueur(){
      if(tirVisible())
        joueur.deplacerTir();
    }
    public void initDplcJoueur(){
    }
    public void enregistrerDplcJoueurGauche(){
      
    }
    public void enregistrerDplcJoueurDroit(){
      
    }
    
}
