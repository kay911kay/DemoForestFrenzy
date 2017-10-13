/* Name: Forest Frenzy
 * Description: A demo game
 * Author: Dominik Loncar and Daniel Wang
 * Start date: 9/17/2014
 * Last edit date: 9/28/2014
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Minion {
  
  int x, y;
  Image still;
  boolean alive;
  int moveDelay = 1;
  final int MOVESPEED = 5; //the bigger the number, the slower the movement
  
  ImageIcon standImg = new ImageIcon("images/trump.gif");
  ImageIcon deadImg = new ImageIcon("images/6.png");
  
  public Rectangle getBounds(){
    return new Rectangle(x,y, 67, 54);
  }
  
  public Minion(int startX, int startY) {
    x = startX;
    y = startY;
    still = standImg.getImage();
    alive = true;
  }//end Character
  
  //this method keeps the object relative to the character, without this, the object moves when the player moves
  public void stay(int dx){
    x = x - dx;
    if (alive == false){
      still = deadImg.getImage();
    }
  }
  
  //move the object towards the left if it is alive
  public void move(){
    if (alive == true){
      if (moveDelay%MOVESPEED == 0){
        x--; 
        moveDelay++;
      }//end if
      else
        moveDelay++;
    }//end if
  }//end move
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public boolean getAlive() {
    return alive;
  }
  
  public Image getImage() {
    return still;
  }
  
}//end character