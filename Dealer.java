import java.util.Scanner;
/**
 * Dealer.java  
 *
 * @author: Zachary, Anand, Jason
 * Assignment #: Blackjack Project
 */
public class Dealer
{
    private static Shoe shoe;
    private Hand dealerHand;
    private double insuranceBet;
    private Scanner scan;
    private boolean blackjack, dealerBlackjack, insuranceBought;

    private final int SHOE_SIZE = 4; //change this value to set how many decks go in the shoe
    private final int DEALER_HIT_MAX = 16; //change this to mess with dealer behavior
    //this constant is the maximum score that the dealer will hit on

    /**
     * Constructor that instantiates a dealer.
     * @param NONE
     * Instantiates a new shoe, the dealer's hand, and a scanner for taking user input.
     */
    public Dealer()
    {
        shoe = new Shoe(SHOE_SIZE);
        dealerHand = new Hand();
        scan = new Scanner(System.in);
    }

    /**
     * Dealer draws a card from the shoe.
     * @param NONE
     * @return a Card object located at the top of the shoe (first item in its ArrayList)
     */
    public static Card dealCard()
    {
        return shoe.drawCard();
    }

    /**
     * Determines if the dealer has bust.
     * @param NONE
     * @return a boolean value that is true if and only if the value of the dealer's hand exceeds 21;
     * otherwise returns false
     */
    public boolean dealerBusted()
    {
        return dealerHand.getValue() > 21;
    }

    /**
     * Helper method that is invoked if the dealer's upcard is an ace
     * and asks the player if the player will bet insurance
     * @param the Player object instantiated in the start method
     * @return NONE
     */
    public void insuranceBet(Player p1)
    {
        int buyInsurance = -1;
        boolean valid = false;

        do
        {
            System.out.println("-----------------------------------------------");
            System.out.println("Dealer has an ace! Buy insurance?");
            System.out.println("Type 0 for yes, any other integer for no.");
            String input = scan.nextLine();
            try
            {
                buyInsurance = Integer.parseInt(input);
                valid = true;
            }
            catch(Exception e)
            {
                System.out.println("Invalid input!");
                continue;
            }
        } while(!valid);

        if(buyInsurance == 0)
        {
            insuranceBought = true;
            boolean amountValid = false;

            do //another sentry loop
            {
                System.out.println("-----------------------------------------------");
                System.out.println("Enter your insurance bet: ");
                System.out.println("The maximum is half your original bet.");
                String bet = scan.nextLine();
                try
                {
                    insuranceBet = Double.parseDouble(bet);
                    if(insuranceBet <= 0 || insuranceBet > (p1.getBet() / 2))
                        throw new Exception();
                    amountValid = true;
                }
                catch(Exception e)
                {
                    System.out.println("Invalid input!");
                    continue;
                }
            }while(!amountValid);

            System.out.println("-----------------------------------------------");
            System.out.println("The dealer will peek at the face down card.");
            if(dealerHand.getValue() == 21)
            {
                System.out.println("The dealer has made a blackjack!");
                System.out.println("The dealer's hand was: ");
                System.out.println(dealerHand);
                dealerBlackjack = true;
            }
            else
            {
                System.out.println("The dealer's hand is not a blackjack.");
                System.out.println("Accordingly, it will not be revealed.");
            }

            determineInsurance(p1); //revised: the dealer will peek at his/her hand
            //and the insurance bet results will be resolved right away
        }
    }

