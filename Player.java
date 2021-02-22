import java.util.Random;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Player
{
    //We used ArrayLists<Objects> as these card holders will have two types of card classes. InstanceOf and String.equals can be used to check which
    private ArrayList<Object> deck;                                         //ArrayList for the deck
    private ArrayList<Object> discardPile;                                  //ArrayList for the discard pile
    private ArrayList<Object> hand;                                          //ArrayList for the hand
    private ArrayList<CoreCard> noteCards;                                   //ArrayList for the note cards a player can play
    private ArrayList<CoreCard> playerNotesBoard;                            //ArrayList for the notes a player must match each turn
    private Random rand;                                                     //Random int
    private int energy;                                                     //Player energy
    private int comboCount;                                                 //Integer for the combo count
    private int score;                                                      //Integer for their score
    private int check;                                                      //Integer for check, special number relating to card sabotages
    private Scanner scn;

    public Player()                                                         //Initializes global variables
    {
       scn= new Scanner(System.in);
       deck = new ArrayList<>();
       discardPile = new ArrayList<>();
       hand = new ArrayList<>();
       noteCards= new ArrayList<>();
       playerNotesBoard= new ArrayList<>();
       rand = new Random();
       check=0;
    }

    public int getCheck()
    {
        return check;
    }                                //Getter for the check

    public void setCheck(int n)
    {
        check= n;
    }                              //Setter for the chekc

    public ArrayList<CoreCard> getPlayerNotesBoard()                      //Getter for the note board array
    {
        return playerNotesBoard;
    }

    public void createBoard()                                           //Method to create the board of notes to match
    {
        playerNotesBoard.clear();                                   //Clears previous board
        int numCards= rand.nextInt(3)+1;                    //Random for number of cards to be displayed on board
        for(int i = 0; i < numCards; i++)
        {
            int randCard= rand.nextInt(5);                  //Random int to decide which colors
            if(randCard==0)
            {
                playerNotesBoard.add(new Blue());
            }
            else if(randCard==1)
            {
                playerNotesBoard.add(new Red());
            }
            else if(randCard==2)
            {
                playerNotesBoard.add(new Green());
            }
            else if(randCard==3)
            {
                playerNotesBoard.add(new Orange());
            }
            else
            {
                playerNotesBoard.add(new Yellow());
            }
        }

    }

    public void printBoard()                                                    //Method to print colors to match
    {
        System.out.println("Notes to match: " + playerNotesBoard);
    }

    public int getEnergy()                                                      //Getter for the energy
    {
        return energy;
    }

    public void setEnergy(int amount)                                           //Setter for the energy
    {
        energy= amount;
    }

    public int getComboCount()                                                  //Getter for the combo count
    {
        return comboCount;
    }

    public void setComboCount(int amount)                                     //Setter for the combo count
    {
        comboCount= amount;
    }

    public int getScore()                                                   //Getter for the score
    {
        return score;
    }

    public void setScore(int amount)                                        //Setter for the score
    {
        score= amount;
    }


    public ArrayList<Object> getDeck()                                    //Method to get the deck
    {
        return deck;
    }

    public ArrayList<Object> getHand()                                  //Getter for the hand
    {
        return hand;
    }

    public ArrayList<CoreCard> getNoteCards()
    {
        return noteCards;
    }

    public void printStats()                                                //Method to print all stats
    {
        printBoard();
        System.out.println("Your stats: ");
        System.out.println( "Energy: " + energy + "\t" + "Combo Count: " + comboCount + "\t" + "Score: " + score + "\t");
        System.out.println("Your hand:");
        printHand();
    }

    public void printResources()                                            //Method to just print resources
    {
        System.out.println("Your resources: ");
        System.out.println( "Energy: " + energy + "\t" + "Combo Count: " + comboCount + "\t" + "Score: " + score + "\t");
    }

    public void printComStats()                                             //MEthod to print computer stats
    {
        System.out.println("Computer stats: ");
        System.out.println( "Energy: " + energy + "\t" + "Combo Count: " + comboCount + "\t" + "Score: " + score + "\t");
    }

    public void buildDeck()                                         //Method to build deck
    {
        for(int i =0; i < 60; i++)
        {
            if( i < 8)
            {
                deck.add(new Green());
            }
            else if(i < 16)
            {
                deck.add(new Orange());
            }
            else if(i < 24)
            {
                deck.add(new Red());
            }
            else if(i < 32)
            {
                deck.add(new Blue());
            }
            else if(i < 40)
            {
                deck.add(new Yellow());
            }
            else if(i < 42)
            {
                deck.add(new BreakStrings());
            }
            else if(i < 44)
            {
                deck.add(new LeftyFlip());
            }
            else if(i < 46)
            {
                deck.add(new GuitarSolo());
            }
            else if(i < 48)
            {
                deck.add(new TuneUp());
            }
            else if(i < 50)
            {
                deck.add(new StarPower());
            }
            else if(i < 52)
            {
                deck.add(new ShakeScreen());
            }
            else
            {
                deck.add(new Energy());
            }
        }
        Collections.shuffle(deck);                                      //Randomizes deck by calling shuffle method
    }

    public void printDeck()                                             //Method to print the deck, used in testing
    {
        int index=0;
        for(Object c : deck)
        {
            index++;
            System.out.println(index + ". " + c.toString());
        }
    }

    public void buildHand()                                             //Method to build the hand from the deck
    {
        for(int i=0; i < 7; i++)
        {
            hand.add(deck.get(i));
        }
        for(int i=0; i < 7; i++)
        {
            deck.remove(i);
        }
    }

    public void printHand()                                                 //Method to print the player's hand
    {
        int index=0;
        for(Object c : hand)
        {
            index++;
            System.out.println(index + ". " + c.toString());
        }
    }

    public void drawCards(int num)                                      //Method to draw certain amount of cards
    {
        for (int i = 0; i < num; i++)
        {
            hand.add(deck.get(i));
        }
        for(int i=0; i < num; i++)
        {
            deck.remove(i);
        }
    }

    public void removeCards(ArrayList<Object> array, int numCards)          //Method to remove cards from any of the arrays
    {
        for(int i=0; i < numCards; i++)
        {
            array.remove(numCards);
        }
    }

    public void discardCard(int cardIndex)                                //Method to add cards to the discard pile
    {
        discardPile.add(hand.get(cardIndex));
        hand.remove(cardIndex);
    }

    public void cardMatch()                                             //Method to increment stats when match is found
    {
        comboCount ++;
        score += (comboCount * 100);
        energy += (comboCount * 10);
    }


    public void useCard(Object usedCard, Player otherPlayer) throws NoCardException
    {                                                               //Method to use a card, catching an error if there's not a card passed in
        try
        {
            if(usedCard instanceof ActionCard)                      //If it's an action card
            {
                ActionCard tempAction = (ActionCard) usedCard;
                while(tempAction.getCardCost() > energy)                //While loop to check cost
                {
                    System.out.println("Can't afford this card! Please enter another non-color card: ");
                    tempAction= (ActionCard) hand.get(scn.nextInt()-1);
                }
                tempAction.playCard(this, otherPlayer);                         //Uses card and then discards it in next line
                discardCard(hand.indexOf(usedCard));
                System.out.println("You played " + tempAction.toString());
            }
            else                                                        //Else card is core card, color notes
            {
                CoreCard tempCore = (CoreCard) usedCard;
                checkMatch(tempCore);                                   //Checks match against notesBoard array for colors
                System.out.println("You played " + tempCore.toString() + "!");
                discardCard(hand.indexOf(usedCard));
            }
        }
        catch(NoCardException e)                            //Catches no card excpetion by reprompting and calling the method
        {
            int input=0;
            printHand();
            System.out.println("Which card would you like to play?");
            input= scn.nextInt();
            Object card= hand.get(input-1);
            useCard(card, otherPlayer);
        }
    }

    public boolean checkTurn()                                  //Checks turn cost based on minimum action card cost
    {
        ArrayList<ActionCard> actionCards = new ArrayList<>();          //ArrayList for all action cards
        for (Object card : hand)                                        //Fills the list
        {
            if (card instanceof ActionCard)
            {
                ActionCard tempCard = (ActionCard) card;
                actionCards.add(tempCard);
            }
        }
        if(actionCards.isEmpty())
        {
            return false;
        }
        else
        {
            int minCost = actionCards.get(0).getCardCost();
            for (ActionCard c : actionCards)                    //For loop to determine minimum cost for an action card
            {
                if (c.getCardCost() < minCost)
                {
                    minCost = c.getCardCost();
                }
            }

            if (energy > minCost)                           //If energy is greater than min cost, return true. Else, return false
            {
                return true;
            }
            return false;
        }
    }

    public void buildNoteCards()                                    //Method to build array of playable note cards
    {
        for(Object c : hand)
        {
            if (c instanceof CoreCard)
            {
                noteCards.add((CoreCard) c);
            }
        }
    }

    public void printNoteCards()                                //Prints playable note cards
    {
        for(int i=0; i < hand.size(); i++)
        {
            if(hand.get(i) instanceof CoreCard)
            {
                System.out.println(i+1 + ". " + hand.get(i).toString());
            }
        }
    }

    public void checkDeck()                                     //Checks if deck is empty and reshuffles discard pile in
    {
        if(deck.isEmpty())
        {
            deck.addAll(discardPile);
            Collections.shuffle(deck);
            buildHand();
            discardPile.clear();
        }
    }

    public void checkMatch(CoreCard card)                           //Method to check for match
    {
        boolean match= false;
        int count=0;
        while(!match && count < playerNotesBoard.size())                            //While no match is found and still in note board
        {
            if(card.toString().equals(playerNotesBoard.get(count).toString()))          //If the card matches a note
            {
                if(!playerNotesBoard.get(count).getMatched())                          //Set that card to matched if not already matched
                {
                    playerNotesBoard.get(count).setMatched(true);
                    match= true;                                                      //Set match to true
                }
                else                                                                //Else print they already matched it
                {
                    System.out.println("Already matched the " + playerNotesBoard.get(count).toString() + "!");
                }
            }
            else                                                    //Else, continue through arraylist
            {
                count++;
            }
        }
        if(match)                                           //If match was found display and remove it. Else, return no match and false
        {
            System.out.println("Player matched the " + playerNotesBoard.get(count).toString() + "!");
            playerNotesBoard.remove(count);
            cardMatch();                                                //Increments score and etc accordingly
        }
        else
        {
            System.out.println("Player didn't match anything!");
        }
    }

    public void checkAllMatched(int turn)                                   //Checks if all colors were matched
    {
        int matchCount=0;
        for(CoreCard c : playerNotesBoard)
        {
            if(c.getMatched())                                             //If the card was matched, count goes up
            {
                matchCount++;
                c.resetMatched();
            }
        }
        if(matchCount < playerNotesBoard.size())                        //If all note board notes weren't matched, display based on whose turn it was
        {
            if(turn==1)
            {
                System.out.println("You didn't match all of your notes!");
            }
            else
            {
                System.out.println("Computer didn't match all of its notes!");
            }
            setComboCount(0);                                           //Resets combo count
        }
        else                                                            //Else display they didn't match everything
        {
            if(turn==1)
            {
                System.out.println("You matched all your notes!");
            }
            else
            {
                System.out.println("Computer matched all its notes!");
            }
        }
    }

    public void randTurn(Player otherPlayer)                            //Random turn for computer players
    {
        createBoard();                                                  //Creates the board to match
        ArrayList<ActionCard> comPlayedCards= new ArrayList<>();        //ArrayList to keep track of cards played
        printComStats();
        for(int i=0; i < hand.size(); i++)                              //For loop to try to match every color card with the board
        {
            if(hand.get(i) instanceof CoreCard)
            {
                CoreCard tempColorCard= (CoreCard) hand.get(i);
                checkMatch(tempColorCard);
                discardCard(i);
            }
        }
        int count=0;
        while(checkTurn() && count < hand.size())                          //While loop to play action cards while their energy is enough
        {
            if(hand.get(count) instanceof ActionCard)
            {
                ActionCard tempActionCard= (ActionCard) hand.get(count);
                comPlayedCards.add(tempActionCard);
                tempActionCard.playCard(this, otherPlayer);
                System.out.println("Computer played " + tempActionCard.toString() + "!");
                discardCard(count);
            }
            count++;
        }
        if(comPlayedCards.isEmpty())                                            //If no action cards played, display this
        {
            System.out.println("Computer didn't play any sabotage or inspiration cards!");
        }
    }



    public void directSetScore(int newScore)                            // made for testing
    {
        score = newScore;
    }

    public void createSampleNotesBoard(String s)                //Test for unit testing
    {
        playerNotesBoard.clear();
        int numCards= rand.nextInt(3)+1;                //Random for number of cards to be displayed on board
        for(int i = 0; i < numCards; i++)
        {

            if(s.equals("Blue"))
            {
                playerNotesBoard.add(new Blue());
            }
            else if(s.equals("Red"))
            {
                playerNotesBoard.add(new Red());
            }
            else if(s.equals("Green"))
            {
                playerNotesBoard.add(new Green());
            }
            else if(s.equals("Orange"))
            {
                playerNotesBoard.add(new Orange());
            }
            else if(s.equals("Yellow"))
            {
                playerNotesBoard.add(new Yellow());
            }
        }

    }
    public boolean sampleCheckMatch(CoreCard card)                          //Sample for unit testing
    {
        boolean match= false;
        int count=0;
        while(!match && count < playerNotesBoard.size())
        {
            if(card.toString().equals(playerNotesBoard.get(count).toString()))
            {
                if(!playerNotesBoard.get(count).getMatched())
                {
                    playerNotesBoard.get(count).setMatched(true);
                }
                match= true;
                return match;
            }
            else if(count == playerNotesBoard.size())
            {
                break;
            }
            count++;
        }
        return match;
    }

    public void directSetEnergy(int newEnergy)              //Sample method for unit testing
    {
        energy = newEnergy;
    }

}


