public class TuneUp extends ActionCard
{
    public TuneUp()                                                         //Constructor for Tune Up
    {
        super("Tune Up", 120);
    }

    @Override
    public void playCard(Player cardUser, Player target)        //Overwrites playCard to have user pay cost and affect opponent
    {
        cardUser.setEnergy(cardUser.getEnergy()-120);
        target.setCheck(6);                                             //6 is number signifying this card is used
    }
}
