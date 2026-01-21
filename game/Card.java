package game;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Card {

   //instance variables 
   private int value;
   private String name;
   private String suit;
   private BufferedImage image; 
   private boolean selected;


   public Card(int value, String name, String suit, String fileName){
      //initialization
		this.value = value;
		this.name = name;
		this.suit = suit;
      selected = false;


      // strip off spaces from fileName
      fileName = fileName.substring(1, fileName.length()-1);
      try{
				image = ImageIO.read(new File(fileName));
			} catch (IOException ex) {
            System.out.println("Error Reading: " +fileName+ ex);
            System.exit(0);
         }
	 }
   //returns the value
	 protected int getValue(){
      return this.value;
   }

   //returns the name
   protected String getName() {
      return name;
   }

   //returns the image
   protected BufferedImage getImage() {
      return image;
   }

   //returns the suit
   protected String getSuit() {
      return suit;
   }

   //return selected
   protected boolean getSelected(){
    return selected;
   }

   

}
