
/**
 * PlayerTester.java 
 *
 * @author Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 * 
 *
 */

public class PlayerTester 
{
    /**
     * @param String[] args the default parameter for main()
     * 
     * Tests every method in class Player
     */
    public static void main(String[] args)
    {
        Player p1 = new Player(); //constructor and initializeBankroll()
        Dealer d1 = new Dealer();
        System.out.println(p1.getBankroll()+"\n"); //tests bankroll getter method

        System.out.println(p1.getDealerPlay()+"\n"+p1.getPlay()+"\n"+p1.getDoubleDown()+
            "\n");
        //tests boolean getter methods

        p1.setPlay(false); //tests the setPlay method
        System.out.println("boolean play set to false");
        System.out.println(p1.getPlay()+"\n");

        p1.reset(); //tests the reset method
        System.out.println("play and dealer play variables reset");
        System.out.println(p1.getDealerPlay()+"\n"+p1.getPlay()+"\n");

        System.out.println(p1.getHand()+"\n"); //tests the getHand method

        p1.betMoney(); //tests the betMoney method
        System.out.println("\nPlayer bet: "+p1.getBet()+"\n"); //tests bet getter method

        System.out.println(p1); //tests toString
        p1.addToBankroll(1000); //tests addToBankroll
        System.out.println(p1); 

        while(p1.getPlay() && !p1.isBusted() && p1.getHand().getHandSize() != 5 && !p1.getDoubleDown()) 
        {
            p1.playCards();
        }
        //tests playCards -- the method for player gameplay 
        //identical loop in the dealer method for gameplay
        //the first two cards are not already added to player's hand

        /*#
         * Gets called once in this tester program
         */

        System.out.println("\nPlayer busted: "+p1.isBusted());
        //tests the bust method
        
        System.out.println("End of tester program");
    }
}
