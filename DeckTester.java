
/**
 * DeckTester.java  
 *
 * @author: Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: Tests every method in class Deck by a runner 
 * 
 *
 */
public class DeckTester
{
    /**
     * @param String[] args the default parameter for main
     * Tests every method in class Deck
     */
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        Deck deck = new Deck();

        System.out.println(deck);
        System.out.println(deck.getDeck().get(0)); //gets the first card in the deck
        System.out.println(deck.getDeck().remove((int)(Math.random()*(deck.getDeck().size()))));
        //removes a random card from the deck
        
    }
}
