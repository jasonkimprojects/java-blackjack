import java.util.ArrayList;
/**
 * Deck.java  
 *
 * @author: Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description:
 * 
 *
 */
public class Deck
{
    private ArrayList<Card> deck = new ArrayList<Card>();
    private String[] suits = {"spades", "diamonds", "hearts", "clubs"};
    private String[] ranks = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * Constructor: creates a full deck of cards using a unique combination
     * of the arrays of suits and ranks above with a nested foreach loop
     * @param NONE
     */
    public Deck()
    {
        for(String suit: suits)
        {
            for(String rank: ranks)
            {
                deck.add(new Card(suit, rank)); //nested foreach loop that goes over every combination
                //of suit and rank listed in the two arrays above
            }
        }
 
    }
    
    /**
     * @param NONE
     * @return an ArrayList<Card> deck the main instance variable of this class
     */
    public ArrayList<Card> getDeck()
    {
        return deck;
    }

    /**
     * @param NONE
     * @return a String that has a description of every card in the deck,
     * followed by the number of cards in the deck (just for diagnosis)
     */
    public String toString()
    {
        String values = "";
        for(Card card: deck)
        {
            values = values + card.toString() +"\n";
        }
        return values+"\n"+deck.size()+" cards in deck";
    }

    
}
