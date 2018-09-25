package type;

/**
 * @author François Barthélemy
 * Interface définissant des méthodes d'accès aux composantes d'un pixel. 
 */
public interface Pixel {

    /**
     * @return la composante rouge du pixel qui est un entier compris
     *  entre 0 et 255
     */
    int getRed();
    /**
     * @return la composante verte du pixel qui est un entier compris
     *  entre 0 et 255
     */
    int getGreen();
    /**
     * @return la composante bleue du pixel qui est un entier compris
     *  entre 0 et 255
     */
    int getBlue();
    /**
     * @return la composante d'opacité du pixel qui est un entier compris
     *  entre 0 et 255 (0 pour un point transparent, 255 pour un point opaque)
     */
    int getAlpha();
}
