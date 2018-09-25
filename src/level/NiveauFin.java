package level;
import type.Image;
import vue.Afficheur;

class NiveauFin extends Niveau{
// J'ai choisi de placer la fin dans un Niveau aussi pour la décorreler de la classe Controleur
  
  Image[] fin;
  Afficheur afficheur;
  
  public NiveauFin(Image[] f, Afficheur a){
    fin= f;
    afficheur= a;
  }
  
  public void fin(){
    afficheur.cacherBouton();
    for(int i=0; i<fin.length;i++){
        afficheur.update(fin[i].getImage());
        attendre(100);
    }
    informer("Le service n'est plus assuré..", 2000);
    informer("pour cause de dinosaure");
    informer("Et le syndicat des vélociraptors..",2000);
    informer("n'a pu s'accorder avec celui des T-Rex..", 2000);
    informer("pour reprendre l'antenne!");
    informer("Vous écoutez actuellement leur hymne");
    informer("Enjoy!!");
    informer("Merci d'avoir suivi le TV SHACKER!");
    informer("A bientôt pour de nouvelles aventures...");
    informer("FIN...");
  }
  private void informer(String msg, int tps){
    afficheur.informer(msg);
    attendre(tps);
  }
   private void informer(String msg){
    afficheur.informer(msg);
    attendre(3300);
  }
  private void attendre(int time){
    try{
      Thread.sleep(time);
    }catch(InterruptedException e){}
  } 
  
}
    
  