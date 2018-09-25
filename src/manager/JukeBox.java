package manager;
class JukeBox{

// Les méthodes de cette objet gèrent les changements de musique 
  
  private AudioManager musicChannel1;
  private AudioManager musicChannel2;
  private AudioManager musicChannel3;
  private AudioManager musicFin;
  private AudioManager channelRunning;
  
  public JukeBox(String srcCh1, String srcCh2, String srcCh3, String srcFin){
    musicChannel1= new AudioManager(srcCh1);
    musicChannel1.pause(); // je fais un appel à pause() pour que le temps de chargement soit dans la construction
    musicChannel2= new AudioManager(srcCh2);                                   //et non durant le jeu.
    musicChannel2.pause();
    musicChannel3= new AudioManager(srcCh3);
    musicChannel3.pause();
    musicFin= new AudioManager(srcFin);
    musicFin.pause();
    channelRunning= musicChannel1;
  }
  
  public void switchChannel1(){
    if (channelRunning!= musicChannel1){
      pause();
      channelRunning= musicChannel1;
      play();
    }
  }
  public void switchChannel2(){
    if(channelRunning!= musicChannel2){
      pause();
      channelRunning= musicChannel2;
      play();
    }
  }
  public void switchChannel3(){
    if(channelRunning!= musicChannel3){
      pause();
      channelRunning= musicChannel3;
      play();
    }
  }
  public void switchFin(){
    channelRunning= musicFin;
    play();
  }
  public void pause(){
    channelRunning.pause();
  }
  public void play(){
    channelRunning.play();
  }
  public void arret(){
    channelRunning.arret();
  }
  
}
    