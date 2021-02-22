public class ShakeScreen extends ActionCard
{
    public ShakeScreen()                                                //Constructor For Shake Screen
    {
        super("Shake Screen", 150);
    }

    @Override
    public void playCard(Player cardUser, Player target)            //Overwrites playCard to have the user pay cost and make opponent discard 3 cards
    {
        cardUser.setEnergy(cardUser.getEnergy()-150);
        target.removeCards(target.getHand(),3);
    }
}
