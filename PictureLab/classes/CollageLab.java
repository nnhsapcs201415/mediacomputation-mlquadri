

/**
 * 
   *collage contains at least 4 copies of the image
   
   *the 4 copies includes the original image and at least 3 modifications of the original 
   image
   
        *you may find it convenient to start with a large, blank picture ( Picture canvas =
        new Picture(height, width); )
   
        *you must invoke the cropAndCopy method that you wrote in the programming practice 
        activity
   
  *modifications must include one or more filters (changing colors) and one or more 
   transformations (scaling, cropping, mirroring)
   
   *the collage must run on its own (don't invoke FileChooser.pickAFile())
   
        *instead copy the source image file into the images folder and specify the file 
        name to the Picture constructor
   
            *For example: Picture sourcePic = new Picture("beach.jpg");
   
   *the collage must be saved as an image file, by default it will be saved in the 
   images folder:
   
        *finalPic.write("YourName.jpg");
   
        *this code doesn't work for some students for unknown reasons. You may need 
        to specify an absolute path. For example:
            finalPic.write("C:\\Users\\gschmit\\Desktop\\GitHub\\unit6MediaComp\\images\\
            Schmit.jpg");

 * 
 * @author (Mlquadri) 
 * @version (1)
 */
public class CollageLab
{
    public static void main()
    {
        Picture origanalPic = new Picture("White_Tiger.jpg");
        Picture pic = new Picture(145,274);
        //Origanal
        origanalPic.explore();
        
        //Change 1 (cropAndCopy ) 
        pic.cropAndCopy( origanalPic, 22, 167, 25, 299, 0, 0 );
        pic.explore();
        
        //Change 2 (Filter)
        pic.negate();
        pic.explore();
        
        //Change 3 (Transformations )
        pic.mirrorVertical();
        pic.explore();
        
        //Change 4 (Extra)
        pic.redFilter("r",4);
        pic.explore();
        
        //Saving
        pic.write("Muhammed Quadri.jpg");
    }
}
