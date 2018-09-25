package entity;

class Ennemi extends Mechant{
  
//  classe d'enemis, ils devront bouger en suivant un pattern, de droite à gauche, atteindre le bord
//  descendre et changer de direction, selon un cycle. Chaque Ennemi largue un tir.
//  Il y a deux utilisation distinct de cette classe. Une en liste chaînée via la classe EnnemiListe (avec traitement
//  par méthodes récursives. Et une en traitement individuel, lorsqu'un Niveau n'a qu'un seul ennemi 
//  à afficher dès le départ.
  
  private Ennemi suivant;

  private int yJoueur;

  public Ennemi(String src, String srcTir, int positionX, int positionY, int tvD, 
                int tvF, int yJ, int tvY, Image m){
    super(src, srcTir, positionX, positionY, tvD, tvF, tvY, m);
    yJoueur= yJ;
    seDeplaceDe(1,0);
    suivant=null;
  }
  
  public Ennemi getSuivant(){
    return suivant;
  }
  public void setSuivant(Ennemi s){
    suivant= s;
  }
  
// Gère les déplacements 
  public void deplacerEnnemi() throws GameOverException{
    if(x+dplcX > tvDebutX && (x+dplcX+width) < tvFinX){
      super.deplacer();
    }else if(toucheBordureDroite() && dplcX==1){
      seDeplaceDe(0, 15);
      super.deplacer();
      if(contacteJoueur())
        throw new GameOverException("L'Empire vous a capturé!");
      seDeplaceDe(-1,0);
    }else if (toucheBordureGauche() && dplcX==-1){
      seDeplaceDe(0, 15);
      super.deplacer();
      if(contacteJoueur())
        throw new GameOverException("L'Empire vous a capturé!");
      seDeplaceDe(1,0);
    }
  }
  private boolean contacteJoueur(){
    return y+height > yJoueur;
  }
  public boolean contacteJoueurRec(){ // Les méthodes avec Rec en signature sont récursives.
    if(this.getSuivant()==null)
      return this.contacteJoueur();
    else
      return this.contacteJoueur() || this.getSuivant().contacteJoueurRec();
  }
  private void deplacementEnGroupe(int dplx, int dply){
    seDeplaceDe(dplx, dply);
    super.deplacer();
  }
  public void deplacementEnGroupeRec(int dx, int dy){
    if(suivant==null)
      this.deplacementEnGroupe(dx,dy);
    else{
      this.deplacementEnGroupe(dx,dy);
      this.getSuivant().deplacementEnGroupeRec(dx,dy);
    }
  }
  
  private boolean toucheBordureGauche(){
    return x+dplcX <=tvDebutX;
  }
  private boolean toucheBordureDroite(){
    return x+width+dplcX >= tvFinX;
    }
  public boolean toucheBordureDroiteRec(){
    if(this.getSuivant()==null)
      return this.toucheBordureDroite();
    else
      return this.toucheBordureDroite() || this.getSuivant().toucheBordureDroiteRec();
  }
  public boolean toucheBordureGaucheRec(){
    if(this.getSuivant()==null)
      return this.toucheBordureGauche();
    else
      return this.toucheBordureGauche() || this.getSuivant().toucheBordureGaucheRec();
  }
//---------------------------------------------------------
// Gère les comportements de tir
  private void enregistrerCoordonnees(){
    int xdepart;
    int ydepart;
    xdepart= super.getX()+super.getWidth()-tir.getWidth();
    ydepart= super.getY()+5;
    tir.enregistrerCoordonnees(xdepart, ydepart);
    tir.tirDepart();
  }
  private void enregistrerCoordonneesDessous(){
    int xdepart;
    int ydepart;
    xdepart= (super.getX()+super.getWidth()/2)-tir.getWidth()/2;
    ydepart= super.getY()+super.getHeight()-25;
    tir.enregistrerCoordonnees(xdepart, ydepart);
    tir.tirDepart();
  }
  public void tire(int debut, int fin){
    if(!tirIsVisible() && ligneDeMire(debut, fin)){
      enregistrerCoordonnees();
      tir.tirDepart();
    }
  }
  private boolean ligneDeMire(int debut, int fin){
    return (debut> x && debut< x+width) || (fin>x && fin<x+width);
  }
  public void tireRec(int posx, int longueur){
    if(!tirIsVisible() && ligneDeMire(posx, longueur)){
      enregistrerCoordonneesDessous();
      tir.tirDepart();
    }
    if(this.getSuivant()!=null)
      this.getSuivant().tireRec(posx, longueur);
  }
  public void deplacerTirRec(){
    if(getYTir() < tvYFin){
      tir.deplacer();
    }else{
      tir.tirMort();
    }
    if(this.getSuivant()!=null)
      this.getSuivant().deplacerTirRec();
  } 
  public Tir interceptTir(int i, int j){
    if((i>= this.getXTir() && i<= (this.getXTir()+(this.getWidthTir()-1))) && 
       (j >= this.getYTir() && j<= (this.getYTir()+(this.getHeightTir()-1))))
      return this.tir;
    else if(this.getSuivant()==null)
      return null;
    else
      return this.getSuivant().interceptTir(i,j);
  }
//----------------------------------------------------------
  public Ennemi intercept(int i, int j){
    if((i>= this.getX() && i<= (this.getX()+(this.getWidth()-1))) && 
            (j >= this.getY() && j<= (this.getY()+(this.getHeight()-1))))
      return this;
    else if(this.getSuivant()==null)
      return null;
    else
      return this.getSuivant().intercept(i,j);
  }

  public void eliminer(Ennemi e){
    if(this.getSuivant()==e)
      this.setSuivant(this.getSuivant().getSuivant());
    else
      this.getSuivant().eliminer(e);
  }
  public void checkDying() throws GameOverException{
    if(dying && dyingCpt== 1)
      throw new GameOverException("Twilight Win!");
    else if(dying){
      died();
    }
  }
  public Ennemi checkDyingGroupe(){
    if(this.getSuivant()== null && !this.isDying())
      return null;
    else if(this.isDying()){
      this.died();
      return this;
    }
    return this.getSuivant().checkDyingGroupe();
  }
  
}
