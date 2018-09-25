package type;

class Point implements Pixel{
  
//  classe qui impl�mente Pixel
    
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
  
//  Cette m�thode permet, par la suite, la mise � jour d'une image 
//  sans obligatoirement passer par une instruction new, utilis�e dans la classe Niveau-->peindre().
  public void copier(int a, int r, int v, int b){
    alpha=a;
    rouge=r;
    vert=v;
    bleu=b;
  }
  
}