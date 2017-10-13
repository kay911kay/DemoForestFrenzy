/* Name: Forest Frenzy
 * Description: A demo game
 * Author: Dominik Loncar and Daniel Wang
 * Start date: 9/17/2014
 * Last edit date: 9/28/2014
 */

import java.awt.*;
import javax.swing.*;
public class Bullet {
  int x, y;//-- Controls the CURRENT location of THIS bullet
  Image img;
  int startingx;
  boolean visible;
  boolean techniqueone = false;
    boolean techniquetwo = false;
      boolean techniquethree = true;
  public Bullet(int startX, int startY){
    ImageIcon newBullet = new ImageIcon("images/arrows122.gif");
    img = newBullet.getImage();
    x = startX;
    y = startY;
    visible = true;
  }//end Bullet
  
  public int getX(){
    return x;
  }
  
  public boolean getVisible(){
    return visible;
  }
  
  public int getY(){
    return y;
  }
  
  public Image getImage(){
    return img;
  }
  
  public Rectangle getBounds(){
    return new Rectangle(x,y, 45, 15);
  }//end getBounds
  
  //move the arrow to the right
  public void move(){
   if (techniqueone == true){
    x = x + 1; //x + bullet speed
   }
    if (techniquetwo == true){
      x = x + 10;
      y = y + 2;
      System.out.println(x);
      
    }
    if (techniquethree == true){
      
      startingx = x;
      System.out.println(startingx);
      if( startingx < 200){
        y = y + 1;
       x = x +1;
      }
      if (startingx >= 50){
        y = y -1;
        x = x+1;
      }
       
    }
    // if (x%34 == 0)
    //   y++;
  //end move
}
}