
/**
 * DealerTester.java 
 *
 * @author Zachary, Anand, Jason
 * Assignment #: Blackjack Project
 * 
 * 
 *
 */

public class DealerTester 
{
    /**
     * @param String[] args the standard parameter for main
     * 
     * Brief Program Description:
     * main program that tests every method in class Dealer
     */
   public static void main(String[] args)
   {
       Dealer dealer = new Dealer();
       
       System.out.println("Card dealt: \n"+dealer.dealCard()+"\n"); 
       //tests the dealCard method
       
       dealer.start();
       /*#
        * tests the start method.
        * The start method tests dealerBusted, insuranceBet, dealerAI, 
        * determineResult, and determineInsurance, and dealCard
        * Thus the method calls all other helper methods in the class
        */
       
       
   }
}
