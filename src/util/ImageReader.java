package util;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/**
 * @author François Barthélemy
 * Classe permettant de lire des fichiers contenant des images.
 */
public class ImageReader {
 /** Lit une image au format JPEG ou au format PNG.
  * 
  * @param path chemin d'accès au fichier contenant l'image
  * @return renvoie l'image sous forme d'un tableau à trois dimensions: 
  *   colonne, ligne et composante de couleur ou d'opacité.
  * @throws IOException 
  */
 public static int[][][] readImage(String path) throws IOException{
  int[][][] res;
  int[] tab;
  //path = new ImageReader().getClass().getResource("/" + path).getFile();
  BufferedImage bufi = ImageIO.read(new File(path));
  int width = bufi.getWidth();
  int height = bufi.getHeight();
  tab = bufi.getRGB(0,0,width,height,null,0,width);
  res = new int[width][height][];
  for (int i=0; i<tab.length; i++){
   res[i%width][i/width] = explodePixel(tab[i]);
  }
  return res;
 }
 private static int[] explodePixel(int pix){
  int[] pt = new int[4];
  pt[0]= pix >> 24 & 0x000000FF;
  pt[1]= pix >> 16 & 0x000000FF;
  pt[2]= pix >> 8 & 0x000000FF;
  pt[3]= pix & 0x000000FF;
  return pt;
 }

}
