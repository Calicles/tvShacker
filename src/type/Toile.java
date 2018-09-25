package type;

class Toile extends Image{
// Classe utilisé pour créer la tv et une copie pour simuler la tv éteinte (car l'original est repeinte)
  
  public Toile(Pixel[][] plan){
    super(plan.length, plan[0].length);
    initToile(plan);
  }
  
  private void initToile(Pixel[][] p){
    for(int i=0;i<image.length;i++){
      for(int j=0;j<image[0].length;j++){
        image[i][j]=new Point(p[i][j].getAlpha(), p[i][j].getRed(), 
                              p[i][j].getGreen(), p[i][j].getBlue());
      }
    }
  }
  public void copier(int x, int y, int alpha, int red, int green, int blue){
    getPoint(x,y).copier(alpha, red, green, blue);
  }
  
}