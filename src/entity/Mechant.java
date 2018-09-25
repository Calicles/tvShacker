package entity;
import type.Animee;
import type.Image;

abstract class Mechant extends Animee{
// super classe de Ennemi et EnnemiAvecScroll
  
  protected Image mort;
  protected Tir tir;
  protected int tvDebutX;
  protected int tvFinX;
  protected int tvYFin;

  public Mechant(String src, int positionX, int positionY, int tvD, int tvXF, Image m){
    super(src, positionX, positionY, m);
    tvDebutX= tvD;
    tvFinX= tvXF; 
    tir=null;
  }
  public Mechant(String src, String srcTir, int positionX, int positionY, int tvD, int tvF, int tvY, Image m){
    super(src, positionX, positionY, m);
    tvDebutX= tvD;
    tvFinX= tvF; 
    tvYFin= tvY;
    tir= new Tir(srcTir, 0, 3);
  }
  
// getters
  public boolean tirIsVisible(){return tir.isVisible();}
  public int getXTir(){return tir.getX();}
  public int getYTir(){return tir.getY();}
  public int getWidthTir(){return tir.getWidth();}
  public int getHeightTir(){return tir.getHeight();}
  public Point[][] getImageTir(){return tir.getImage();}
  
  public void tire(int debutJoueur, int finJoueur){
    if(!tirIsVisible()){
      int xdepart;
      int ydepart;
      xdepart= super.getX()+((super.getWidth()/2)-(tir.getWidth()/2));
      ydepart= super.getY() + super.getHeight();
      tir.enregistrerCoordonnees(xdepart, ydepart);
      tir.tirDepart();
    }
  }
  public void deplacerTir(){
    if(getYTir() < tvYFin){
      tir.deplacer();
    }else{
      tir.tirMort();
    }
  }
  
}