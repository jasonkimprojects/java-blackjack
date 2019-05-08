
/**
 * Game.java 
 *
 * @author: Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 *
 * 
 *
 */

public class Game 
{
    /**
     * @param String[] args the standard parameter for main
     * 
     * Brief Program Description:
     * Runner program for the game
     */
   public static void main(String[] args)
   {
       Dealer dealer = new Dealer();
       dealer.start(); //instantiates Dealer object and starts the game
       //the start method instantiates the Player object.
   }
}
