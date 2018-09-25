package main;
import vue.Afficheur;

class TvShacker{
  
//  Classe comprenant la méthode main. Elle doit uniquement initialiser la fenêtre.

  
  public static void main(String[] args){
    
    new Afficheur(new Image("images/tv4.png").getImage());
  }
}