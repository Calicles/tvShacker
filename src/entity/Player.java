package entity;
import type.Animee;

abstract class Player extends Animee{
// Super classe des joueurs
  
  protected Tir tir;
  
  protected int tvDebut;
  protected int tvFin;
  protected int tvDebutY;
  protected boolean triggerPushed;
  
  public Player(String src, String srcTir, int positionX, int positionY, 
                int bordureDebut, int bordureFin, int bordureDebutY, Image m){
    super(src, positionX, positionY, m);
    tvDebutY= bordureDebutY;
    setBordure(bordureDebut, bordureFin);
    tir=new Tir(srcTir);
    triggerPushed= false;
  }
  public Player(String src, String srcTir,int dplcTirX, int dplcTirY, int positionX, int positionY, 
                int bordureDebut, int bordureFin, int bordureDebutY){
    super(src, positionX, positionY);
    tvDebutY= bordureDebutY;
    setBordure(bordureDebut, bordureFin);
    tir=new Tir(srcTir, dplcTirX, dplcTirY);
    triggerPushed= false;
  }
   
  private void setBordure(int d, int f){
    tvDebut=d;
    tvFin=f;
  }
  
//getters, j'ai vu cette manière d'écrire dans le code source d'un programmeur amateur américain et je l'ai gardé.
  public int getXTir(){return tir.getX();}
  public int getYTir(){return tir.getY();}
  public int getWidthTir(){return tir.getWidth();}
  public int getHeightTir(){return tir.getHeight();}
  public boolean tirVisible(){return tir.isVisible();}
  public Point[][] getImageTir(){return tir.getImage();}
  
// gère les tirs
  public void deplacerTir(){
    if(getYTir() > tvDebutY){
      tir.deplacer();
    }else{
      tir.tirMort();
    }
  }
  public void tire(){
    if(triggerPushed)
      enregistrerCoordonnees();
  }
  protected void tirMort(){
    tir.tirMort();
  }
  protected void enregistrerCoordonnees(){} 
  // relié au buttonListener pour valider le tir
  public void actionPushed(){}
  public void actionReleased(){
    triggerPushed=false;
  }
  
  
}