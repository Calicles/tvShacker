package type;

class Point implements Pixel{
  
//  classe qui implémente Pixel
    
  private int rouge, vert, bleu, alpha;
  
  public Point(int a, int r, int v, int b){
    rouge=r;
    vert=v;
    bleu=b;
    alpha=a;
  }
  
  //getters
  public int getRed(){return rouge;}
  public int getGreen(){return vert;}
  public int getBlue(){return bleu;}
  public int getAlpha(){return alpha;}
  
//  Cette méthode permet, par la suite, la mise à jour d'une image 
//  sans obligatoirement passer par une instruction new, utilisée dans la classe Niveau-->peindre().
  public void copier(int a, int r, int v, int b){
    alpha=a;
    rouge=r;
    vert=v;
    bleu=b;
  }
  
}