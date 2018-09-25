package vue;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.image.*;


/**
 * @author François Barthélemy
 * 
 * Classe dont les instances sont des fenêtres affichées à l'écran
 * contenant une image.
 *
 */
@SuppressWarnings("serial")
public class Afficheur extends JFrame{
 private JPanel jp;
 private JPanel feuille;
 private JButton on, off, chaine1, chaine2, chaine3;
 private int[] tab;
 
 private PanelBas panelBas;
 private Controleur controleur;
 

 
 /**
  * @param pixels l'image à afficher
  */
 public Afficheur(Pixel[][] pixels){
  on= new JButton("ON");
  off= new JButton("OFF");
  chaine1= new JButton("chaine 1");
  chaine2= new JButton("chaine 2");
  chaine3= new JButton("chaine 3");
  on.setFocusable(false);
  off.setFocusable(false);
  chaine1.setFocusable(false);
  chaine2.setFocusable(false);
  chaine3.setFocusable(false);

  feuille= new JPanel();
     feuille.setLayout(null);
  int width, height;
  width = pixels.length;
  height = pixels[0].length;
  controleur= new Controleur(this, pixels);
  on.addActionListener(new ButtonListenerOn(controleur));
  off.addActionListener(new ButtonListenerOff(controleur));
  chaine1.addActionListener(new ButtonListenerChaine1(controleur));
  chaine2.addActionListener(new ButtonListenerChaine2(controleur));
  chaine3.addActionListener(new ButtonListenerChaine3(controleur));
  panelBas= new PanelBas();
  BufferedImage bim = new BufferedImage(width, height,
    BufferedImage.TYPE_INT_ARGB);
  int[] pixtab = arrayFromPix(pixels);
  tab = ( (DataBufferInt) bim.getRaster()
    .getDataBuffer() ).getData();
  System.arraycopy(pixtab, 0, tab, 0, pixtab.length);
  jp = new ImagePanel(bim);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  jp.setPreferredSize(new Dimension(bim.getWidth(),bim.getHeight()));
  panelBas.setSize(new Dimension(bim.getWidth(), 100));
  feuille.add(jp);
  feuille.add(panelBas);
  jp.setLayout(null);
  jp.add(on);
  jp.add(off);
  jp.add(chaine1);
  jp.add(chaine2);
  jp.add(chaine3);
  feuille.setPreferredSize(new Dimension(bim.getWidth()-10, bim.getHeight()+40));
  jp.setBounds(0, 0, width, height);
  panelBas.setBounds(0, height, width, 50);
  on.setBounds(480, 267, 55, 30);
  off.setBounds(480+55, 267, 60, 30); // TODO G�ometrie
  chaine1.setBounds(484, 50, 104, 30);
  chaine2.setBounds(484, 80, 104, 30);
  chaine3.setBounds(484, 110, 104, 30);
  this.add(feuille);
  this.setContentPane(feuille);
  this.addKeyListener(new ToucheListener(controleur));
  this.setTitle("OLD TV SHACKER");
  this.setFocusable(true);
  this.pack();
  this.setLocationRelativeTo(null);
  this.setResizable(false);
  this.setVisible(true);
  this.requestFocus();
 }
 
 /**
  * Méthode servant à afficher une nouvelle image dans la feneêtre.
  * 
  * Cette image doit avoir les mêmes dimensions que celle donnée en 
  * paramètre au constructeur lors de la création de l'objet sur lequel
  * la méthode est appelée.
  * @param pixels l'image à afficher
  * @throws IndexOutOfBoundsException parfois levée si pixels n'a pas
  * la même dimension que l'image précédemment affichée par l'objet.
  */
 public void update(Pixel[][] pixels){
  int[] pixtab = arrayFromPix(pixels);
  System.arraycopy(pixtab, 0, tab, 0, pixtab.length);
  jp.revalidate();
  jp.repaint();
 }
 private int[] arrayFromPix(Pixel[][] pixels){
  int[] res = new int[pixels.length*pixels[0].length];
  for (int col = 0; col< pixels.length; col++){
   for (int lig=0; lig<pixels[0].length; lig++){
    res[pixels.length*lig+col] = intFromPixel(pixels[col][lig]);
   }
  }
  return res;
 }
 private int intFromPixel(Pixel pix){
  int res = pix.getAlpha();
  res = (((res<<8) + pix.getRed()<< 8) + pix.getGreen());
  return (res<<8) + pix.getBlue();
 }
 public void informer(String s){
   panelBas.informer(s);
 }
 public void resetInformation(){
   panelBas.resetInformation();
 }
 public void cacherBouton(){
   on.setVisible(false);
   off.setVisible(false);
   chaine1.setVisible(false);
   chaine2.setVisible(false);
   chaine3.setVisible(false);
 }
 
 /** Méthode qui ferme la fenêtre.
  * 
  */
 public void fermer(){
  this.dispose();
 }

}
