package type;
class Fond extends Image{
  
//  Cette classe crée une image défilante (de haut en bas) pouvant être utilisé comme arrière plan. Ici utilisée
// dans la classe NiveauTypeInvaders pour créé l'illusion d'avancer du vaisseau.
  
  private Point[][][] animation;
  private int sprite;
  
  public Fond(String srce){
    super(srce);
    initAnimationTab();
    sprite=0;
  }
// Appelée dans la classe Niveau pour peindre la scène, l'indice est incrémenté à chaque rafraichissement de l'image. 
  public Point[][] update(){
    
    if(sprite>=animation.length)
      sprite=0;
    sprite++;
    return animation[sprite-1];
    
  }
  
//  Cette méthode cré un tableau contenant à chaque indice incrémenté, un dixième 
//  de l'image décalée vers le bas. 
//  à chaque appel de peindre (dans la classe Niveau), 
//  c'est l'indice suivant (par getFond()) du tableau qu'elle peint, puis l'indice revient à zero à length.
  private void initAnimationTab(){
    animation= new Point[18][][];
    Point[][][] tab1= deroule(image);
    Point[][] renverse= reversX();
    Point[][][] tab2= deroule(renverse);

    for(int i=0; i<animation.length; i++){
      if(i<animation.length/2)
        animation[i]=tab1[i];
      else
        animation[i]=tab2[i-(animation.length/2)];
    }
  }
// Inverse le tableau selon une symétrie de l'axe des y.
  private Point[][] reversX(){
    Point[][] tab= new Point[image.length][image[0].length];
    for(int i=0, z=(image.length-1); i<image.length; i++,z--){
      for(int j=0;j<image[0].length;j++){
        tab[i][j]= new Point(image[z][j].getAlpha(), image[z][j].getRed(),
                             image[z][j].getGreen(), image[z][j].getBlue());
      }
    }
    return tab;
  }
// coupe la tableau d'un dixième à chaque alvéole incrémenté.
  private Point[][][] deroule(Point[][] modele){
    Point[][][] tab= new Point[9][image.length][image[0].length];
    int indiceY;
    tab[0]= modele;
    for(int indice= 1;indice <tab.length;indice++){
      for(int i=0; i<image.length; i++){
        indiceY= ((image[0].length/10)*9);
        for(int j=0; j<image[0].length; j++){
          if(tab[indice-1][i][j]==null)
            System.out.println("indice:  "+indice+"  i:  "+i+"   j:   "+j);
          tab[indice][i][j]= new Point(tab[indice-1][i][indiceY].getAlpha(), 
                                             tab[indice-1][i][indiceY].getRed(), 
                                             tab[indice-1][i][indiceY].getGreen(),
                                             tab[indice-1][i][indiceY].getBlue());
          if(j== (image[0].length/10)-1)
            indiceY=0;
          
          indiceY++;
        }
      }
    }
    return tab;
  }
  
}
                      
                     