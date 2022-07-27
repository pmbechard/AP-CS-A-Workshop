import java.awt.Color;

/**
 * Steganography class for Activity 1
 *
 */
public class Steganography1
{
  
  /**
   * Constructor for objects of class Steganography
   */
  public Steganography1()
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
  
  public static void main(String[] args) {
    // Activity 1.8
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    Picture copy = testClearLow(beach);
    copy.explore();
    // Activity 1.12
    Picture beach2 = new Picture("beach.jpg");
    beach2.explore();
    Picture copy2 = testSetLow(beach2, Color.PINK);
    copy2.explore();
    // Activity 1.14
    Picture copy3 = revealPicture(copy2);
    copy3.explore();
  }
}

