/**
 * This class is responsible for controlling the Treasure Hunter game.<p>
 * It handles the display of the menu and the processing of the player's choices.<p>
 * It handles all of the display based on the messages it receives from the Town object.
 *
 */
import java.util.Scanner;

public class TreasureHunter
{
    //Instance variables
    private Town currentTown;
    private Casino casino;
    private Hunter hunter;
    private boolean hardMode;
    private static  boolean easyMode;
    private static boolean cheatMode;

    //Constructor
    /**
     * Constructs the Treasure Hunter game.
     */
    public TreasureHunter()
    {
        // these will be initialized in the play method
        currentTown = null;
        casino = null;
        hunter = null;
        hardMode = false;
    }

    // starts the game; this is the only public method
    public void play ()
    {
        welcomePlayer();
        enterTown();
        showMenu();
        end();
    }

    /**
     * Creates a hunter object at the beginning of the game and populates the class member variable with it.
     */
    private void welcomePlayer()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TREASURE HUNTER!");
        System.out.println("Going hunting for the big treasure, eh?");
        System.out.print("What's your name, Hunter? ");
        String name = scanner.nextLine();

        System.out.print("Hard mode, Normal Mode, Easy mode or cheat mode?(h/n/e/c?): ");
        String mode = scanner.nextLine();
        if (mode.equals("h") || mode.equals("H"))
        {
            hardMode = true;
            Hunter.setLuck(-4);
        }
        else if(mode.equals("e") || mode.equals("E"))
        {
            easyMode = true;
            Hunter.setLuck(4);
        }
        else if(mode.equals("c") || mode.equals("C"))
        {
            System.out.println("Enter super secret password! :");
            String pass = scanner.nextLine();
            if(pass.equals("I am a big cheater"))
            {
                cheatMode = true;
            }
        }
        int startingGold = 10;
        if(isEasyMode() || cheatMode)
        {
            startingGold = 50;
        }
        hunter = new Hunter(name, startingGold);
        casino = new Casino(hunter);
    }
    public static boolean isEasyMode()
    {
        return easyMode;
    }
    public static boolean isCheatMode(){return  cheatMode;}

    /**
     * Creates a new town and adds the Hunter to it.
     */
    private void enterTown()
    {
        double markdown = 0.50;
        double toughness = 0.4;
        if (hardMode)
        {
            // in hard mode, you get less money back when you sell items
            markdown = 0.25;

            // and the town is "tougher"
            toughness = 0.75;
        }
        if(easyMode)
        {
            markdown = 1;
            toughness = 0.2;
        }

        // note that we don't need to access the Shop object
        // outside of this method, so it isn't necessary to store it as an instance
        // variable; we can leave it as a local variable
        Shop shop = new Shop(markdown);

        // creating the new Town -- which we need to store as an instance
        // variable in this class, since we need to access the Town
        // object in other methods of this class
        currentTown = new Town(shop, toughness);

        // calling the hunterArrives method, which takes the Hunter
        // as a parameter; note this also could have been done in the
        // constructor for Town, but this illustrates another way to associate
        // an object with an object of a different class
        currentTown.hunterArrives(hunter);
        Treasure.genTreasure();
        Treasure.setTreasureFound(false);
    }

    /**
     * Displays the menu and receives the choice from the user.<p>
     * The choice is sent to the processChoice() method for parsing.<p>
     * This method will loop until the user chooses to exit.
     */
    private void showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!(choice.equals("X") || choice.equals("x")) && hunter.getGold() > 0 && Treasure.returnTreasureStorage().size() <3)
        {
            System.out.println();
            System.out.println(currentTown.getLatestNews());
            if(!Treasure.getTreasureStatus().isEmpty())
            {
                System.out.println(Treasure.getTreasureStatus());
            }
            Treasure.changeTreasureStatus();
            System.out.println("***");
            System.out.println(hunter);
            System.out.println(currentTown);
            System.out.println("(B)uy something at the shop.");
            System.out.println("(S)ell something at the shop.");
            System.out.println("(M)ove on to a different town.");
            System.out.println("(L)ook for trouble!");
            System.out.println("(H)unt for treasure!");
            System.out.println("(C)heck out the Casino");
            System.out.println("Give up the hunt and e(X)it.");
            System.out.println();
            System.out.print("What's your next move? ");
            choice = scanner.nextLine();
            choice = choice.toUpperCase();
            processChoice(choice);
        }
       if(currentTown.getLatestNews().contains("gold"))
        {
            System.out.println();
            System.out.println(currentTown.getLatestNews());
        }

    }

    private void end() {
        System.out.println();
        if (hunter.getGold() <= 0) {
            System.out.println("You ran out of gold." + Shop.red + " GAME OVER!" + Shop.reset);
        }
        else if (Treasure.returnTreasureStorage().size() >= 3) {
            System.out.println("You found three treasures. You are an epic gamer: " + Shop.green + " YOU WIN!" + Shop.reset);
        }
        else {
            System.out.println("Something went wrong??");
        }
    }

    /**
     * Takes the choice received from the menu and calls the appropriate method to carry out the instructions.
     * @param choice The action to process.
     */
    private void processChoice(String choice)
    {
        if (choice.equals("B") || choice.equals("b") || choice.equals("S") || choice.equals("s"))
        {
            currentTown.enterShop(choice);
        }
        else if (choice.equals("M") || choice.equals("m"))
        {
            if (currentTown.leaveTown())
            {
                //This town is going away so print its news ahead of time.
                System.out.println(currentTown.getLatestNews());
                enterTown();
            }
        }
        else if (choice.equals("L") || choice.equals("l"))
        {
            currentTown.lookForTrouble();
        }
        else if (choice.equals("H") || choice.equals("h"))
        {
            Treasure.treasureHunt();
        }
        else if (choice.equals("X") || choice.equals("x"))
        {
            System.out.println("Fare thee well, " + hunter.getHunterName() + "!");
        }
        else if (choice.equals("C") || choice.equals("c"))
        {
            casino.enterCasino();
        }
        else
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
    }
}