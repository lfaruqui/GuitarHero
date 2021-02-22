import java.util.Random;

public class BreakStrings extends ActionCard
{
    public BreakStrings()                                               //Constructs break string class based on super
    {
        super("Break Strings", 200);
    }

    @Override
    public void playCard(Player cardUser, Player target)                //Overwrites playCard to set which color player can't play
    {
        Random rand = new Random();
        cardUser.setEnergy(cardUser.getEnergy()-120);
        target.setCheck(rand.nextInt(5) + 1); //each number 1 to 5 refers to one of five colors that cant't be used
    }
}
