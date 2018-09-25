package entity;
class EnnemiListe{
  
//  Liste chainée d'Ennemi. Cette classe traite le groupe.
  
  private Ennemi debutListe;
  private int nbrEnnemi;
  private int tvDebut;
  private int tvFin;
  private int dplcX;
  
  public EnnemiListe(int nbrE, String src, String srcTir, int posX, int posY, int tvD, int tvF, int yJ, 
                     int tvY, Image mort){
    debutListe= new Ennemi(src, srcTir, posX, posY, tvD, tvF, yJ, tvY, mort);
    nbrEnnemi=nbrE;
    tvDebut=tvD;
    tvFin= tvF;
    initListe(src, mort, srcTir, yJ, tvY);
    dplcX=1;
  }
// Créé la chaîne
  private void initListe(String s, Image m, String sTir, int y, int tvY){
    Ennemi e=debutListe;
    int nbr=1;
    
    while(nbr<=nbrEnnemi-1){
      e.setSuivant(new Ennemi(s, sTir, e.getX()+e.getWidth()+15, e.getY(), tvDebut, tvFin, y, tvY, m));
      e=e.getSuivant();
      nbr++;
    }
  }
//--------------------------------------------------------
  public void deplacerEnnemi() throws GameOverException{
    if(!toucheBordureGauche() && dplcX==-1)
      deplacerGroupe(dplcX,0);
    if(toucheBordureGauche() && dplcX==-1){
      if(contacteJoueur())
        throw new GameOverException("L'Empire vous a capturé!");
      deplacerGroupe(0,15);
      dplcX=1;
    }else if(!toucheBordureDroite() && dplcX==1)
      deplacerGroupe(dplcX,0);
    else if(toucheBordureDroite() && dplcX==1){
      if(contacteJoueur())
        throw new GameOverException("L'Empire vous a capturé");
      deplacerGroupe(0,15);
      dplcX=-1;
    }
  }
  private void deplacerGroupe(int dx, int dy){
    debutListe.deplacementEnGroupeRec(dx,dy);
  }
  private boolean toucheBordureDroite(){
    return debutListe.toucheBordureDroiteRec();
  }
  private boolean toucheBordureGauche(){
    return debutListe.toucheBordureGaucheRec();
  }
  private boolean contacteJoueur(){
    return debutListe.contacteJoueurRec();
  }
  public Ennemi intercept(int i, int j){
    return debutListe.intercept(i,j);
  }
//--------------------------------------------------------
  public void tire(int posx, int longueur){
    debutListe.tireRec(posx, longueur);
  }
  public void deplacerTir(){
    debutListe.deplacerTirRec();
  }

  public Tir interceptTir(int i, int j){
    return debutListe.interceptTir(i,j);
  }
//---------------------------------------------------------
// Gère le retrait de la liste d'un ennemi mort
  private void eliminer(Ennemi e) throws GameOverException{
    if(debutListe==e && debutListe.getSuivant()==null)
      throw new GameOverException("The rebellion win!!"); 
    else if(e==debutListe)
      debutListe= debutListe.getSuivant();
    else 
      debutListe.eliminer(e);
  }
  public void checkDying() throws GameOverException{
    Ennemi e= debutListe.checkDyingGroupe();
    if(e!=null && e.getCpt()>=4)
      eliminer(e);
  }
  
}