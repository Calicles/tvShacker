package type;
import java.io.IOException;

//  Cette classe est la mère de tous les sprites, elle accouche des classes Fond et Animee et transitivement
//  de Joueur, Bombe, Tir, Ennemi. Permet la conversion en tableau de pixel.

class Image{
  
  protected int[][][] tampon;
  protected Point[][] image;
  
  public Image(String srce){
    try{
      tampon= ImageReader.readImage(srce);
    }catch(IOException ioe){
      ioe.printStackTrace();
    }
    image=new Point[tampon.length][tampon[0].length];
    traduireImage(tampon);
  }
  public Image(int width, int height){
    image= new Point[width][height];
  }
  
//  getters
  public int getWidth(){return image.length;}
  public int getHeight(){return image[0].length;}
  public Point getPoint(int pixX, int pixY){return image[pixX][pixY];}
  public Point[][] getImage(){return image;}
  

  private void traduireImage(int[][][] tab){
    for(int i=0;i<tab.length;i++){
      for(int j=0;j<tab[0].length;j++){
        image[i][j]=new Point(tab[i][j][0], tab[i][j][1], tab[i][j][2], tab[i][j][3]);
      }
    }
  }
  
}
  