import java.util.ArrayList;
/**
 * Hand.java  
 *
 * @author: Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description:
 * 
 *
 */
public class Hand
{
    private ArrayList<Card> hand;
    private int value;
    public Hand ()
    {
        hand = new ArrayList<Card>();
        value = 0;
    }

    /**
     * Adds a card to the hand
     * 
     * @ param - Card to be added to the hand
     * @ return - none
     */
    public void addCard (Card c)
    {
        hand.add(c);
        updateValue(); //re-calculates the sum of the hand
        //and updates the ace value if necessary
    }

    /**
     * adds up the individual values of the cards in order to calculate the value of the hand
     */
    public void updateValue ()
    {
        value = 0; //value initialized again
        for (Card c: hand)
        {
            value += c.getNumValue(); //simple summation foreach loop
        }
        if (value > 21) 
        {
            for (Card c: hand)
            {
                if (c.getRank().equals("ace")) //this happens with every card addition
                    c.setAceValue(); //so only one ace is changed at a time, despite how this looks
            }
            value = 0;
            for (Card c: hand)
            {
                value += c.getNumValue(); //recalculates the sum
            }
        }
    }
    
    /**
     * @return the sum of the cards in the hand
     */
    public int getValue ()
    {
        return value;
    }
    
    /**
     * Determines if the hand is a blackjack
     * @return a boolean that is true if and only if the hand is blackjack and false otherwise
     */
    public boolean isBlackjack()
    {
        if(hand.size() == 2 && value == 21)
            return true;
        else return false;
    }
    
    /**
     * @return the number of cards in the hand, used to determine 5 card charlie
     */
    public int getHandSize()
    {
        return hand.size(); 
    }

    /**
     * @return the dealer's second card (faces up)
     * Not to be used for the player
     */
    public Card getFaceUpCard() 
    {
        return hand.get(1); 
    }

    /**
     * @return a String with information about all the cards in the hand and their total value
     */
    public String toString ()
    {
        String str = "";
        for (Card c: hand)
        {
            str += c.toString();
            str += "\n";
        }
        str += "\nTotal value: " + value;
        return str;
    }

    /**
     * @return the ArrayList of cards which is the instance variable of Hand
     */
    public ArrayList<Card> getCardList()
    {
        return hand;
    }
}
