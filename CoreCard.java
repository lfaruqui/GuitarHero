public class CoreCard                               //Class for the core, or color, cards
{
    private String cardName;
    private boolean matched;                        //Boolean for whether this specific card was matched

    public CoreCard(String name)                    //Filled constructor for card's name
    {
        cardName= name;
        matched= false;
    }

    public boolean getMatched()                     //Getter
    {
        return matched;
    }

    public void setMatched(boolean newVal)          //Setter
    {
        matched= newVal;
    }

    public void resetMatched()
    {
        matched= false;
    }       //Resets it back to false

    @Override                                           //Overwrites toString
    public String toString()
    {
        return cardName;
    }
}
