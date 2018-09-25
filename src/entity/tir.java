package entity;
import type.Armement;

class Tir extends Armement{
  
  public Tir(String src){
    super(src);
    super.seDeplaceDe(0,-15);
  }
  public Tir(String src, int dplcX, int dplcY){
    super(src);
    super.seDeplaceDe(dplcX, dplcY);
  }
  
  public boolean tirVisible(){
    return super.isVisible();
  }
  public void enregistrerCoordonnees(int xdepart, int ydepart){
    x= xdepart;
    y= ydepart;
  }
  public void tirDepart(){
    visible=true;
  }
  public void tirMort(){
    visible=false;
  }
  
}