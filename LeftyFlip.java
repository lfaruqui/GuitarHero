
public class LeftyFlip extends ActionCard
{
    public LeftyFlip()                                          //Constructor for Lefty Flip based on super
    {
        super("Lefty Flip", 120);
    }

    @Override                                                   //Overwrites playCard to create a new note board for the player and print it
    public void playCard(Player cardUser, Player otherPlayer)
    {
        cardUser.createBoard();
        cardUser.printBoard();
    }
}



