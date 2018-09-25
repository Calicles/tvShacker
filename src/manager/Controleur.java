package manager;
import vue.Afficheur;

class Controleur implements Runnable{
  
//  Cette classe est censée incarnée le Controleur du modèle MVC, j'ai tenté de recrer ce pattern...
//  le modèle sera les différentes classes Niveau qui recueille toutes les infos, et la vue incarnée par Afficheur.
//  Elle est le thread logique (celle qui lance le jeu et l'animation, 
//  ainsi que la musique via la classe JukeBox.
  
  private NiveauTypeInvader chaine1;
  private NiveauAventure chaine2;
  private NiveauStatic chaine3;
  private NiveauFin fin;
  private Niveau tampon;
  private Afficheur afficheur;
  private JukeBox jukebox;
  private Thread thread;
  
  private boolean tvOn;
  private long depart;
  private long tpsFin;
  private long interval;
  private final long attente= 40; // Constante de temps pour calculer le timing du rafraichissement.

  
  public Controleur(Afficheur a, Pixel[][] arrpl){
    afficheur=a;
    jukebox= new JukeBox("images/Star Wars.wav", "images/Indiana Jones.wav", "images/Open Up Your Eyes.wav", 
                         "images/Jurassic Park.wav");
    chaine1= new NiveauTypeInvader("Star Wars", arrpl, "images/espace.png", "images/xwing3.png", 3,
                                   "images/tie fighter petit.png", "images/laser bleu.png", 
                                   "images/laser rouge.png", new Image("images/explosion.png"));
    Image[] tab= {new Image("images/indiana0.png"), new Image("images/indiana1.png"), 
      new Image("images/indiana2.png"), new Image("images/indiana3.png"), 
      new Image("images/indiana4.png")};
    
    Image[] tab2= {new Image("images/indi ennemi0.png"), new Image("images/indi ennemi1.png"), 
      new Image("images/indi ennemi2.png"), new Image("images/indi ennemi3.png"), 
      new Image("images/indi ennemi4.png"), new Image("images/indi ennemi5.png"),
      new Image("images/indi ennemi6.png"), new Image("images/indi ennemi7.png"),
      new Image("images/indi ennemi8.png"), new Image("images/indi ennemi9.png"),
      new Image("images/indi ennemi10.png"), new Image("images/indi ennemi11.png")};
    
    chaine2= new NiveauAventure("Indiana Jones", arrpl, "images/souk2.png", "images/indiana0.png", 
                                tab, "images/balle.png", "images/indi ennemi0.png", 
                                tab2, new Image("images/indi ennemi mort.png"));
    chaine3= new NiveauStatic("My Little Pony the Movie", arrpl, "images/ponyVille.png", "images/twilight.png", 
                              "images/tempest petit2.png","images/laser bleu.png", 
                              "images/globe2.png", new Image("images/tempest mort.png"), 
                              new Image("images/twilight pierre.png"));
    tampon= chaine1;
    tvOn=false;
    thread= new Thread(this);
  }
  
  @Override
  public void run() {
    while(chaine1.isInGame() || chaine2.isInGame() || chaine3.isInGame()){
      
      depart= System.currentTimeMillis();
      while(tvOn && tampon.isInGame()){
        peindre();
        try{
          animerCycle();
        }catch(GameOverException e){
          gameOver(e);
          jukebox.arret();
          informer();
        }
        timing();
        sleep(calculInterval());
        depart= System.currentTimeMillis(); //Le départ est ici, car il y en a un avant la boucle, avec celui-ci
      }                                     //ils prennent en compte le temps de calcul du test du loop while.
      
      peindre();
      if(!tvOn){
        peindre();
        try{
          synchronized(this){ //J'ai choisi ce système pour éviter les tours de boucle inutiles en cas de tvOff.
            wait();
          }
        }catch(InterruptedException e){}
      }
      sleep(2); //ralenti les loops en cas de niveau fini.
    }
    viderMemoire(); // Prépare de la place pour le chargement des images plus lourdes de NiveauFIn en affectant null
    sleep(2500);                                            // aux autres chaînes, la JVM pourra ainsi nettoyer
    informer("Interruption des programmes");
    jukebox.switchFin();
    fin();
    
  }
  
