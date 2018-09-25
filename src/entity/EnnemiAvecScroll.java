package entity;
import type.Image;

class EnnemiAvecScroll extends Mechant{
// Classe avec Animation
  
  private Image[] scroll;
  private int indice; // sert à indiquer le sprite à afficher dans l'animation
  
  public EnnemiAvecScroll(String src, int positionX, int positionY, int tvD, int tvF, Image[] tab, Image m){
    super(src, positionX, positionY, tvD, tvF, m);
    scroll= initAnimationTab(tab);
    indice= 0;
  }
// Boucle l'animation dans un array (il faut inverser l'ordre les sprites pour finir les mouvement)
  private Image[] initAnimationTab(Image[] tab){
    Image[] newTab= new Image[tab.length*2-1];
    int indice=tab.length-1;
    
    for(int i=0; i<newTab.length; i++){
      if(i<tab.length)
        newTab[i]= tab[i];
      else{
        newTab[i]= tab[indice];
        indice--;
      }
    }
    return newTab;
  }
// Renvoi le bon sprite à peindre à la classe NiveauAventure
  public Point[][] updateImage(){
    if(dying)
      return image;
    else if(indice<= scroll.length-2){
      indice++;
    }else
      indice=0;
    return scroll[indice].getImage();
  }
  
  public void checkDying() throws GameOverException{
    if(dying && dyingCpt==1)
      throw new GameOverException("Et Indiana continua sa quête du graal!");
    else if (dying){
      died();
    }
  }
  
}
    