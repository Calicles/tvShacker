package type;
class Animee extends Image{
  
//  Cette classe hérite d'Image et accouche de toutes les autres classes qui contiennent des images (Armement,
//  Playeur, Mechant...). Chacun ayant des coordonnées et devant gérer des déplacements 
//  ou devant afficher l'arrière plan.
  protected Image mort;
  
  protected int width;
  protected int height;
  protected int dplcX;
  protected int dplcY;
  protected int x;
  protected int y;
  protected int dyingCpt; // compte le nombre de tour pour laisser afficher le sprite de mort d'un animé.
  protected boolean dying; // vérifie si un personnage est toujours vivant.
  
  public Animee(String src, int xDepart, int yDepart){
    super(src);
    width=super.getWidth();
    height=super.getHeight();
    x=xDepart;
    y=yDepart;
    dying=false;
  }
  public Animee(String src, int xDepart, int yDepart, Image m){
    super(src);
    width=super.getWidth();
    height=super.getHeight();
    x=xDepart;
    y=yDepart;
    dying=false;
    mort=m;
  }
  public Animee(String src){
    super(src);
    width=super.getWidth();
    height=super.getHeight();
  }
  
  //getter
  public int getX(){return x;}
  public int getY(){return y;}
  public boolean isDying(){return dying;}
  public int getCpt(){return dyingCpt;}

// Utilisé par chaîne d'appel jusque dans le cycle du Thread de la classe Controleur.
  public void deplacer(){
    x += dplcX;
    y = (y+ dplcY);
  }
  
// Permet d'adapter le vecteur de déplacement selon l'instance (joueur, ennemi, tir)
  public void seDeplaceDe(int dpHor, int dpVert){
    dplcX= dpHor;
    dplcY= dpVert;
  }
  
// Gère la fin de vie
  public void dying(){
    dying=true;
  }
  public void died(){
    image= mort.getImage();
    dyingCpt++;
    width=mort.getWidth(); // Important pour le calcul de la méthode peindre() dans Niveau
    height=mort.getHeight();
  }
  
}