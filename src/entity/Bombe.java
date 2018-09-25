package entity;
import type.Armement;

class Bombe extends Armement{
  
  public Bombe(String src, int xdepart, int ydepart){
    super(src,xdepart,ydepart);
    super.seDeplaceDe(0,1);
  }
  
  
}