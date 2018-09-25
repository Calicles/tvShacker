package manager;
import java.io.*;
import javax.sound.sampled.*;
import java.io.IOException;

class AudioManager extends Thread{
  
////  Cette classe lance la musique de chaque niveau, elle sera invoquée dans la classe JukeBox
//    elle même invoquée dans Controleur.
//    J'ai féfini le thread en deamon pour ne pas empêcher la fermeture de la JVM en cas de nécessité.
  AudioInputStream ais=null;
  SourceDataLine line;
  
  String source;
  boolean playing;
  boolean levelRunning;
  
  public AudioManager(String s){
    playing=true;
    levelRunning= true;
    source=s;
    this.setDaemon(true);
    this.start();
  }
  
  @SuppressWarnings("unused")
public void run(){
    File fichier= new File(source);
    try{
      AudioFileFormat format= AudioSystem.getAudioFileFormat(fichier);
    }catch(UnsupportedAudioFileException u){
      u.printStackTrace();
    }catch(IOException i){
      i.printStackTrace();
    }
    
    try{
      ais= AudioSystem.getAudioInputStream(fichier);
    }catch(UnsupportedAudioFileException u){
      u.printStackTrace();
    }catch(IOException i){
      i.printStackTrace();
    }
    
    AudioFormat audioFormat= ais.getFormat();
    DataLine.Info info= new DataLine.Info(SourceDataLine.class,audioFormat);
    
    try{
      line= (SourceDataLine) AudioSystem.getLine(info);
    }catch(LineUnavailableException l){
      l.printStackTrace();
      return;
    }
    
    try{
      line.open(audioFormat);
    }catch(LineUnavailableException l){
      l.printStackTrace();
      return;
    }
    
    line.start();
    try{
      byte bytes[]= new byte[1024];
      int bytesRead=0;
      while(((bytesRead= ais.read(bytes, 0, bytes.length)) != -1) && levelRunning){
        synchronized(this){
          if(!playing){
            try{
              wait();
            }catch(InterruptedException ie){}
          }
        }
        line.write(bytes,0, bytesRead);
      }
    }catch(IOException i){
      i.printStackTrace();
      return;
    }
  }
// Toutes le méthodes sont liées aux envents de changement de niveau.
  public void play(){
    playing=true;
    synchronized(this){
      notify();
    }
  }
  public void pause(){
    synchronized(this){
      playing= false;
    }
  }
  public void arret(){
    levelRunning= false;
  }
  
}