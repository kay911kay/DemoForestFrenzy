/* Name: Forest Frenzy
 * Description: A demo game
 * Author: Dominik Loncar and Daniel Wang
 * Start date: 9/17/2014
 * Last edit date: 9/28/2014
 */

import java.awt.*; //used for graphics
import java.awt.event.*; //used for ActionListener
import javax.swing.*;
import java.util.ArrayList;


//create class called Background with 
//JPanel
//Runnable
public class Background extends JPanel implements ActionListener, Runnable {
  public Image img; //creates image object called img
  public Timer time; //creat Timer object called time
  public int v = 750; //spawn distance from top horizontal line
  public Thread animator; //animates stuff
  
  final int groundY = 780; //position of ground, Y, where everything spawns
  
  public Character player; //create Character object called player
  
  //declare all enemy objects
  Minion enemy1;
  Minion enemy2;
  Minion enemy3;
  Minion enemy4;
  Minion enemy5;
  Minion enemy6;
  Boss boss;
  
  String youWin = ""; //this changes to a win message when you win
  
  static Font font = new Font("SanSerif", Font.BOLD, 24); //default font
  static Font font2 = new Font("SanSerif", Font.BOLD, 100); //win font
  
  private int jumpP = 750; //needed for jump method
  private int jumpHeight = 100; //height of jump in pixels
  
  boolean jumped = false; //says whether jump has been initiated
  
  public Background() {
    //the following segment creates all the enemy objects in the correct positions
    player = new Character();
    enemy1 = new Minion(800, groundY);
    enemy2 = new Minion(1000, groundY);
    enemy3 = new Minion(1200, groundY);
    enemy4 = new Minion(1400, groundY);
    enemy5 = new Minion(1600, groundY);
    enemy6 = new Minion(1800, groundY);
    boss = new Boss(2000, groundY-160);
    
    addKeyListener(new AL()); //key listener
    setFocusable(true); 
   // ImageIcon background = new Image("images/forst.png"); //background
    img = Toolkit.getDefaultToolkit().getImage("images/forst.png"); //grabs the background
    time = new Timer(1, this); //create timer
    time.start();//starts timer
    
  }//end Background
  
  public void actionPerformed(ActionEvent e) {
    collisions(); //used for collision detection
    ArrayList bullets = Character.getBullets(); //arraylist for bullets
    for (int i = 0; i < bullets.size(); i++){
      //This is how to get a current element in an arrayList
      //similar to x[2] in a normal array
      Bullet m = (Bullet) bullets.get(i);//draw:
      if (m.getVisible() == true) //if bullet is visible
        m.move(); //move it
      else //if not
        bullets.remove(i); //remove it
    }//end for
    
    player.move(); //allows player to move
    
    //allows enemies to move and keeps them in a correct position relative to the character
    enemy1.stay(player.getHorzMovement());
    enemy1.move();
    enemy2.stay(player.getHorzMovement());
    enemy2.move();
    enemy3.stay(player.getHorzMovement());
    enemy3.move();
    enemy4.stay(player.getHorzMovement());
    enemy4.move();
    enemy5.stay(player.getHorzMovement());
    enemy5.move();
    enemy6.stay(player.getHorzMovement());
    enemy6.move();
    boss.stay(player.getHorzMovement());
    boss.move();
    
    repaint();
  }//end actionPerformed
  
  public void collisions(){
    //create all the rectangles
    Rectangle r1 = enemy1.getBounds();
    Rectangle r2 = enemy2.getBounds();
    Rectangle r3 = enemy3.getBounds();
    Rectangle r4 = enemy4.getBounds();
    Rectangle r5 = enemy5.getBounds();
    Rectangle r6 = enemy6.getBounds();
    Rectangle rb = boss.getBounds(); //this is for when the boss can damage the character
    Rectangle rb2 = boss.getBounds2(); //this is for when the boss can be damaged by the arrows
    Rectangle playerBox = player.getBounds(v);
    
    ArrayList bullets = player.getBullets();
    for (int i = 0; i < bullets.size(); i++){ //for each bullet, check if it hits the enemies
      Bullet m = (Bullet) bullets.get(i);
      Rectangle m1 = m.getBounds();
      if (r1.intersects(m1) && enemy1.getAlive()){
        enemy1.alive = false;
        m.visible = false;
      }//end if
      
      if (r2.intersects(m1) && enemy2.getAlive()){
        enemy2.alive = false;
        m.visible = false;
      }//end if
      
      if (r3.intersects(m1) && enemy3.getAlive()){
        enemy3.alive = false;
        m.visible = false;
      }//end if
      
      if (r4.intersects(m1) && enemy4.getAlive()){
        enemy4.alive = false;
        m.visible = false;
      }//end if
      
      if (r5.intersects(m1) && enemy5.getAlive()){
        enemy5.alive = false;
        m.visible = false;
      }//end if
      
      if (r6.intersects(m1) && enemy6.getAlive()){
        enemy6.alive = false;
        m.visible = false;
      }//end if
      
      if (rb2.intersects(m1) && boss.getAlive()){
        boss.getHit();
        m.visible = false;
      }//end if
    }//end for
    
    if (playerBox.intersects(r1)  && enemy1.getAlive()|| playerBox.intersects(r2)  && enemy2.getAlive()|| playerBox.intersects(r3)  && enemy3.getAlive()|| playerBox.intersects(r4)  && enemy4.getAlive()|| 
        playerBox.intersects(r5)  && enemy5.getAlive()|| playerBox.intersects(r6)  && enemy6.getAlive()|| playerBox.intersects(rb) && boss.getAlive()) //quit if main char is hit
      System.exit(0);
    if (playerBox.intersects(rb2) && boss.getAlive() == false) //print msg if you win
      youWin = "Congratulations! You Win!";
  }//end collisions
  
