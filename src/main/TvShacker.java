package main;
import vue.Afficheur;

class TvShacker{
  
//  Classe comprenant la m�thode main. Elle doit uniquement initialiser la fen�tre.

  
  public static void main(String[] args){
    
    new Afficheur(new Image("images/tv4.png").getImage());
  }
}