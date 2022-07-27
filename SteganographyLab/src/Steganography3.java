import java.awt.Color;
import java.util.ArrayList;

/**
 * Steganography class for Activity 3
 *
 */
public class Steganography3
{
  
  /**
   * Constructor for objects of class Steganography
   */
  public Steganography3()
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
    Pixel [][] pixels = p.getPixels2D();
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
    Pixel [][] pixels = p.getPixels2D();
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
   * true if secret is same size or smaller than source
   * @param source is not null
   * @param secret is not null
   * @return true if secret can be hidden in source, false otherwise.
   */
  public static boolean canHide(Picture source, Picture secret)
  {
    return source.getWidth() >= secret.getWidth() &&
      source.getHeight() >= secret.getHeight();
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
  
  /**
   * Hides secret in picture, starting at a given point in picture
   * @param source is not null
   * @param secret is not null
   * @param startRow row in source where hidden pic will start
   * @param startColumn column in source where hidden pic will start
   * @return combined Picture with secret hidden in source
   * precondition: source is same or greater width and height as secret
   */
  public static Picture hidePicture(Picture source, Picture secret, 
                                    int startRow, int startColumn){
    Picture hidden = new Picture(source);
    Pixel[][] hPixels = hidden.getPixels2D();
    Pixel[][] sPixels = secret.getPixels2D();
    int width = sPixels[0].length;
    int height = sPixels.length;
    for (int r = startRow, srow=0; r < hPixels.length && srow < height;
         r++,srow++)
    {          
      for (int c = startColumn, scol=0; c < hPixels[0].length-1 && 
           scol < width; c++, scol++)
      {
        Pixel s = sPixels[srow][scol];
        setLow(hPixels[r][c], s.getColor());
      }
    }
    return hidden;
  }
  
  /**
   * Checks to see if pic1 and pic2 match
   * @param pic1 first picture
   * @param pic2 second picture
   * @return true if pics match; false otherwise
   */
  public static boolean isSame(Picture pic1, Picture pic2)
  {
    Pixel[][] pixels = pic1.getPixels2D();
    Pixel[][] otherPixels = pic2.getPixels2D();
    Pixel pixel = null;
    Pixel otherPixel = null;
    if(pic1.getWidth() != pic2.getWidth() || 
       pic1.getHeight() != pic2.getHeight())
    {
      return false;
    }
    for (int row = 0; row < pixels.length;row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        pixel = pixels[row][col];
        otherPixel = otherPixels[row][col];
        
        if(pixel.getRed() != otherPixel.getRed() ||
           pixel.getGreen() != otherPixel.getGreen() ||
           pixel.getBlue() != otherPixel.getBlue())
        {    
          return false;
        }
      }
    }
    return true;
  }
  
