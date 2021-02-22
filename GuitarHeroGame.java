//Written by Matthew Myles & Hamed Faruqui

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GuitarHeroGame
{
    private Player player1;                                         //Private Player instance for player 1
    private Player player2;                                         //Private player instance for player 2
    private Random rand;                                            //Private variable for the random
    private boolean comGame;                                        //Private variable for whether it's a game against CP or not
    private int rounds;                                             //Private int to keep track of the rounds
    private int nextRounds;                                         //Variable to keep track of rounds+2 for sabotage cards
    private Scanner scn;                                            //Private for scanner
    private File highScoreFile;                                     //Private variable for high score file

    public GuitarHeroGame(Scanner scanner)                       //Game constructor. sets all the variables and builds player decks and hands
    {
        player1= new Player();
        player2= new Player();
        scn= scanner;
        rand= new Random();
        rounds=0;
        nextRounds=0;

        player1.buildDeck();
        player1.buildHand();
        player2.buildDeck();
        player2.buildHand();

        highScoreFile= new File("highScores.txt");
    }

    public void gamePrompt() throws InputMismatchException              //Takes input to set up the game by prompting, throws mismatch exception upon incorrect input
    {
        try                                                             //Try-catch for the input
        {
            int input=0;
            System.out.println("Welcome to the Guitar Hero Card Game!");
            System.out.println("Will you be playing alone or with someone? (Enter number) \n1. Alone  \n2. With someone");
            input = scn.nextInt();
            if (input == 1)                                 //If playing alone, computer is set to true
            {
                comGame = true;
            }
            else                                           //Else, it's set to false
            {
                comGame = false;
            }
            System.out.println("How many rounds will you be playing? (Enter the number)");
            rounds = scn.nextInt();
            nextRounds=rounds+2;
            System.out.println();
        }
        catch (InputMismatchException e)
        {
            throw new InputMismatchException("Invalid input!");
        }

    }

    public void playGame() throws FileNotFoundException        //Method to play the game, by going through the player turns and high score check
    {
        gamePrompt();
        int round=0;
        if(comGame)                                                     //If computer game
        {
            while(round < rounds)                                       //Configurable while loop based on rounds
            {
                setTurnOrder();                                         //Method that gets the turn order and then actually steps through the player turns
                round++;                                                //Increments round
            }
            if(player1.getScore() > player2.getScore())                 //If-else to check who won, also based on whether computer did better
            {
                System.out.println("You beat the computer!");
                printHighScore(player1);                                //Updates high score if necessary
            }
            else
            {
                System.out.println("You lost to the computer!");
                printHighScore(player2);                               //Updates high score
            }
        }
        else                                                          //Else for when 2 person game
        {
            while(round < rounds)
            {
                setTurnOrder();
                round++;
            }
            if(player1.getScore() > player2.getScore())
            {
                System.out.println("Player 1 wins!");
                printHighScore(player1);                            //Updates high score and prints result if necessary
            }
            else
            {
                System.out.println("Player 2 wins!");
                printHighScore(player2);                                //Updates high score
            }
        }
    }

    public void setTurnOrder()                                         //Method to set the turn order based on score
    {
        if(player1.getScore() > player2.getScore())            //If-else ladder to set the turn order based on score comparison
        {
            Turns(1);
        }
        else if(player2.getScore() > player1.getScore())
        {
            Turns(2);
        }
        else
        {
            Turns(rand.nextInt(2)+1);
        }
    }

    public void Turns(int turns)             //Method to step through turns and check decks for reshuffling
    {
        player1.checkDeck();                                        //Calls checkDeck method to see if shuffle is necessary and do it
        player2.checkDeck();
        if(comGame)                                                 //If computer game
        {
            if (turns == 1)                                         //Player 1 will go first and then the computer
            {
                System.out.println("Player 1: ");
                playerTurn(player1, player2);
                player1.checkAllMatched(1);                     //Checks if player matched all notes
                System.out.println();
                System.out.println("Computer: ");
                player2.randTurn(player1);                           //Calls method for random turn
                player2.checkAllMatched(2);                     //Checks if computer matched all notes
                player2.printComStats();
                System.out.println();
                if(rounds==nextRounds)
                {
                    updateTempRounds();                                 //Updates nextRounds to be accurate for the cards
                }
            }
            else                                                //Else if computer game, will do the same but no random turns
            {
                System.out.println("Computer: ");
                player2.randTurn(player1);
                player2.checkAllMatched(2);
                player2.printComStats();
                System.out.println();
                playerTurn(player1, player2);
                player1.checkAllMatched(1);
                System.out.println();
                if(rounds==nextRounds)
                {
                    updateTempRounds();                                 //Updates nextRounds to be accurate for the cards
                }
            }
        }
        else                                                    //Else for when it's a two person game
        {
            if(turns == 1)
            {
                System.out.println("Player 1: ");
                playerTurn(player1, player2);
                player1.checkAllMatched(1);
                System.out.println();
                System.out.println("Player 2: ");
                playerTurn(player2, player1);
                player2.checkAllMatched(2);
                System.out.println();
                if(rounds==nextRounds)
                {
                    updateTempRounds();                                 //Updates nextRounds to be accurate for the cards
                }
            }
            else
            {
                System.out.println("Player 2: ");
                playerTurn(player2, player1);
                player2.checkAllMatched(2);
                System.out.println();
                System.out.println("Player 1: ");
                playerTurn(player1, player2);
                player1.checkAllMatched(1);
                System.out.println();
                if(rounds==nextRounds)
                {
                    updateTempRounds();                                 //Updates nextRounds to be accurate for the cards
                }
            }
        }
        player1.drawCards(2);                                       //Players draw 2 new cards for the next turn
        player2.drawCards(2);
    }

    public void playerTurn(Player player, Player otherPlayer)                   //Method for human player turn
    {
        try                                                             //Try-catch based on card being passed in
        {
            player.createBoard();                                       //Creates the player's note board to match
            int input=0;
            player.printStats();                                        //Prints player's stats
            while(input > -1 && player.getHand().size() > 0)
            {
                if(player.checkTurn())                       //If player has resources to play action cards
                {
                    System.out.println("\nYou have the resources to play all your cards!");
                    player.printStats();
                    System.out.println("Which card do you want to play? (Enter -1 to end your turn)");
                    input= scn.nextInt();
                    System.out.println();
                    if(input == -1)
                    {
                        break;
                    }
                    else
                    {
                        Object playedCard = player.getHand().get(input-1);
                        if(checkSabotage(playedCard, player))                       //Calls method to check if player has condition for cards they can / can't play
                        {
                            System.out.println("Can't play that card due to opponent's tune up!");
                        }
                        else                                                    //Else, play the card
                        {
                            player.useCard(playedCard, otherPlayer);
                        }
                    }
                }
                else if(!player.checkTurn())      //Else if player can't afford any action cards
                {
                    if(player.getPlayerNotesBoard().isEmpty())                  //If their note board is already empty from previous moves
                    {
                        System.out.println("Matched all notes!");
                        break;
                    }
                    player.buildNoteCards();                                    //Builds a list based on the note cards they can play for free
                    System.out.println("\nYou can't afford any non-color cards!");
                    player.printBoard();                                        //Reprints colors to match
                    player.printResources();                                    //Prints energy, combo count, etc
                    if(player.getNoteCards().isEmpty())                         //If there aren't any colors to play, ergo no way to get energy, end turn
                    {
                        System.out.println("No color cards to play! Turn ends!");
                        input=-1;
                    }
                    else                                                        //Else take input
                    {
                        System.out.println("Pick from the cards below to play (Or enter -1 to end your turn)");
                        player.printNoteCards();                                //Prints the note cards they can play
                        input=scn.nextInt();
                        if(input == -1)
                        {
                            break;
                        }
                        CoreCard tempCore = (CoreCard) player.getHand().get(input-1);
                        if(checkSabotage(tempCore,player))                      //Checks for condition on color cards they can play
                        {
                            System.out.println("Can't play that color due to opponent's break strings!");
                        }
                        else
                        {
                            player.useCard(tempCore, player);                  //Player plays the card
                        }
                    }
                }
            }
            System.out.println("Turn over!");
        }
        catch (NoCardException e)                                            //Catches a NoCardException to have player retry the turn
        {
            System.out.println("No card chosen! Retry turn");
            playerTurn(player, otherPlayer);
        }
    }

    public boolean checkSabotage(Object card, Player player)
    {
        if(rounds < nextRounds)                                         //If rounds are less than rounds+2, sabotage cards still work
        {
            if(player.getCheck() == 6)                                  //If their number is 6, they can't play specific action cards
            {
                if(card instanceof LeftyFlip || card instanceof BreakStrings || card instanceof ShakeScreen)
                {
                    return true;
                }
            }
            else if(player.getCheck() == 5 && card instanceof Orange)               //Else if they can't play orange and try to
            {
                return true;
            }
            else if(player.getCheck() == 4 && card instanceof Green)              //Else if they can't play
            {
                return true;
            }
            else if(player.getCheck() == 3 && card instanceof Blue)                 //If player tries to play a Blue Card when they can't
            {
                return true;
            }
            else if(player.getCheck() == 2 && card instanceof Red)                  //If check is 2, for Red, and player tries to play Red
            {
                return true;
            }
            else if(player.getCheck() == 1 && card instanceof Yellow)              //If check is 1 for yellow and player plays yellow
            {
                return true;
            }
        }
        else                                                                    //Else reset player's special check number and return false
        {
            player.setCheck(0);
            return false;
        }
        return false;
    }

    public void updateTempRounds()                                          //Method to update what round+2 is
    {
        nextRounds= rounds+2;
    }


    public boolean checkHighScore(int score) throws FileNotFoundException       //Method to check high score and add new if needed
    {
        try                                                                 //Try-catch for FileNotFoundException
        {
            int currentHighScore;
            boolean checkScores= true;                                      //Boolean to continue checking scores
            PrintWriter pw= new PrintWriter(highScoreFile);                 //Print writer based for the file
            Scanner in= new Scanner(highScoreFile);                        //Scanner to scan in data from the file
            if(!in.hasNext())                                             //If it's first game, where there aren't any high scores, first to winning score is new high score
            {
                pw.println(score);
                return true;
            }
            else                                                        //Else when there is data
            {
                while(in.hasNext() && checkScores)                      //While there are scores to scan
                {
                    currentHighScore= Integer.parseInt(in.next());     //Current high score is read in from the file
                    if(currentHighScore < score)                        //If the new score is greater than the old, its written as new high score
                    {
                        pw.println(score);
                        checkScores= false;
                    }
                }
            }
            if(!checkScores)                                        //If stopped checking and new score was found, return true
            {
                pw.close();
                in.close();
                return true;
            }
            else
            {
                pw.close();
                in.close();
                return false;
            }
        }
        catch (FileNotFoundException e)                                 //Catch FileNotFoundException and throws it
        {
            throw new FileNotFoundException();
        }
    }

    public void printHighScore(Player player) throws FileNotFoundException                  //Prints highscore based on above method
    {
        if(checkHighScore(player.getScore()) && comGame)
        {
            System.out.println("Computer got a new high score of " + player.getScore() + ". Radical!");
        }
        else if(checkHighScore(player.getScore()))
        {
            System.out.println("Player got a new high score of " + player.getScore() + ". Radical!");
        }
        else
        {
            System.out.println("No new high scores!");
        }
    }


    public int sampleTurnOrder(Player player1, Player player2)          //Made for unit testing the method
    {
        if(player1.getScore() > player2.getScore())
        {
            return 1;
        }
        else if(player2.getScore() > player1.getScore())
        {
            return 2;
        }
        else
        {
            return rand.nextInt(2)+1;
        }
    }

}
