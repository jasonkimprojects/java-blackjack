
/**
 * CardTester.java 
 *
 * @author: Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: Tests every method of class Card by a runner program
 * 
 *
 */

public class CardTester 
{
    /**
     * @param String[] args the default parameter for main
     * Tests every method in class Card
     */
   public static void main(String[] args)
   {
       System.out.println('\u000C');
       
       Card c = new Card("spades", "ace");
       System.out.println(c); //tests toString
       System.out.println("Original value: "+c.getNumValue()); //tests getNumValue
       c.setAceValue(); //changes numValue of 11 to 1
       System.out.println("Changed value: "+c.getNumValue());
       System.out.println(c.getSuit()); //tests getSuit
       System.out.println(c.getRank()); //tests getRank
       
       System.out.println();
       
       Card c2 = new Card("hearts", "king");
       System.out.println(c2);
       System.out.println("Original value: "+c2.getNumValue());
       c2.setAceValue(); //does nothing
       System.out.println("Changed value: "+c2.getNumValue());
       System.out.println(c2.getSuit());
       System.out.println(c2.getRank());
       
       

       
   }
}
