
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;
import java.util.Scanner;

public class UnitTestGuitarHero                                     //Class to unit test each method for specific cases
{
    private Scanner in = new Scanner(System.in);
    private GuitarHeroGame testGame = new GuitarHeroGame(in);
    private Player player1 = new Player();
    private Player player2 = new Player();
    private Random rand = new Random();

    @Test
    public void testTurnOrder1()//p1<p2
    {
        player1.directSetScore(100);
        player2.directSetScore(200);
        int result = testGame.sampleTurnOrder(player1, player2);
        assertEquals(2, result);
    }
    @Test
    public void testTurnOrder2()//p2<p1
    {
        player1.directSetScore(200);
        player2.directSetScore(100);
        int result = testGame.sampleTurnOrder(player1, player2);
        assertEquals(1, result);
    }
    @Test
    public void testTurnOrder3()//equal
    {
        player1.directSetScore(200);
        player2.directSetScore(200);
        int result = testGame.sampleTurnOrder(player1, player2);
        assertEquals(rand.nextInt(2)+1, result);
    }

    @Test
    public void testCheckMatch1()
    {
        Player player1 = new Player();
        player1.createSampleNotesBoard("Blue");
        CoreCard c = new Blue();
        boolean result = player1.sampleCheckMatch(c);
        assertEquals(true, result);

    }
    @Test
    public void testCheckMatch2()
    {
        Player player1 = new Player();
        player1.createSampleNotesBoard("Orange");
        CoreCard c = new Orange();
        boolean result = player1.sampleCheckMatch(c);
        assertEquals(true, result);

    }
    @Test
    public void testCheckMatch3()
    {
        Player player1 = new Player();
        player1.createSampleNotesBoard("Green");
        CoreCard c = new Green();
        boolean result = player1.sampleCheckMatch(c);
        assertEquals(true, result);

    }
    @Test
    public void testCheckMatch4()
    {
        Player player1 = new Player();
        player1.createSampleNotesBoard("Red");
        CoreCard c = new Red();
        boolean result = player1.sampleCheckMatch(c);
        assertEquals(true, result);

    }
    @Test
    public void testCheckMatch5()
    {
        Player player1 = new Player();
        player1.createSampleNotesBoard("Yellow");
        CoreCard c = new Yellow();
        boolean result = player1.sampleCheckMatch(c);
        assertEquals(true, result);
    }

    @Test
    public void testCheckMatch6()//no match
    {
        Player player1 = new Player();
        player1.createSampleNotesBoard("Yellow");
        CoreCard c = new Blue();
        boolean result = player1.sampleCheckMatch(c);
        assertEquals(false, result);

    }
    @Test
    public void testCheckMatch7()//no match
    {
        Player player1 = new Player();
        player1.createSampleNotesBoard("Green");
        CoreCard c = new Blue();
        boolean result = player1.sampleCheckMatch(c);
        assertEquals(false, result);

    }
    @Test
    public void testCheckMatch8()//no match
    {
        Player player1 = new Player();
        player1.createSampleNotesBoard("Orange");
        CoreCard c = new Blue();
        boolean result = player1.sampleCheckMatch(c);
        assertEquals(false, result);
    }

    @Test
    public void testCheckTurn1()//has energy
    {
        Player player1 = new Player();
        player1.directSetEnergy(500);
        player1.buildDeck();
        player1.buildHand();
        boolean result = player1.checkTurn();
        assertEquals(true, result);
    }
    @Test
    public void testCheckTurn2()//lacks energy
    {
        Player player1 = new Player();
        player1.directSetEnergy(20);
        player1.buildDeck();
        player1.buildHand();
        boolean result = player1.checkTurn();
        assertEquals(false, result);
    }


}
