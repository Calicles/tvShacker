package level;
import entity.Ennemi;
import entity.EnnemiListe;
import entity.Joueur;
import entity.Tir;
import type.Fond;

class NiveauTypeInvader extends Niveau{
  
//  Cette classe devait être le seul niveau au début du projet avant que je découvre à quoi sert vraiment la 
//  programmation orientée objet... Le projet a alors pris une nouvelle tournure avec l'idée de la tv.
  
  private Joueur joueur;
  private Fond fond;
  private EnnemiListe ennemis;
  
  private int nbrEnnemi;
  
  
  
  
  public NiveauTypeInvader(String t, Pixel[][] arrPlan, String srcFond, String srcJoueur, int nbr, String srcAlien, 
                           String srcTirJoueur,String srcTirAlien, Image mort){
    titre= t;
    arrierePlan= new Toile(arrPlan);
    copie= new Toile(arrPlan);
    fond=new Fond(srcFond);
    boardWidth= arrierePlan.getWidth();
    boardHeight= arrierePlan.getHeight();
    tvDebutX= 110;
    tvDebutY= 110;
    tvWidth= tvDebutX + fond.getWidth();
    tvHeight= tvDebutY + fond.getHeight();
    tvOn=true;
    nbrEnnemi=nbr;
    joueur= new Joueur(srcJoueur, srcTirJoueur, (tvDebutX + fond.getWidth()/2), tvHeight - (tvHeight/5),
                     tvDebutX, tvWidth, tvDebutY, mort);
    ennemis= new EnnemiListe(nbrEnnemi, srcAlien, srcTirAlien, tvDebutX, tvDebutY, 
                     tvDebutX, tvWidth, joueur.getY(), tvHeight, mort);
    inGame=true;
  }
  
