import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    public void redFilter(String c, int by)
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                if(!(c.equals("r")))
                {
                    pixelObj.setRed(pixelObj.getRed()/by);
                }
                if(!(c.equals("b")))
                {
                    pixelObj.setBlue(pixelObj.getBlue()/by);
                }
                if(!(c.equals("g")))
                {
                    pixelObj.setGreen(pixelObj.getGreen()/by);
                }
            }
        }
    }

    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }

    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(255-(pixelObj.getRed()));
                pixelObj.setGreen(255-(pixelObj.getGreen()));
                pixelObj.setBlue(255-(pixelObj.getBlue()));
            }
        }
    }

    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        int average;
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                average = (pixelObj.getRed()+pixelObj.getGreen()+pixelObj.getBlue())/3;
                pixelObj.setRed(average);
                pixelObj.setGreen(average);
                pixelObj.setBlue(255-(pixelObj.getBlue()));
            }
        }
    }

    public void fixUnderWater()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(pixelObj.getBlue()-20);
                pixelObj.setGreen(pixelObj.getGreen()-60);
            }
        }
    }


    public Picture scaleNew(Picture sourcePicture, int factor)
    {
        Pixel copyPixel = null;
        Pixel pastePixel = null;
        int pasteRow = 0;
        int pasteCol = 0;
        Pixel[][] copyPixels = sourcePicture.getPixels2D();
        Picture newPic = new Picture((copyPixels.length*factor), (copyPixels[0].length*factor));
        Pixel[][] pastePixels = newPic.getPixels2D();
        for (int row = 0; row < (copyPixels.length); row+=3)
        {
            pasteCol = (pastePixels[row].length);
            for (int col = 0; col < (copyPixels[row].length); col+=3)
            {
                copyPixel = copyPixels[row][col];
                pastePixel = pastePixels[pasteRow][pasteCol];
                for(int row2 = 0; row2>factor; row2++)
                {
                    for(int col2 = 0; col2>factor; col2++)
                    {
                        pastePixel = pastePixels[pasteRow+row2][pasteCol*3+col2];
                    }
                }
                pasteCol+=3;
            }
            pasteRow+=3;
        }
        return newPic;
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length / 2; row++)
        {
            for (int col = 0; col < width; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[pixels.length- (1 + row)][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length / 2; row++)
        {
            for (int col = 0; col < width; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[pixels.length- (1 + row)][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        } 
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {
                count++;
                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
        System.out.println(count);
    }

    public void mirrorArms()
    {
        int mirrorPoint1 = 190;
        int mirrorPoint2 = 195;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        // Right
        for (int row = 159; row < 190; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 105; col < 169; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[mirrorPoint1 + (mirrorPoint1 - row)][col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
        //Left
        for (int row = 172; row < 195; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 239; col < 293; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[mirrorPoint2 + (mirrorPoint2 - row)][col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    public void mirrorGull()
    {
        int mirrorPoint = 341;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        for (int row = 235; row < 320; row++)
        {
            for (int col = 237; col < mirrorPoint; col++)
            {
                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    public void cropAndCopy( Picture sourcePicture, int startSourceRow, int endSourceRow, 
    int startSourceCol, int endSourceCol, int startDestRow, int startDestCol )
    {
        Pixel copyPixel = null;
        Pixel pastePixel = null;
        int pRow = startDestRow;
        int pCol = startDestCol;
        Pixel[][] copyPixels = sourcePicture.getPixels2D();
        Pixel[][] pastePixels = this.getPixels2D();
        for (int row = startSourceRow; row < endSourceRow; row++)
        {
            pCol = startDestCol;
            for (int col = startSourceCol; col < endSourceCol; col++)
            {
                copyPixel = copyPixels[row][col];
                pastePixel = pastePixels[pRow][pCol];
                pastePixel.setColor(copyPixel.getColor());
                pCol++;
            }
            pRow++;
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this