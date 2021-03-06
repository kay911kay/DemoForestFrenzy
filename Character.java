/* Name: Forest Frenzy
 * Description: A demo game
 * Author: Dominik Loncar and Daniel Wang
 * Start date: 9/17/2014
 * Last edit date: 9/28/2014
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Character {
  
  int x, y, realtimeX, nx2, left; 
  int vertMovement; //if this value is 1, then the object is moving up(because of the keypress) and 0 if ther is no vertical movement
  int horzMovement; //if this value is 1, then the object is moving right(because of the keypress), left is -1 and no horizontal movement is 0
  Image currentImg; //currently displayed image of player
  
  ImageIcon standImg = new ImageIcon("images/walkinggod1.gif");
  ImageIcon jumpLeftImg= new ImageIcon("images/jumpLeft.png");
  ImageIcon jumpRightImg= new ImageIcon("images/jumpRight.png");
  ImageIcon leftImg = new ImageIcon("images/left.gif");
  ImageIcon attackImg = new ImageIcon("images/shoot.gif");
  
  int ammo = 100;
  static ArrayList bullets;//Holds number of bullets on screen
  
  public Character() {
    x = 0;
    left = 150; //how far from left vertical line you spawn
    realtimeX = 0;
    nx2= 685;
    y = 172;
    currentImg = standImg.getImage();
    bullets = new ArrayList();
  }//end Character
  
  public static ArrayList getBullets(){
    return bullets;
  }
  
  //used for rectangle creation for collision detection
  public Rectangle getBounds(int y){
    return new Rectangle(left, y, 161, 120);
  }
  
  //creates arrow objects and puts them in an arraylist
  public void fire(){
    if (ammo > 0){
      ammo--;
      Bullet z = new Bullet((left + 60), 685);
      bullets.add(z);
    }//end if
  }//end fire
  
  public void move() {
    x = x + horzMovement;
    nx2= nx2 + horzMovement;
    realtimeX = realtimeX + horzMovement;
  }//end move
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public int getsag() {
    return left;
  }
  
  public int getXX() {
    if (x%3 == 0)
      x = x -1;
    return x;
  }
  
  public int getnX() {
    return realtimeX;
  }
  
  public int getnX2() {
    return nx2;
  }
  
  public int getHorzMovement() {
    return horzMovement;
  }
  
  public Image getImage() {
    return currentImg;
  }
  
  //this method finds what key is pressed then changes the horzMovement value and images accordingly
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode(); //find out what key is pressed
    
    if (key == KeyEvent.VK_LEFT){ //if its the left arrow key
      horzMovement = -1; //set horzMovement to -1
      currentImg = leftImg.getImage();  //and change image
      if (vertMovement == 1)
        currentImg = jumpLeftImg.getImage();
    }//end if
    
    if (key == KeyEvent.VK_RIGHT){ //if right arrow key is pressed
      horzMovement = 1;
      currentImg = standImg.getImage(); // change image
      if (vertMovement == 1)
        currentImg = jumpRightImg.getImage();
    }//end if
    
    if (key == KeyEvent.VK_UP){ //if up is pressed
      vertMovement = 1; //
      if (horzMovement == 1) //and moving right
        currentImg = jumpRightImg.getImage();
      if (horzMovement == -1)
        currentImg = jumpLeftImg.getImage(); //and moving left
    }//end if   
    if (key == KeyEvent.VK_SPACE){ //if space is pressed, attack
      currentImg = attackImg.getImage();
    }//end if
    
    if (key == KeyEvent.VK_SPACE)
    {
      fire();
    }
  }//end keyPressed
  
  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode(); //find out what key is released
    
    if (key == KeyEvent.VK_LEFT){ //if its left, change horzMovement to 0
      horzMovement = 0;
    }//end if
    
    if (key == KeyEvent.VK_RIGHT){ //if its right, change horzMovement to 0
      horzMovement = 0;
      currentImg = standImg.getImage();
    }//end if
    
    if (key == KeyEvent.VK_UP){ //if up is pressed, change vertMovement
      vertMovement = 0;
      currentImg = standImg.getImage();
    }//end if
    
    if (key == KeyEvent.VK_SPACE){ //if space is pressed, attack
      currentImg = standImg.getImage();
    }//end if
  }//end keyReleased
}//end character
