import java.util.Scanner;
/**
 * Player.java  
 *
 * @author: Zachary, Anand, Jason (Group 7)
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description:
 * 
 *
 */
public class Player
{
    private Hand playerHand;
    private double bankroll = 0;
    private double bet = 0;
    private Scanner scan;
    private boolean dealerPlay = true; //if the dealer will play, or not
    //if the player wins by 5 card charlie or busts, this variable is set to false
    private boolean play = true; //if the play cards loop will keep running or not
    //if the player wins by 5 card charlie, busts, stands, or double downs
    //then this variable is set to false
    private boolean doubleDown = false; //if the player selects double down, this variable
    //is set to true to indicate that

    public Player()
    {
        playerHand = new Hand();
        scan = new Scanner(System.in);
        initializeBankroll();
    }

    /**
     * @return - boolean dealerPlay
     */
    public boolean getDealerPlay()
    {
        return dealerPlay;
    }

    /**
     * @return - boolean play
     */
    public boolean getPlay()
    {
        return play;
    }

    /**
     * @param - boolean to be the new value of play
     */
    public void setPlay(boolean x)
    {
        play = x;
    }

    /**
     * Resets booleans to default value after each round
     */
    public void reset() 
    {
        play = true;
        dealerPlay = true;
        doubleDown = false;
    }

    /**
     * @return - boolean doubleDown
     */
    public boolean getDoubleDown()
    {
        return doubleDown;
    }

    /**
     * @return - double bankroll
     */
    public double getBankroll()
    {
        return bankroll;
    }

    /** 
     * @return - Hand playerHand
     */
    public Hand getHand()
    {
        return playerHand;
    }

    /**
     * @return - double bet
     */
    public double getBet()
    {
        return bet;
    }

    /**
     * Gives the player money or to take it away
     * @param - int num to be added to bankroll
     */
    public void addToBankroll(double num) 
    {
        bankroll += num;
    }

    /**
     * Appears once at the start of the game, sets the value of bankroll
     */
    public void initializeBankroll() 
    {
        double playerMoney;
        boolean valid = false;
        do //Just a sentry loop in case the player puts in a stupid value
        {
            System.out.println("-----------------------------------------------");
            System.out.println("How much is your bankroll in dollars?"); //Player inputs the bankroll at the start of the game
            String input = scan.nextLine();
            try
            {
                playerMoney = Double.parseDouble(input);
                if(playerMoney <= 0)
                    throw new Exception();
                bankroll = playerMoney;
                valid = true;
            }
            catch(Exception e)
            {
                System.out.println("Invalid input!");
                continue;
            }
        } while(!valid);
    }

    /**
     * called at the start of each round, determines the amount of money the player will bet
     */
    public void betMoney() 
    {
        double playerBet;
        boolean valid = false;
        do
        {
            System.out.println("-----------------------------------------------");
            System.out.println("Starting round...");
            System.out.println("Enter your bet:"); //Player enters the bet
            String input = scan.nextLine();
            try
            {
                playerBet = Double.parseDouble(input);
                if(playerBet <=0 || playerBet > bankroll)
                    throw new Exception();
                bet = playerBet;
                valid = true;
            }
            catch(Exception e)
            {
                System.out.println("Invalid input!");
                continue;
            }
        }while(!valid);
    }

    /**
     * main method for player gameplay 
     */
    public void playCards() //
    {
        dealerPlay = true;
        int playerChoice = 0;
        boolean valid = false;
        do
        {
            System.out.println("-----------------------------------------------");
            System.out.println("Select your action by entering a number:");
            if(playerHand.getHandSize() == 2)
                System.out.println("1 to hit, 2 to stand, 3 to double down.");
            else
                System.out.println("1 to hit, 2 to stand.");
            String input = scan.nextLine();
            try
            {
                playerChoice = Integer.parseInt(input);
                if(playerChoice != 1 && playerChoice != 2 && playerChoice != 3)
                    throw new Exception();
                if(playerHand.getHandSize() != 2 && playerChoice == 3)
                    throw new Exception();
                valid = true;
            }
            catch(Exception e)
            {
                System.out.println("Invalid input!");
                continue;
            }
        } while(!valid); //Just a sentry loop to prevent stupid input value

        if(playerChoice == 1) //if the player hits
        {
            hit(); //just a helper method for adding a card to the hand and recalculating the sum
            System.out.println("-----------------------------------------------");
            System.out.println("You have selected: Hit");
            System.out.println("Your cards are: ");
            System.out.println(getHand());
            if(isBusted()) //if the player busts
            {
                System.out.println("-----------------------------------------------");
                System.out.println("BUST! Dealer wins.");
                addToBankroll(-1 * bet); //player loses the bet
                System.out.println(this);
                dealerPlay = false; //dealer doesn't play because the player already lost
            }
        }

        if(playerChoice == 2)//if the player stands
        {
            System.out.println("-----------------------------------------------");
            System.out.println("You have selected: Stand");
            System.out.println("Your final score is: "+getHand().getValue());
            play = false; //player's turn stops
        }

        if(playerChoice == 3 && bet * 2 <= bankroll && playerHand.getHandSize() == 2) //if the player double-downs
        {
            bet *= 2; //player's bet is doubled
            hit(); //the rest of the implementation is identical to when the player hits
            System.out.println("-----------------------------------------------");
            System.out.println("You have selected: Double Down");
            System.out.println("Your bet has been doubled to $"+bet+".");
            System.out.println("Your cards are: ");
            System.out.println(getHand());
            if(isBusted())
            {
                System.out.println("-----------------------------------------------");
                System.out.println("BUST! Dealer wins.");
                addToBankroll(-1 * bet); //player loses the bet
                System.out.println(this);
                dealerPlay = false; //dealer doesn't play because the player already lost
            }
            play = false; //You can double down ONLY ONCE
            doubleDown = true;
        }
        else if(playerChoice == 3 && bet * 2 > bankroll)
        {
            System.out.println("-----------------------------------------------");
            System.out.println("Double down unavailable because\nyour bet exceeds half of your bankroll.");
        }
        else if(playerChoice == 3 && playerHand.getHandSize() != 2)
        {
            System.out.println("-----------------------------------------------");
            System.out.println("Invalid input!");
            System.out.println("Double down is unavailable after taking a hit.");
        }
    }

    /**
     * add card to the player's hand when they hit
     */
    public void hit()
    {
        playerHand.addCard(Dealer.dealCard());
    }

    /**
     * @return - boolean, true if player has busted, false otherwise.
     */
    public boolean isBusted()
    {
        return playerHand.getValue() > 21;
    }

    public String toString()
    {
        return "You currently have $"+String.format("%.2f",bankroll)+".";
    }
}
