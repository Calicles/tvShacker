package entity;

class Joueur extends Player{
  
  
  @SuppressWarnings("unused")
private Image mort;
  
  public Joueur(String src, String srcTir, int positionX, int positionY, 
                int bordureDebut, int bordureFin, int bordureDebutY, Image m){
    super(src, srcTir, positionX, positionY, bordureDebut, bordureFin, bordureDebutY, m);
  }
  
// Permet de définir d'où part le tir
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
  
// gère les déplacements
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
// Utilisé pour "lâcher" le tir
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