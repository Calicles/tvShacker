package entity;
import type.Image;

public class JoueurAvecScroll extends Player{
  
// Classe joueur avec animation, dans ce projet, je n'ai pu le faire ce déplacer car j'ai manqué de sprite...
// J'ai eu différentes planches de dessin et soit le personnage tirait soit il avançait, j'ai du faire un choix pour
// répondre au temps de programmation imparti.
  
  private Image[] scroll;
  private Image[] debout;
  private Image[] tampon;
  private int indice;
  
  public JoueurAvecScroll(String src, Image[] tab, String srcTir, int positionX, int positionY, 
                int bordureDebut, int bordureFin, int bordureDebutY){
    super(src, srcTir, 3, 0, positionX, positionY, bordureDebut, bordureFin, bordureDebutY);
    scroll= initAnimationTab(tab);
    debout= new Image[1];
    debout[0]= scroll[0];
    indice= 0;
    tampon= debout;
  }
  
// Permet de finir l'animation qui s'arreterait une fois le pistolet sorti. L'ordre est inversé pour revenir à l'état
// initial à la fin du mouvement.
  private Image[] initAnimationTab(Image[] tab){
    Image[] newTab= new Image[tab.length*2-1];
    int indice=4;
    
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
// trouve d'où part le tir
  protected void enregistrerCoordonnees(){
   if(!tirVisible()){
    int xdepart;
    int ydepart;
    xdepart= super.getX()+super.getWidth();
    ydepart= super.getY() + super.getHeight()/2-10 ;
      tir.enregistrerCoordonnees(xdepart, ydepart);
      tir.tirDepart();
    }
  }
// Appellé part l'appui sur espace, la méthose lance l'animation
  public void actionPushed(){
    tampon= scroll;
    
  }
  public void actionReleased(){
    triggerPushed=false;
  }
// Envoi le sprite du personnage 
  public Point[][] updateImage(){
    if(tampon==scroll){
      if(indice<=23){
        indice++;
        if(indice>0 && indice <3)
          return scroll[1].getImage();
        else if(indice>=3 && indice<6){
          triggerPushed= true;
          return scroll[2].getImage();
        }else if(indice >=6 && indice< 9)
          return scroll[3].getImage();
        else if(indice>=9 && indice <12)
          return scroll[4].getImage();
        else if(indice>= 12 && indice < 15)
          return scroll[5].getImage();
        else if(indice>= 15 && indice <18)
          return scroll[6].getImage();
        else if( indice>=18 && indice< 21)
          return scroll[7].getImage();
        else if(indice>= 21 && indice<= 24)
          return scroll[8].getImage();
      }else{
        tampon=debout;
        indice=0;
      }
    }
    return tampon[0].getImage();
  }
  public void tire(){
    if(triggerPushed)
      enregistrerCoordonnees();
  }
  public void deplacer(){
    deplacerTir();
  }
  public void checkDying() throws GameOverException{
    if(dying)
      throw new GameOverException("Et Il n'y eu jamais d'Indiana 4...");
  }

  
  public void initDplc(){}
  public void enregistrerDplcGauche(){}
  public void enregistrerDplcDroit(){}
  
  
  
}
  