import java.awt.Color;
import java.util.ArrayList;

/**
 * Steganography class for Activity 4
 *
 */
public class Steganography4
{

    /**
     * Constructor for objects of class Steganography
     */
    public Steganography4()
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

    /**
      * Takes a string consisting of capital letters and spaces and
      * encodes the string into an arraylist of integers.
      * The integers are 1-26 for A-Z, 27 for space, and 0 for end of
      * string. The arraylist of integers is returned.
      * @param s string consisting of capital letters and spaces
      * @return arraylist containing integer encoding of s
      */
     public static ArrayList<Integer> encodeString(String s)
     {
         String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
         ArrayList<Integer> result = new ArrayList<Integer>();
         for (int i = 0; i < s.length(); i++)
         {
             if (s.substring(i,i+1).equals(" "))
             {
                 result.add(27);
             }
             else 
             {
                 result.add(alpha.indexOf(s.substring(i,i+1))+1);
             }
         }
         result.add(0);
         return result;
     }
     
     /**
       * Returns the string represented by the codes arraylist.
       * 1-26 = A-Z, 27 = space
       * @param codes encoded string
       * @return decoded string
       */
     public static String decodeString(ArrayList<Integer> codes){
          String result="";
          String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
          for (int i=0; i < codes.size(); i++)
          {
              if (codes.get(i) == 27)
              {
                  result = result + " ";
              }
              else 
              {
                  result = result +
                    alpha.substring(codes.get(i)-1,codes.get(i));
              }
          }
          return result;
     }
     
     /**
       * Given a number from 0 to 63, creates and returns a 3-element
       * int array consisting of the integers representing the
       * pairs of bits in the number from right to left.
       * @param num number to be broken up
       * @return bit pairs in number
       */
     private static int[] getBitPairs(int num)
     {
         int[] bits=new int[3];
         int code = num;
         for (int i=0; i < 3; i++)
         {
             bits[i] = code % 4;
             code = code / 4;
         }
         return bits;
     }
    
    /**
     * Hide a string (must be only capital letters and spacees) in a
     * picture.
     * The string always starts in the upper left corner.
     * @param source picture to hide string in
     * @param s string to hide
     * @return pic with hidden string
     */
    public static Picture hideText(Picture source, String s){
        Pixel leftPixel = null;
        Picture newPic = new Picture(source);
        Pixel[][] pixels = newPic.getPixels2D();
        Color leftColor = null;
        // create the list of integers representing encoded message
        ArrayList<Integer> eCode = encodeString(s);
        int currentCharNum = 0;
        for (int row = 0;
            row < pixels.length && currentCharNum < eCode.size(); row++)
        {
            for (int col = 0; col < pixels[0].length &&
                currentCharNum < eCode.size(); col++)
            {
                leftPixel = pixels[row][col];
                clearLow(leftPixel);
                leftColor = leftPixel.getColor();
                int [] bits = getBitPairs(eCode.get(currentCharNum));
                leftPixel.setColor(new Color(leftColor.getRed()+bits[0],
                    leftColor.getGreen()+bits[1],
                    leftColor.getBlue()+bits[2]));
                currentCharNum++;
            }
        }
        return newPic;
    }
    
    /**
      * Returns a string hidden in the picture
      * @param source picture with hidden string
      * @return revealed string
      */
     public static String revealText(Picture source){
         Pixel leftPixel = null;
         Pixel[][] pixels = source.getPixels2D();
         Color leftColor = null;
         String result = "";
         ArrayList<Integer> codes = new ArrayList<Integer>();
         boolean finished = false;
         for (int row = 0; row < pixels.length && !finished; row++)
         {
             for (int col = 0; col < pixels[0].length && !finished;
                col++)
             {
                 leftPixel = pixels[row][col];
                 leftColor = leftPixel.getColor();
                 int code = leftColor.getRed() % 4 +
                   (leftColor.getGreen()% 4) * 4 +
                   (leftColor.getBlue() % 4) * 16;
                 if (code == 0) 
                 {
                     finished = true;
                 } 
                 else 
                 {
                     codes.add(code);
                 }
             }
         }
         return decodeString(codes);
    }

    public static void main(String[] args) {
       // Activity 4.4
       Picture beach = new Picture("beach.jpg");
       beach.explore();
       Picture hiddenText = hideText(beach,"THIS IS A TEST");
       hiddenText.explore();
       String reveal = revealText(hiddenText);
       if(reveal.equals("THIS IS A TEST"))
       {
           System.out.println("It worked!");
       }
       else 
       {
           System.out.println("Something went wrong!");
       }


       
    }
}

