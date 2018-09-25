package type;
class Animee extends Image{
  
//  Cette classe h�rite d'Image et accouche de toutes les autres classes qui contiennent des images (Armement,
//  Playeur, Mechant...). Chacun ayant des coordonn�es et devant g�rer des d�placements 
//  ou devant afficher l'arri�re plan.
  protected Image mort;
  
  protected int width;
  protected int height;
  protected int dplcX;
  protected int dplcY;
  protected int x;
  protected int y;
  protected int dyingCpt; // compte le nombre de tour pour laisser afficher le sprite de mort d'un anim�.
  protected boolean dying; // v�rifie si un personnage est toujours vivant.
  
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

// Utilis� par cha�ne d'appel jusque dans le cycle du Thread de la classe Controleur.
  public void deplacer(){
    x += dplcX;
    y = (y+ dplcY);
  }
  
// Permet d'adapter le vecteur de d�placement selon l'instance (joueur, ennemi, tir)
  public void seDeplaceDe(int dpHor, int dpVert){
    dplcX= dpHor;
    dplcY= dpVert;
  }
  
// G�re la fin de vie
  public void dying(){
    dying=true;
  }
  public void died(){
    image= mort.getImage();
    dyingCpt++;
    width=mort.getWidth(); // Important pour le calcul de la m�thode peindre() dans Niveau
    height=mort.getHeight();
  }
  
}