    /**
     * Helper method that is invoked if the dealer has to play cards
     * Not invoked if the player doesn't bust.
     * If the player has blackjack, the dealer just checks for the first two cards and does not
     * draw any more cards.
     */
    public void dealerAI(Player p1)
    {
        System.out.println("-----------------------------------------------");
        if((blackjack || p1.getHand().getHandSize() == 5) && dealerHand.getValue() != 21)
        {
            System.out.println("Dealer does not have a blackjack.");
        }
        else
        {
            System.out.println("Dealer is playing...");
            System.out.println("Dealer's cards are: ");
            System.out.println(dealerHand); //dealer's cards finally revealed
        }

        if(dealerHand.getValue() == 21) //checks if the dealer had a blackjack
        {
            System.out.println("-----------------------------------------------");
            System.out.println("The dealer has made a blackjack!");
            dealerBlackjack = true;
        }

        while(dealerHand.getValue() <= 21 && !dealerBlackjack && !blackjack && p1.getHand().getHandSize() != 5) //if dealer has a blackjack, this isn't needed
        {
            if(dealerHand.getValue() <= DEALER_HIT_MAX) //dealer hits
            {
                dealerHand.addCard(dealCard());
                System.out.println("-----------------------------------------------");
                System.out.println("Dealer hits.");
                System.out.println("Dealer's cards are: ");
                System.out.println(dealerHand);
            }
            else
            {
                System.out.println("-----------------------------------------------");
                System.out.println("Dealer stands.");
                break; //dealer stands and quits drawing cards
                //because the dealer isn't betting, there is no implementation for double down
            }
        }

        if(dealerHand.getValue() <= 21 && !blackjack && p1.getHand().getHandSize() != 5) //if the dealer finishes without busting
        {
            System.out.println("Dealer's final score is: "+dealerHand.getValue());
        }
        else if(dealerHand.getValue() > 21)
        {
            System.out.println("-----------------------------------------------");
            System.out.println("Dealer BUST!");
        }
    }

    /**
     * Helper method that is invoked by the insuranceBet method if the player bets insurance.
     * Responsible for printing the result on the screen
     * And changing the player's bankroll accordingly. 
     */
    public void determineResult(Player p1)
    {
        System.out.println("-----------------------------------------------");
        if((blackjack && dealerBlackjack) || p1.getHand().getValue() == dealerHand.getValue() && !blackjack && !dealerBlackjack && p1.getHand().getHandSize()!=5)
        {
            System.out.println("Result: PUSH"); //if the player and dealer have a tie
        }
        else if(p1.getHand().getHandSize() == 5 && !dealerBlackjack)
        {
            System.out.println("Result: Player wins by 5-card Charlie!");
            p1.addToBankroll(p1.getBet());
        }
        else if((p1.getHand().getValue() > dealerHand.getValue() || dealerBusted()) && p1.getDoubleDown())
        {
            System.out.println("Result: Player wins and receives the doubled bet!");
            p1.addToBankroll(p1.getBet());
        }
        else if((blackjack && !dealerBlackjack) || p1.getHand().getValue() > dealerHand.getValue() || dealerBusted())
        {
            System.out.println("Result: Player wins!"); 
            if(blackjack)
            {
                p1.addToBankroll(p1.getBet() * 1.5);
            }
            else
            {
                p1.addToBankroll(p1.getBet());
            }
            //if only the player has a blackjack, or if the player scores higher than the dealer, or if the dealer busts
        }
        else
        {
            //all other cases: if only the dealer has a blackjack, or if the dealer scores higher than the player
            System.out.println("Result: Dealer wins!");
            p1.addToBankroll(-1 * p1.getBet());
        }
        System.out.println(p1);
    }

    /**
     * Helper method that is invoked if the player had bet on insurance
     * Prints the result on the screen and changes the player's bankroll accordingly
     */
    public void determineInsurance(Player p1)
    {
        System.out.println("-----------------------------------------------");
        System.out.println("Insurance bet results:");

        if(!p1.getDealerPlay()) //if the dealer didn't play and therefore
        //the dealer's cards weren't shown
        {
            System.out.println("Dealer's cards are: ");
            System.out.println(dealerHand);
        }

        if(dealerBlackjack)
        {
            System.out.println("You have won the bet!");
            p1.addToBankroll(2 * insuranceBet);
        }
        else
        {
            System.out.println("You have lost the bet!");
            p1.addToBankroll(-1 * insuranceBet);
        }
        System.out.println(p1);
    }

