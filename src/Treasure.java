import java.util.ArrayList;
import java.util.List;
public class Treasure {
    private static final List<String> treasureStorage = new ArrayList<>();
    private static String currentTreasure;
    private static boolean treasureFound;
    private static String treasureStatus;

    public static void genTreasure()
    {
        int randomInt = (int)(Math.random()*3)+1;
        if(randomInt == 1)
        {
            currentTreasure = "Eye of Das";
        }
        else if(randomInt == 2)
        {
            currentTreasure = "Can of Pure Oxygen";
        }
        else{
            currentTreasure = "Diamond Hoe";
        }

    }
    public static String getCurrentTreasure()
    {
        return currentTreasure;
    }

    public static boolean isTreasureFound() {
        return treasureFound;
    }

    public static void setTreasureFound(boolean treasureFound) {
        Treasure.treasureFound = treasureFound;
    }

    public static void addTreasure(String treasure)
    {
        if(treasureStorage.contains(treasure) && !TreasureHunter.isEasyMode())
        {
            System.out.println("Duplicate Treasure! You curled your arm and threw, never to see the treasure again");
        }
        else{
            treasureStorage.add(treasure);
        }
    }

    public static List<String> returnTreasureStorage()
    {
        return treasureStorage;
    }

    public static boolean treasureHunt()
    {
        if (treasureFound) {
            treasureStatus = "You already found the treasure in this town.";
            return false;
        }
        int randomInt = (int)(Math.random()*2)+1;
        if(randomInt==1){
            treasureStatus = "You found the glorious " + currentTreasure+"!";
            addTreasure(currentTreasure);
            treasureFound = true;
            return true;
        }
        treasureStatus = "You did not find any treasure!";
        return false;
    }

    public static String getTreasureStatus()
    {
        return treasureStatus;
    }
    public static void changeTreasureStatus()
    {
        treasureStatus ="";
    }


}
