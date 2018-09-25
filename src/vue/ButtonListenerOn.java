package vue;

import java.awt.event.*;
// Ensemble de classe qui gère les events de click sur les boutons.

import manager.Controleur;

class ButtonListenerOn implements ActionListener{
 
 private Controleur controleur;
 private boolean isStart;
 
 ButtonListenerOn(Controleur c){
  controleur=c;
  isStart= false;
  
 }
 public void actionPerformed(ActionEvent e) {
   if(!isStart){
     controleur.start();
     isStart=true;
   }else
     controleur.tvOn();
 }
}
//----------------------------

class ButtonListenerOff implements ActionListener{
  private Controleur controleur;
  
  ButtonListenerOff(Controleur c){
    controleur=c;
  }
  public void actionPerformed(ActionEvent e){
    controleur.tvOff();
  }
}
//----------------------------

class ButtonListenerChaine1 implements ActionListener{
  Controleur controleur;
  
  ButtonListenerChaine1(Controleur c){
    controleur=c;
    
  }
  public void actionPerformed(ActionEvent e) {
    controleur.switchChaine1();
  }
}
//----------------------------

class ButtonListenerChaine2 implements ActionListener{
  Controleur controleur;
  
  ButtonListenerChaine2(Controleur c){
    controleur=c;
    
  }
  public void actionPerformed(ActionEvent e) {
    controleur.switchChaine2();
  }
}
//-----------------------------

class ButtonListenerChaine3 implements ActionListener{
  Controleur controleur;
  
  ButtonListenerChaine3(Controleur c){
    controleur=c;
    
  }
  public void actionPerformed(ActionEvent e) {
    controleur.switchChaine3();
  }
}
 