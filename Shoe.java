import java.util.ArrayList;
import java.util.Collections;
/**
 * Shoe.java  
 *
 * @author: Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description:
 * 
 *
 */
public class Shoe
{
    /**
     * A shoe consists of an ArrayList of Cards
     */
    private ArrayList<Card> shoe = new ArrayList<Card>();
    private final int DECK_NUMBER;

    /**
     * @param int numDecks the number of decks to be shuffled in the shoe
     * Adds every card in each deck to the shoe and then shuffles the shoe
     */
    public Shoe(int numDecks)
    {
        DECK_NUMBER = numDecks;
        for(int i=0; i<numDecks; i++)
        {
            Deck deck = new Deck();
            for(Card c: deck.getDeck())
            {
                shoe.add(c); //adds every card from each deck to the shoe 
            }
        }
        Collections.shuffle(shoe); //shuffles the arraylist
    }

    /**
     * @param none
     * @return the card at the top of the shoe
     */
    public Card drawCard()
    {
        if(shoe.size() == 0) //checks if shoe is empty. If so, refills the shoe
        {
            shoe = new ArrayList<Card>();
            for(int i=0; i<DECK_NUMBER; i++)
            {
                Deck deck = new Deck();
                for(Card c: deck.getDeck())
                {
                    shoe.add(c); //adds every card from each deck to the shoe 
                }
            }
            Collections.shuffle(shoe); //shuffles the arraylist
        }
        return shoe.remove(0); //draws from top of deck
    }

    /**
     * @param none
     * @return a String printing out information about every card in the shoe
     * in addition to the number of cards in the shoe
     */
    public String toString()
    {
        String values = "";
        for(Card card: shoe)
        {
            values = values + card.toString() +"\n";
        }
        return values+"\n"+shoe.size()+" cards in shoe";
    }

    /**
     * @param none
     * @return an ArrayList of Cards which is the instance variable of this class
     */
    public ArrayList<Card> getShoe()
    {
        return shoe;
    }
}
