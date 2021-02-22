public class StarPower extends ActionCard
{
    public StarPower()                                              //Constructor for Star Power
    {
        super("Star Power", 200);
    }

    @Override
    public void playCard(Player cardUser, Player target)            //Overwrites playCard to have user pay the cost and increase their combo count
    {
        cardUser.setEnergy(cardUser.getEnergy()-200);
        cardUser.setComboCount(cardUser.getComboCount()+5);
    }
}
