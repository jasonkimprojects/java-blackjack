
/**
 * ShoeTester.java  
 *
 * @author: Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: Tests every method of class Shoe by a runner
 * 
 *
 */
public class ShoeTester
{
    /**
     * @param String[] args just the standard parameter for main
     * @return none
     * 
     * Instantiates a shoe object and tests its methods below:
     * String toString()
     * Card drawCard()
     * ArrayList<Card> getShoe()
     */
    public static void main(String[] args)
    {
        Shoe s1 = new Shoe(4);
        System.out.println(s1); //tests toString
        System.out.println("\ndrawCard() Randomness Test:");
        for(int i=0; i<10; i++)
        {
            System.out.println(s1.drawCard()); //tests drawCard
        }
        
        System.out.println("\nshoe ArrayList getter method test: ");
        System.out.println(s1.getShoe().get(5)); //tests getShoe
        //after 10 cards have been removed from the top of the deck

    }
}
