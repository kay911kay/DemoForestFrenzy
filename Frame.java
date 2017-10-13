/* Name: Forest Frenzy
 * Description: A demo game
 * Author: Dominik Loncar and Daniel Wang
 * Start date: 9/17/2014
 * Last edit date: 9/28/2014
 */

import javax.swing.*; //adds JFrame among other things

public class Frame { //create class called Frame
  
  public Frame(){
    JFrame frame = new JFrame(); //creates the JFrame object called frame
    frame.add(new Background());  //
    frame.setTitle("Forest Frenzy"); //changes title of program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //without this, app still runs, but JFrame closes on X
    frame.setSize(1440,900); //sets resolution
    frame.setVisible(true); //without this, JFrame is launched, but hidden
    frame.setLocationRelativeTo(null); //launch JFrame in the center instead of the top left, we might not need this
  }//end Frame
  
  public static void main(String[] args){//main method
    new Frame(); //creates Frame object 
  }//end main
}//end Frame class