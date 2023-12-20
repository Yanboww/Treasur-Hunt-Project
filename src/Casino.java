import java.util.Scanner;
public class Casino {
    private static int totalEarning =0;
    private Hunter character;
    private Scanner s;
    public Casino(Hunter c)
    {
        character =c;
        s = new Scanner(System.in);
    }
    public void enterCasino()
    {
        if(character.getGold()<=0)
        {
            System.out.println();
            System.out.println("Get out you pleb");
        }
        else
        {
            System.out.println("Do you want to play in Lucky Dice? (y/n): ");
            String reply = s.nextLine();
            if(reply.equals("y") || reply.equals("Y"))
            {
                playGame();
            }
            else{
                System.out.println("Okay get out");
            }
        }
    }

    public void playGame()
    {
        System.out.println();
        System.out.println("Welcome to Lucky Dice! The rules are simple:");
        System.out.println("- If the hunter gets the number exactly right, they get double their gold!\n" +
                "- If the hunter gets within 2 of the number, they get their gold back.\n" +
                "- if the hunter is more than 2 away from the number, they lose all of their gold\n");
        String repeat = "y";
       while(repeat.equals("y") || repeat.equals("Y"))
       {
           System.out.print("How much gold do you want to bet?: ");
           int betAmount;
           try{
               betAmount = Integer.parseInt(s.nextLine());
           }
           catch(NumberFormatException e)
           {
               System.out.println("That is not a valid amount!");
               betAmount =1000000000;
           }
           while (character.getGold()<betAmount)
           {
               System.out.println("Stop joking. You are poor and don't have that much gold!");
               System.out.print("How much gold do you want to bet? (x) to exit: ");
               String reply = s.nextLine();
               if(reply.equals("x") || reply.equals("X"))
               {
                   break;
               }
               try{
                   betAmount = Integer.parseInt(reply);
               }
               catch (NumberFormatException e)
               {
                   System.out.println("That is not a valid amount!");
                   betAmount =1000000000;
               }
           }
           System.out.println("Okay dear customer! You have bet " + betAmount + " gold!");
           System.out.print("please enter you guess between 1 and 12: ");
           int guess = Integer.parseInt(s.nextLine());
           int doubleDice = (int)(Math.random()*12)+1;
           if(guess == doubleDice)
           {
               System.out.println("Congratulations! You were exactly on the mark!");
               System.out.println("You win " + betAmount*2 + " gold!");
               character.changeGold(betAmount);
               totalEarning+=betAmount*2;
           }
           else if(guess-2 == doubleDice || guess+2 == doubleDice)
           {
               System.out.println("You were quite close!");
               System.out.println("You received your " + betAmount + " gold back");
           }
           else{
               System.out.println("Wow you lost. Maybe you should try again! Most gamblers quit...BLAH BLAH BLAH");
               System.out.println("You ignored the host as they took your " + betAmount + " gold away");
               totalEarning-=betAmount;
               character.changeGold(-betAmount);
           }
           character.setLuck(character.getGold()/10);
           System.out.print("Do you want to play again? (y/n): ");
           repeat = s.nextLine();
       }
       
    }
}
