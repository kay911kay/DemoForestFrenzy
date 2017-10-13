/* Name: Forest Frenzy
 * Description: A demo game
 * Author: Dominik Loncar and Daniel Wang
 * Start date: 9/17/2014
 * Last edit date: 9/28/2014
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Boss {
  
  int x, y, health;
  Image still;
  boolean alive;
  int moveDelay = 1;
  final int MOVESPEED = 7; //the bigger the number, the slower the movement
  
  ImageIcon standImg = new ImageIcon("images/bestmush.gif");
  ImageIcon deadImg = new ImageIcon("images/mushdies.gif");
  
  public Rectangle getBounds(){
    return new Rectangle(x,y, 625, 265);
  }
  
  public Rectangle getBounds2(){
    return new Rectangle(x+420,y, 300, 265);
  }
  
  public Boss(int startX, int startY) {
    x = startX;
    y = startY;
    still = standImg.getImage();
    alive = true;
    health = 100;
  }//end Character
  
  public void stay(int dx){
    x = x - dx;
    if (alive == false){
      y = 500;
      still = deadImg.getImage();
    }
  }
  
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
  
  public void getHit(){
    health = health - 5;
    if (health <= 0)
      alive = false;
  }
  
  public int getX() {
    return x;
  }
  
  public int getHealth() {
    return health;
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