  /**
   * Returns arraylist of points where pictures differ
   * @param pic1 first picture
   * @param pic2 second picture
   * @return list of points where pic1 and pic2 differ or
   *  returns empty list if they are not the same size
   */
  public static ArrayList<Point> findDifferences(Picture pic1, 
                                                 Picture pic2)
  {
    ArrayList<Point> list = new ArrayList<Point>();
    Pixel[][] pixels = pic1.getPixels2D();
    Pixel[][] otherPixels = pic2.getPixels2D();
    Pixel pixel = null;
    Pixel otherPixel = null;
    if(pic1.getWidth() != pic2.getWidth() || 
       pic1.getHeight() != pic2.getHeight())
    {
      return list;
    }
    
    for (int row = 0; row < pixels.length;row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        pixel = pixels[row][col];
        otherPixel = otherPixels[row][col];
        
        if(pixel.getRed() != otherPixel.getRed()
             || pixel.getGreen() != otherPixel.getGreen()
             || pixel.getBlue() != otherPixel.getBlue())
        {
          list.add(new Point(row, col));
        }
      }
    }
    return list;
  }
  
  
  /**
   * Draws red rectangle around area containing points
   * @param pic source picture
   * @param points list of points
   * @return pic with rectangle drawn
   * pre-condition all of points are on the Picture pic
   * pre-condition - points contains at least two points
   */
  public static Picture showDifferentArea (Picture pic, 
                                           ArrayList<Point> points)
  {
    Picture result = new Picture(pic);
    //set starting points so that first Point examined 
    // changes it,
    int minRow = pic.getHeight()-1, minCol = pic.getWidth() - 1;
    int maxRow = 0, maxCol = 0;
    // find the upper left and lower right point in the 
    //  arraylist myPoints
    for(Point p: points)
    {
      int row = p. getRow();
      int col = p.getCol();
      if(row < minRow)
      {
        minRow = row;
      }
      if(row > maxRow)
      {
        maxRow = row;
      }
      if(col < minCol) 
      {
        minCol = col;
      }
      if(col > maxCol)
      {
        maxCol = col;
      }
    }
    Pixel pixel = null;
    //color top and bottom of bounding rectangle
    for(int col = minCol; col <= maxCol; col++)
    {   
      pixel = result.getPixel(col, minRow);
      pixel.setColor(Color.red);
      pixel = result.getPixel(col, maxRow);
      pixel.setColor(Color.red);
    }
    //color sides of bounding rectangle
    for(int row = minRow + 1; row < maxRow; row++)
    {
      pixel = result.getPixel(minCol,row);
      pixel.setColor(Color.red);
      pixel = result.getPixel(maxCol, row);
      pixel.setColor(Color.red);
    }
    return result;
  }
  
  
  public static void main(String[] args) {
    // Activity 3.1
    Picture beach = new Picture("beach.jpg");
    Picture robot = new Picture("robot.jpg");
    Picture flower1 = new Picture("flower1.jpg");
    beach.explore();
    // these lines hide 2 pictures
    Picture hidden1 = hidePicture(beach, robot, 65, 208);
    Picture hidden2 = hidePicture(hidden1, flower1, 280, 110);
    hidden2.explore();
    Picture unhidden = revealPicture(hidden2);
    unhidden.explore();
    // Activity 3.2
    Picture swan = new Picture("swan.jpg");
    Picture swan2 = new Picture("swan.jpg");
    
    System.out.println("Swan and swan2 are the same: " +
                       isSame(swan, swan2));
    swan = testClearLow(swan);
    System.out.println("Swan and swan2 are the same "+
                       "(after clearLow run on swan): " + isSame(swan, swan2));
    // Activity 3.3
    Picture arch = new Picture("arch.jpg");
    Picture arch2 = new Picture("arch.jpg");
    Picture koala = new Picture("koala.jpg");
    Picture robot1 = new Picture("robot.jpg");
    ArrayList<Point> pointList = findDifferences(arch, arch2);
    System.out.println("PointList after comparing two identical pictures " 
                         + "has a size of " + pointList.size());
    pointList = findDifferences(arch, koala);
    System.out.println("PointList after comparing two different "+ 
                       " size of pictures " + "has a size of " + pointList.size());
    arch2 = hidePicture(arch, robot1,65,102);
    pointList = findDifferences(arch, arch2);
    System.out.println("Pointlist after hiding a picture has a size of "
                         + pointList.size());
    arch.show();
    arch2.show();
    
    // Activity 3.4
    Picture hall = new Picture("femaleLionAndHall.jpg");
    Picture robot2 = new Picture("robot.jpg");
    Picture flower2 = new Picture("flower1.jpg");
    
    // hide pictures
    Picture hall2 = hidePicture(hall, robot2, 50, 300);
    Picture hall3 = hidePicture(hall2, flower2, 115, 275);
    hall3.explore();
    if(!isSame(hall, hall3))
    {
      Picture hall4 = showDifferentArea(hall,
                                        findDifferences(hall, hall3));
      hall4.show();
      Picture unhiddenHall3 = revealPicture(hall3);
      unhiddenHall3.show();
    }    
    
    
  }
}