  public void start(){ //Déclenché par appui du bouton on.
    tvOn= true;
    jukebox.play();
    informer();
    thread.start();
  }
  
// Cycle de rafraichissement
  private void peindre(){
    afficheur.update(tampon.peindre());
  }
  private void animerCycle() throws GameOverException{
    deplacerJoueur();
    tire();
    deplacerEnnemi();
    checkDying();
  }
  private void deplacerEnnemi() throws GameOverException{
    tampon.deplacerEnnemi();
  }
  private void checkDying() throws GameOverException{
    tampon.checkDying();
  }
  private void deplacerJoueur(){
    tampon.deplacerJoueur();
  }
  private void tire() throws GameOverException{
    tampon.tire();
    tampon.ennemiTire();
  }
//------------------------------------------------------------
// Liées aux events clavier-souris
  public void actionPushed(){
    tampon.actionPushed();
  }
  public void actionReleased(){
    tampon.actionReleased();
  }
  public void initDplcJoueur(){
    tampon.initDplcJoueur();
  }
  public void enregistrerDplcJoueurGauche(){
    tampon.enregistrerDplcJoueurGauche();
  }
  public void enregistrerDplcJoueurDroit(){
    tampon.enregistrerDplcJoueurDroit();
  }
  public void tvOn(){
    jukebox.play();
    tampon.tvOn();
    tvOn= true;
    synchronized(this){ // réveille le thread
      notify();
    }
    informer();
  }
  public void tvOff(){
    jukebox.pause();
    tampon.tvOff();
    tvOn=false;
    afficheur.resetInformation();
  }
  public void switchChaine1(){
    if(isTvOn()){
      jukebox.switchChannel1();
      tampon= chaine1;
      informer();
    }
  }
  public void switchChaine2(){
    if(isTvOn()){
      jukebox.switchChannel2();
      tampon= chaine2;
      informer();
    }
  }
  public void switchChaine3(){
    if(isTvOn()){
      jukebox.switchChannel3();
      tampon= chaine3;
      informer();
    }
  }
//--------------------------------------------------
  private boolean isTvOn(){
    return tvOn;
  }
  private void gameOver(GameOverException goe){
    tampon.gameOver(goe);
  }
  private String getTitre(){
    return tampon.getTitre();
  }
  private void informer(){
    afficheur.informer(getTitre());
  }
  private void informer(String msg){
    afficheur.informer(msg);
  }
  
// Liées au rapport de timing de rafraichissement
  private long calculInterval(){
    interval= attente-(tpsFin - depart);
    if(interval<0)
      interval=0;
    return interval;
  }
  private void timing(){
    tpsFin= System.currentTimeMillis();
  }
  private void sleep(int time){
    try{
      Thread.sleep(time);
    }catch(InterruptedException e){}
  }
  private void sleep(long time){
    try{
      Thread.sleep(time);
    }catch(InterruptedException e){}
  }
//----------------------------------------------------
  private void fin(){
    Image[] tmp={new Image("images/tvfin0.png"), new Image("images/tvfin1.png"), new Image("images/tvfin2.png"), 
      new Image("images/tvfin3.png"),
      new Image("images/tvfin4.png"), new Image("images/tvfin5.png"), new Image("images/tvfin6.png"), 
      new Image("images/tvFin7.png"),
      new Image("images/tvfin8.png"), new Image("images/tvfin9.png"), new Image("images/tvfin10.png")};
    fin= new NiveauFin(tmp, afficheur);
    fin.fin();
  }
  private void viderMemoire(){
    chaine1= null;
    chaine2= null;
    chaine3= null;
  }
  @SuppressWarnings("unused")
private void fermer(){
    afficheur.fermer();
  }

}