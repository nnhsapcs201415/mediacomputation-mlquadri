

/**
 * 
   collage contains at least 4 copies of the image
   
   the 4 copies includes the original image and at least 3 modifications of the original 
   image
   
        you may find it convenient to start with a large, blank picture ( Picture canvas =
        new Picture(height, width); )
   
        you must invoke the cropAndCopy method that you wrote in the programming practice 
        activity
   
   modifications must include one or more filters (changing colors) and one or more 
   transformations (scaling, cropping, mirroring)
   
   the collage must run on its own (don't invoke FileChooser.pickAFile())
   
        instead copy the source image file into the images folder and specify the file 
        name to the Picture constructor
   
            For example: Picture sourcePic = new Picture("beach.jpg");
   
   the collage must be saved as an image file, by default it will be saved in the 
   images folder:
   
        finalPic.write("YourName.jpg");
   
        this code doesn't work for some students for unknown reasons. You may need 
        to specify an absolute path. For example:
            finalPic.write("C:\\Users\\gschmit\\Desktop\\GitHub\\unit6MediaComp\\images\\
            Schmit.jpg");

 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CollageLab
{
    /**
     * Default constructor for objects of class CollageLab
     */
    public CollageLab()
    {
        
    }
    
    public static void main()
    {
        
    }
}
