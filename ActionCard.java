public class ActionCard implements Card                                     //CLass for the action cards, those with cost
{
    private String cardName;
    private int cardCost;

    public ActionCard(String name, int cost)                            //Filled constructor for each type
    {
        cardName= name;
        cardCost= cost;
    }

    public int getCardCost()
    {
        return cardCost;
    }

    @Override                                                           //Overwrites playCard method in interface
    public void playCard(Player user, Player target)
    {
        System.out.println("You played " + toString());
    }

    @Override                                                           //Overwrites to string to print name
    public String toString()
    {
        return cardName;
    }
}
