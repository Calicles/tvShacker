package level;
import entity.Ennemi;
import entity.Joueur;
import type.Image;

class NiveauStatic extends Niveau{
// Niveau proche de Invaders mais avec un fond statique et un unique ennemi
  
  private Joueur joueur;
  private Ennemi ennemi;
  private Image decors;
   
  public NiveauStatic(String t, Pixel[][] arrPlan, String dec, String srcJoueur, String srcEnnemi, 
                      String srcTirJoueur,String srcTirEnnemi, Image srcEnnemiMort, Image srcJoueurMort){
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
    joueur= new Joueur(srcJoueur, srcTirJoueur, (tvDebutX + decors.getWidth()/2), tvHeight - (tvHeight/5),
                     tvDebutX, tvWidth, tvDebutY, srcJoueurMort);
    ennemi= new Ennemi(srcEnnemi, srcTirEnnemi, (tvDebutX + (tvWidth/2)), tvDebutY-5, 
                     tvDebutX, tvWidth, joueur.getY(), tvHeight, srcEnnemiMort);
    inGame=true;
  }
  
  public Point[][] peindre(){
    int deltaX=0;
    int deltaY=0;
    
    if(tvOn){
      for(int i=tvDebutX;i<tvWidth;i++){
        for(int j=tvDebutY;j<tvHeight;j++){
          
          if(chevaucheJoueur(i,j)){
            deltaX= (i - joueur.getX());
            deltaY= (j - joueur.getY());
            if(joueur.getImage()[deltaX][deltaY].getAlpha()!=0){
              if(chevaucheTirEnnemi(i,j)){
                joueur.dying();
              }
              arrierePlan.copier(i,j, joueur.getPoint(deltaX,deltaY).getAlpha(), 
                                 joueur.getPoint(deltaX,deltaY).getRed(),
                                 joueur.getPoint(deltaX,deltaY).getGreen(), 
                                 joueur.getPoint(deltaX,deltaY).getBlue());
            }else{
              deltaX= (i - tvDebutX);
              deltaY= (j - tvDebutY);
              arrierePlan.copier(i,j, decors.getPoint(deltaX,deltaY).getAlpha(), 
                                 decors.getPoint(deltaX,deltaY).getRed(), 
                                 decors.getPoint(deltaX,deltaY).getGreen(), 
                                 decors.getPoint(deltaX,deltaY).getBlue());
            }
          }else if(chevaucheTirEnnemi(i,j)){
            deltaX= (i - ennemi.getXTir());
            deltaY= (j - ennemi.getYTir());
            if(ennemi.getImageTir()[deltaX][deltaY].getAlpha()!=0)
              arrierePlan.copier(i,j, ennemi.getImageTir()[deltaX][deltaY].getAlpha(), 
                                 ennemi.getImageTir()[deltaX][deltaY].getRed(), 
                                 ennemi.getImageTir()[deltaX][deltaY].getGreen(), 
                                 ennemi.getImageTir()[deltaX][deltaY].getBlue());
            
          }else if(chevaucheEnnemi(i,j)){
            deltaX= (i - ennemi.getX());
            deltaY= (j - ennemi.getY());
            if(ennemi.getImage()[deltaX][deltaY].getAlpha()!=0){
              if(chevaucheTir(i,j)){
                joueur.tirMort();
                ennemi.dying();
              }
              arrierePlan.copier(i,j, ennemi.getPoint(deltaX,deltaY).getAlpha(), 
                                 ennemi.getPoint(deltaX,deltaY).getRed(),
                                 ennemi.getPoint(deltaX,deltaY).getGreen(), 
                                 ennemi.getPoint(deltaX,deltaY).getBlue());
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
// Detecte quoi peindre
  private boolean chevaucheJoueur(int i, int j){
    return ((i>= joueur.getX() && i< (joueur.getX()+(joueur.getWidth()))) && 
            (j >= joueur.getY() && j< (joueur.getY()+(joueur.getHeight()))));
  }
  private boolean chevaucheEnnemi(int i, int j){
    return ((i>= ennemi.getX() && i< (ennemi.getX()+(ennemi.getWidth()))) && 
            (j >= ennemi.getY() && j< (ennemi.getY()+(ennemi.getHeight()))));
  }
  private boolean chevaucheTir(int i, int j){
    if(tirVisible())
      return ((i>= joueur.getXTir() && i< (joueur.getXTir()+(joueur.getWidthTir()))) && 
              (j >= joueur.getYTir() && j< (joueur.getYTir()+(joueur.getHeightTir()))));
    else
      return false;
  }
  private boolean chevaucheTirEnnemi(int i, int j){
    if(ennemi.tirIsVisible()){
      return ((i>= ennemi.getXTir() && i< (ennemi.getXTir()+(ennemi.getWidthTir()))) && 
             (j >= ennemi.getYTir() && j< (ennemi.getYTir()+(ennemi.getHeightTir()))));
    }else{
      return false;
    }
  }
//-------------------------------------------------------------
  public void deplacerEnnemi() throws GameOverException{
    ennemi.deplacerEnnemi();
    if(ennemi.tirIsVisible()){
      ennemi.deplacerTir();
    }
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
  public void ennemiTire(){
    ennemi.tire(joueur.getX(), joueur.getWidth());
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
  public void checkDying() throws GameOverException{
    joueur.checkDying("Twilight est transformée en pierre!");
    ennemi.checkDying();
  }
    
}
