
/**
 * Card.java  
 *
 * @author: Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: 
 * 
 *
 */
public class Card
{
    private String suit, rank;
    private int numValue;

    /**
     * Constructor for class Card
     * @param a String that indicates the suit followed by another String that indicates rank
     */
    public Card(String s, String r)
    {
        suit = s;
        rank = r;
        if(r.equals("ace")) //if-else statements to determine numeric value from rank
        {
            numValue = 11;
        }
        else if(r.equals("jack") || r.equals("queen") || r.equals("king"))
        {
            numValue = 10;
        }
        else
        {
            numValue = Integer.parseInt(r); //converts string to int
        }

    }

    /**
     * @param NONE
     * @return a String that describes the rank and suit of the card
     */
    public String toString()
    {
        return rank+" of "+suit;
    }

    /**
     * @param NONE
     * @return an integer that represents the numeric value of the card
     */
    public int getNumValue()
    {
        return numValue;
    }

    /**
     * @param NONE
     * @return a String that represents the suit of the card
     */
    public String getSuit() //just for diagnostic purposes 
    {
        return suit;
    }

    /**
     * @param NONE
     * @return a String that represents the rank of the card
     */
    public String getRank()
    {
        return rank;
    }
    
    /**
     * This method is invoked by the hand class 
     * If an ace is added to the hand, but the hand's total value
     * Exceeds 21. The method changes the ace's numeric value from 11 to 1.
     */
    public void setAceValue() //called by the hand class
    {
        if(numValue == 11) //same condition in hand -- just for double checking
        {
            numValue = 1;
        }
    }

}
