package type;

// Super-classe de Tir, a terme je voudrais rajouter différents armement (tête-chercheuse, laser...)
class Armement extends Animee{
  
  protected boolean visible;
  
  public Armement(String src, int xdepart, int ydepart){
    super(src, xdepart, ydepart);
    visible= false;
  }
  public Armement(String src){
    super(src);
    visible= false;
  }
  
  public void deplacer(){
    if(visible)
      super.deplacer();
  }
  public boolean isVisible(){
    return visible;
  }
  
}