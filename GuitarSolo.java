public class GuitarSolo extends ActionCard
{
    public GuitarSolo()                                             //Constructor for guitar solo
    {
        super("Guitar Solo", 150);
    }

    @Override                                                   //Overwrites playCard to have player pay the cost and then draw 3 cards
    public void playCard(Player cardUser, Player otherPlayer)
    {
        cardUser.setEnergy(cardUser.getEnergy()-150);
        for(int i = 0; i < 3; i ++)
        {
            cardUser.drawCards(3);
        }
    }
}
