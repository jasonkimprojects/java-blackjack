
/**
 * HandTester.java  
 *
 * @author: Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: Runner and tester program for class Hand
 * 
 *
 */
public class HandTester
{
    /**
     * Instantiates a hand object and tests all of its methods
     * 
     * @param String[] args just the standard parameter for main
     * @return none
     * 
     */
    public static void main (String [] args)
    {

        Hand hand1 = new Hand ();

        hand1.addCard(new Card("clubs","queen")); //addCard, which calls updateValue
        System.out.println(hand1.getValue()); //getValue

        hand1.addCard(new Card("hearts", "5"));
        System.out.println(hand1.getValue());

        hand1.addCard(new Card("spades", "ace"));
        System.out.println(hand1.getValue());

        System.out.println(hand1); //toString

        
        Hand hand2 = new Hand();

        hand2.addCard(new Card("spades", "ace"));
        hand2.addCard(new Card("diamonds", "10"));

        System.out.println(hand2);
        System.out.println("Blackjack :"+hand2.isBlackjack()); //isBlackjack
        System.out.println("Hand size: "+hand2.getHandSize()); //getHandSize

        System.out.println(hand2.getFaceUpCard()); //getFaceUpCard

        System.out.println(hand1.getCardList().get(0)); //getCardList

    }

}