 // Peint toute l'image en incrustant chaque élément. Les places des incrustations sont caclulées grâce aux
//  variables deltaX et deltaY.
  public Point[][] peindre(){
    int deltaX=0;
    int deltaY=0;
    Point[][] tab= fond.update();
    Ennemi alien;
    Tir tirEnnemi;
    
    if(tvOn){
      for(int i=tvDebutX;i<tvWidth;i++){
        for(int j=tvDebutY;j<tvHeight;j++){
          
          if(chevaucheJoueur(i,j)){
            deltaX= (i - joueur.getX());
            deltaY= (j - joueur.getY());
            if(joueur.getImage()[deltaX][deltaY].getAlpha()!=0){
              if((tirEnnemi=chevaucheTirEnnemi(i,j))!=null){
                joueur.dying();
              }
              arrierePlan.copier(i,j, joueur.getPoint(deltaX,deltaY).getAlpha(), 
                                 joueur.getPoint(deltaX,deltaY).getRed(),
                                 joueur.getPoint(deltaX,deltaY).getGreen(), 
                                 joueur.getPoint(deltaX,deltaY).getBlue());
            }else if(((tirEnnemi=chevaucheTirEnnemi(i,j))!=null) && joueur.getImage()[deltaX][deltaY].getAlpha()==0){
              deltaX= (i - tirEnnemi.getX());
              deltaY= (j - tirEnnemi.getY());
              if(tirEnnemi.getImage()[deltaX][deltaY].getAlpha()!=0)
                arrierePlan.copier(i,j, tirEnnemi.getImage()[deltaX][deltaY].getAlpha(), 
                                   tirEnnemi.getImage()[deltaX][deltaY].getRed(), 
                                   tirEnnemi.getImage()[deltaX][deltaY].getGreen(), 
                                   tirEnnemi.getImage()[deltaX][deltaY].getBlue());
            }else{
              deltaX= (i - tvDebutX);
            deltaY= (j - tvDebutY);
            arrierePlan.copier(i,j, tab[deltaX][deltaY].getAlpha(), tab[deltaX][deltaY].getRed(), 
                               tab[deltaX][deltaY].getGreen(), tab[deltaX][deltaY].getBlue());
            }
          }else if((tirEnnemi=chevaucheTirEnnemi(i,j))!=null){
            deltaX= (i - tirEnnemi.getX());
            deltaY= (j - tirEnnemi.getY());
            if(tirEnnemi.getImage()[deltaX][deltaY].getAlpha()!=0)
              arrierePlan.copier(i,j, tirEnnemi.getImage()[deltaX][deltaY].getAlpha(), 
                                 tirEnnemi.getImage()[deltaX][deltaY].getRed(), 
                                 tirEnnemi.getImage()[deltaX][deltaY].getGreen(), 
                                 tirEnnemi.getImage()[deltaX][deltaY].getBlue()); 
            else{
              deltaX= (i - tvDebutX);
              deltaY= (j - tvDebutY);
              arrierePlan.copier(i,j, tab[deltaX][deltaY].getAlpha(), tab[deltaX][deltaY].getRed(), 
                                 tab[deltaX][deltaY].getGreen(), tab[deltaX][deltaY].getBlue());
            }
          }else if((alien=chevaucheEnnemi(i,j))!=null){
            deltaX= (i - alien.getX());
            deltaY= (j - alien.getY());
            if(alien.getImage()[deltaX][deltaY].getAlpha()!=0){
              if(chevaucheTir(i,j)){
                alien.dying();
                joueur.tirMort();
              }
              arrierePlan.copier(i,j, alien.getPoint(deltaX,deltaY).getAlpha(), 
                               alien.getPoint(deltaX,deltaY).getRed(),
                               alien.getPoint(deltaX,deltaY).getGreen(), 
                               alien.getPoint(deltaX,deltaY).getBlue());
            }else if(chevaucheTir(i,j) && alien.getImage()[deltaX][deltaY].getAlpha()==0){
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
              arrierePlan.copier(i,j, tab[deltaX][deltaY].getAlpha(), tab[deltaX][deltaY].getRed(), 
                                 tab[deltaX][deltaY].getGreen(), tab[deltaX][deltaY].getBlue());
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
            arrierePlan.copier(i,j, tab[deltaX][deltaY].getAlpha(), tab[deltaX][deltaY].getRed(), 
                               tab[deltaX][deltaY].getGreen(), tab[deltaX][deltaY].getBlue());
          }
        }
      }
      return arrierePlan.getImage();
    }else 
      return copie.getImage();
  }
// Detecte les objets pour peindre
  private boolean chevaucheJoueur(int i, int j){
    return ((i>= joueur.getX() && i<= (joueur.getX()+(joueur.getWidth()-1))) && 
            (j >= joueur.getY() && j<= (joueur.getY()+(joueur.getHeight()-1))));
  }
  private Ennemi chevaucheEnnemi(int i, int j){
    return ennemis.intercept(i,j);
  }
  private boolean chevaucheTir(int i, int j){
    if(tirVisible())
      return ((i>= joueur.getXTir() && i<= (joueur.getXTir()+(joueur.getWidthTir()-1))) && 
             (j >= joueur.getYTir() && j<= (joueur.getYTir()+(joueur.getHeightTir()-1))));
    else
      return false;
  }
//---------------------------------
// Methodes de rafraichissement des cycles
  private Tir chevaucheTirEnnemi(int i, int j){
    return ennemis.interceptTir(i,j);
  }
  public void deplacerEnnemi() throws GameOverException{
    ennemis.deplacerEnnemi();
    ennemis.deplacerTir();
  }
  private boolean tirVisible(){
    return joueur.tirVisible();
  }
  public void deplacerJoueur(){
    joueur.deplacer();
    if(tirVisible())
      joueur.deplacerTir();
  }
  public void tire(){
    joueur.tire();
  }
  public void enregistrerDplcJoueurGauche(){
    joueur.enregistrerDplcGauche();
  }
  public void enregistrerDplcJoueurDroit(){
    joueur.enregistrerDplcDroit();
  }
  public void initDplcJoueur(){
    joueur.initDplc();
  }
  public void actionPushed(){
    joueur.actionPushed();
  }
  public void actionReleased(){
    joueur.actionReleased();
  }
  public void ennemiTire(){
    ennemis.tire(joueur.getX(), joueur.getX()+joueur.getWidth());
  } 
  public void checkDying() throws GameOverException{
    joueur.checkDying("Et il n'y eu jamais de Star Wars V !");
    ennemis.checkDying();
  }

}
  