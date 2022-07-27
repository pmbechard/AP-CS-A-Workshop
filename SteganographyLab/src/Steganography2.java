import java.awt.Color;

/**
 * Steganography class for Activity 2
 *
 */
public class Steganography2
{
  
  /**
   * Constructor for objects of class Steganography
   */
  public Steganography2()
  { 
  }
  
  /**
   * Clear the lower (rightmost) two bits in a pixel.
   * @param p pixel to be changed
   */
  public static void clearLow( Pixel p )
  {
    Color oldColor = p.getColor();
    
    p.setColor(new Color(4*(oldColor.getRed()/4),
                         4*(oldColor.getGreen()/4), 
                         4*(oldColor.getBlue()/4)));
  }
  
  /**
   * Method to test clearLow on all pixels in a Picture
   * @param pic Picture to be changed
   * @return modified picture
   */
  public static Picture testClearLow(Picture pic)
  {
    Picture p = new Picture(pic);
    Pixel[][] pixels = p.getPixels2D();
    for (int r = 0; r < pixels.length; r++)
    {
      for (int c = 0; c < pixels[0].length; c++)
      {
        clearLow(pixels[r][c]);
      }
    }
    return p;
  }
  
  /**
   * Set the lower 2 bits in a pixel to the highest 2 bits in c
   * @param p pixel to be changed
   * @param c color to be used for modification
   */
  public static void setLow(Pixel p, Color c)
  {
    clearLow(p);
    Color oldColor = p.getColor();
    int rAdd = c.getRed() / 64;
    int gAdd = c.getGreen() / 64;
    int bAdd = c.getBlue() / 64;
    p.setColor(new Color(oldColor.getRed()+rAdd,
                         oldColor.getGreen()+gAdd, oldColor.getBlue()+bAdd));
  }
  
  /**
   * Method to test setLow on all pixels in a Picture
   * @param pic picture to be changed
   * @param col color to be used in modification
   * @return modified picture
   */
  public static Picture testSetLow(Picture pic, Color col)
  {
    Picture p = new Picture(pic);
    Pixel[][] pixels = p.getPixels2D();
    for (int r = 0; r < pixels.length; r++)
    {
      for (int c = 0; c < pixels[0].length; c++)
      {
        setLow(pixels[r][c], col);
      }
    }
    return p;
  }
  
  /**
   * Sets the highest two bits of each pixel’s colors
   * to the lowest two bits of each pixel’s colors
   * @param hidden picture with hidden image
   * @return revealed picture
   */
  public static Picture revealPicture(Picture hidden)
  {
    Picture copy = new Picture(hidden);
    Pixel[][] pixels = copy.getPixels2D();
    Pixel[][] source = hidden.getPixels2D();
    for (int r = 0; r < pixels.length; r++)
    {
      for (int c = 0; c < pixels[0].length; c++)
      {   
        Color col = source[r][c].getColor();
        Pixel p = pixels[r][c];     
        p.setRed(col.getRed() % 4 * 64);
        p.setGreen(col.getGreen() % 4 * 64);
        p.setBlue(col.getBlue() % 4 * 64);
      }
    }
    return copy;
  }
  
  /**
   * Determines whether secret can be hidden in source, which is
   * true if source and secret are the same dimensions.
   * @param source is not null
   * @param secret is not null
   * @return true if secret can be hidden in source, false otherwise.
   */
  public static boolean canHide(Picture source, Picture secret)
  {
    return source.getWidth() == secret.getWidth() &&
      source.getHeight() == secret.getHeight();
  }
  
  /**
   * Creates a new Picture with data from secret hidden in data from source
   * @param source is not null
   * @param secret is not null
   * @return combined Picture with secret hidden in source
   * precondition: source is same width and height as secret
   */
  public static Picture hidePicture(Picture source, Picture secret)
  {
    Picture hidden = new Picture(source);
    Pixel[][] hPixels = hidden.getPixels2D();
    Pixel[][] sPixels = secret.getPixels2D();
    // Since the images are the same size,
    // either can be used for loop conditions.
    for (int r = 0; r < hPixels.length; r++)
    {
      for (int c = 0; c < hPixels[0].length; c++)
      {
        Pixel s = sPixels[r][c];
        setLow(hPixels[r][c], s.getColor());
      }
    }
    return hidden;
  }
  
  
  public static void main(String[] args) {
    // Activity 2.8
    Picture beach = new Picture("beach.jpg");
    Picture arch = new Picture("arch.jpg");
    System.out.println ("beach same size as arch: " + 
                        canHide(beach, arch));
    // Activity 2.9
    Picture swan = new Picture("swan.jpg");
    Picture gorge = new Picture("gorge.jpg");
    Picture combined = hidePicture(swan, gorge);
    combined.explore();
    // Activity 2.11 - pics are already loaded above
    if(canHide(swan, gorge)) {
      Picture combined1 = hidePicture(swan, gorge);
      combined1.explore();
      Picture revealed = revealPicture(combined1);
      revealed.explore();
    }
  }
}

