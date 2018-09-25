package entity;

class Joueur extends Player{
  
  
  @SuppressWarnings("unused")
private Image mort;
  
  public Joueur(String src, String srcTir, int positionX, int positionY, 
                int bordureDebut, int bordureFin, int bordureDebutY, Image m){
    super(src, srcTir, positionX, positionY, bordureDebut, bordureFin, bordureDebutY, m);
  }
  
// Permet de d�finir d'o� part le tir
  protected void enregistrerCoordonnees(){
    if(!tirVisible()){
      int xdepart;
      int ydepart;
      xdepart= super.getX()+((super.getWidth()/2)-(tir.getWidth()/2));
      ydepart= super.getY() -7;
      super.tir.enregistrerCoordonnees(xdepart, ydepart);
      super.tir.tirDepart();
    }
  }
  
// g�re les d�placements
  public void initDplc(){
    dplcX=0;
  }
  public void deplacer(){
    super.deplacer();
    if(x <= tvDebut)
      x=tvDebut+2;
    else if(x+width >= tvFin)
      x=tvFin-width-2;
  }
  public void enregistrerDplcGauche(){
    super.seDeplaceDe(-5,0);
  }
  public void enregistrerDplcDroit(){
    super.seDeplaceDe(5,0);
  }
// Utilis� pour "l�cher" le tir
  public void actionPushed(){
    triggerPushed=true;
  }
  public void checkDying(String msg) throws GameOverException{
   if(dying && dyingCpt== 1)
      throw new GameOverException(msg);
    else if(dying)
      died();
  }

  
}