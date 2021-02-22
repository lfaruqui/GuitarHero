public class Energy extends ActionCard
{
    public Energy()                                 //Constructor for energy based on super class
    {
       super("Energy", 50);
    }

    @Override
    public void playCard(Player cardUser, Player target)        //Overwrites to have player pay cost and then increase their energy
    {
        cardUser.setEnergy(cardUser.getEnergy()-50);
        cardUser.setEnergy(cardUser.getEnergy() + 150);
    }
}
