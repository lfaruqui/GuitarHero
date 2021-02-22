import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test
{
    public static void main (String[] args) throws FileNotFoundException
    {
        Scanner in= new Scanner(System.in);
        GuitarHeroGame game= new GuitarHeroGame(in);
        game.playGame();
    }
}
/*So, we had to fix our code by passing in a scanner into the Guitar Hero Game class as we ran into a "NoSuchElementException"
    for Scanner in the playerTurn method. It seemed to be skipping over the input line, irrespective of checking for "scn.hasNextInt()" in the while loop.
    It was working fine one minute, we then closed our IDE's, reopened them and encountered the error believe it or not.
 */