    /**
     * The main method called by the Game class to start the match. 
     * Makes use of several helper methods implemented above.
     */
    public void start() //THE BIG ALMIGHTY METHOD
    {
        System.out.println('\u000C');

        System.out.println("-----------------------------------------------");
        System.out.println("B L A C K J A C K");
        System.out.println("Group 7 - Zachary, Anand, Jason");

        Player p1 = new Player(); //initializes player and the bankroll
        boolean keepPlaying = true;

        while(keepPlaying) //Makes it possible to play several rounds with one bankroll
        {
            p1.reset();
            p1.getHand().getCardList().clear();
            dealerHand.getCardList().clear(); //Clears everyone's hands

            p1.betMoney(); //prompts the player to bet money

            System.out.println("-----------------------------------------------");
            System.out.println("Your cards are:");
            p1.getHand().addCard(dealCard()); //first two cards are dealt
            p1.getHand().addCard(dealCard());
            System.out.println(p1.getHand());

            dealerHand.addCard(dealCard()); //dealer's cards are dealt
            dealerHand.addCard(dealCard());

            System.out.println("-----------------------------------------------");
            System.out.println("The dealer's card facing up is:");
            System.out.println(dealerHand.getFaceUpCard()); //reveals the dealer's second card

            blackjack = false;
            dealerBlackjack = false;
            insuranceBought = false;
            //just some boolean variables to indicate if the game should go on or stop
            //or if the player or dealer has made a blackjack

            insuranceBet = 0;
            if(dealerHand.getFaceUpCard().getRank().equals("ace") && p1.getBet() != p1.getBankroll()) //dealer insurance
            {
                insuranceBet(p1);
            }
            else if(dealerHand.getFaceUpCard().getRank().equals("ace") && p1.getBet() == p1.getBankroll())
            {
                System.out.println("-----------------------------------------------");
                System.out.println("Since your bet is your entire bankroll,\nYou cannot bet on insurance.");
            }

            if(p1.getHand().isBlackjack()) //if the player gets a blackjack
            {
                System.out.println("-----------------------------------------------");
                System.out.println("You have made a blackjack!");
                p1.setPlay(false);
                blackjack = true;
            }

            while(p1.getPlay() && !p1.isBusted() && p1.getHand().getHandSize() != 5 && !p1.getDoubleDown() && !dealerBlackjack)
            {
                p1.playCards();
                if(p1.getHand().getValue() == 21) //automatically stops if the player's hand reaches 21
                {
                    System.out.println("-----------------------------------------------");
                    System.out.println("Your hand has reached 21.");
                    System.out.println("The gameplay for the round will stop.");
                    break;
                }
            }

            if(!p1.isBusted() && p1.getHand().getHandSize() == 5) //5-card charlie
            {
                System.out.println("-----------------------------------------------");
                System.out.println("You have a 5-card charlie!");
            }

            if(p1.getDealerPlay() && !dealerBlackjack) //starts dealer AI
            {
                dealerAI(p1);
            }

            if(!p1.isBusted()) //if the player busted, the game ended way earlier either on line 131 or line 158
            {
                determineResult(p1);
            }

            if(p1.getBankroll() <= 0) //if the player goes broke, ends the game forcefully 
            {
                keepPlaying = false;
                System.out.println("-----------------------------------------------");
                System.out.println("GAME OVER: Your bankroll is empty.");
            }
            else
            {
                int again = -1;
                boolean valid = false;

                do //Just another sentry loop to prevent stupid values
                {
                    System.out.println("-----------------------------------------------");
                    System.out.println("Play another round? Enter 1 for yes, 0 for no.");
                    String input = scan.nextLine();
                    try
                    {
                        again = Integer.parseInt(input);
                        if(again != 0 && again != 1)
                            throw new Exception();
                        valid = true;
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid input!");
                        continue;
                    }
                }while(!valid);

                if(again == 0) //ends the game
                {
                    keepPlaying = false;
                    System.out.println("GAME OVER: Thanks for playing!");
                }
            }

        }
    }
}