  public void paint(Graphics g){
    if (player.vertMovement == 1 && jumped == false) { //if up is pressed and char is not jumping
      jumped = true; //
      animator = new Thread(this);
      animator.start();
    }//end if
    
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    
    g2d.drawImage(img, 685 - player.getnX2(), -1500, null);
    g2d.drawImage(player.getImage(), player.left, v - 20, null);
    
    ArrayList bullets = Character.getBullets();
    for (int i = 0; i < bullets.size(); i++){
      //This is how to get a current element in an arrayList
      //similar to x[2] in a normal array
      Bullet m = (Bullet) bullets.get(i);//draw:
      g2d.drawImage(m.getImage(), m.getX(), m.getY(), null);
    }//end for
    
    g2d.setFont(font);
    g2d.setColor(Color.BLUE);
    g2d.drawString("Ammo left: " + player.ammo, 500, 50);
    switch (boss.getHealth()) {
      case 100:  g2d.setColor(Color.BLUE);
      break;
      case 80:  g2d.setColor(Color.GREEN);
      break;
      case 60:  g2d.setColor(Color.YELLOW);
      break;
      case 40:  g2d.setColor(Color.PINK);
      break;
      case 20:  g2d.setColor(Color.RED);
      break;
    }
    g2d.drawString("Boss health: " + boss.getHealth(), 700, 50);
    g2d.setFont(font2);
    g2d.drawString(youWin, 100, 200);
    //draw all the enemies and boss
    g2d.drawImage(enemy1.getImage(), enemy1.getX(), enemy1.getY(), null);
    g2d.drawImage(enemy2.getImage(), enemy2.getX(), enemy2.getY(), null);
    g2d.drawImage(enemy3.getImage(), enemy3.getX(), enemy3.getY(), null);
    g2d.drawImage(enemy4.getImage(), enemy4.getX(), enemy4.getY(), null);
    g2d.drawImage(enemy5.getImage(), enemy5.getX(), enemy5.getY(), null);
    g2d.drawImage(enemy6.getImage(), enemy6.getX(), enemy6.getY(), null);
    
    g2d.drawImage(boss.getImage(), boss.getX(), boss.getY(), null);
  }//end paint
  
  //checks for keypresses in player class
  private class AL extends KeyAdapter {
    public void keyReleased(KeyEvent e){
      player.keyReleased(e);
    }
    
    public void keyPressed(KeyEvent e){
      player.keyPressed(e);
    }
  }//end KeyAdapter
  
  boolean h = false;
  boolean doneJumping = false;
  
  public void cycle() { //jumping
    
    if (h == false)
      v--;
    if (v == jumpP-jumpHeight)
      h = true;
    if (h == true && v <= jumpP) {
      v++;
      if (v == jumpP)
        doneJumping = true;
    } //end if
  }//end cycle
  
  //this segment is for timing
  public void run() {
    long beforeTime, timeDiff, sleep;
    
    beforeTime = System.currentTimeMillis();
    
    while (doneJumping == false) { //for animations
      cycle();
      
      timeDiff = System.currentTimeMillis() - beforeTime;
      sleep = 10 - timeDiff;
      
      if (sleep < 0)
        sleep = 2;
      try {
        Thread.sleep(sleep);
      }//end try
      
      catch (InterruptedException e) {
        System.out.println("interrupted");
      }//end catch
      
      beforeTime = System.currentTimeMillis();
    } //end while
    
    doneJumping = false;
    h = false;
    jumped = false;
  } //end run
  
  public int getV(){
    return v;
  }
} //end board
