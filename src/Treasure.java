import java.util.ArrayList;
import java.util.List;
public class Treasure {
    private static final List<String> treasureStorage = new ArrayList<>();
    private static String currentTreasure;
    private static boolean treasureFound;
    private static String treasureStatus ="";

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
        int randomInt = (int)(Math.random()*100)+1 + Hunter.getLuck();
        if(randomInt >= 80 || TreasureHunter.isCheatMode()){
            treasureStatus = "You found the glorious " + currentTreasure+"!";
            if(TreasureHunter.isCheatMode()){
                treasureStatus = "Wow you are really good at finding treasure the " + currentTreasure+ " was right in front of you this whole time!";
            }
            if(TreasureHunter.isCheatMode() && treasureStorage.contains(currentTreasure))
            {
                treasureStatus = "You already have the " + currentTreasure +  " but we'll keep it anyways!";
            }
            if(Hunter.getLuck() >= 4) {
                treasureStatus = "You're pretty lucky! The " + currentTreasure+ " was close by and you found it!";
            }
            if(Hunter.getLuck() <= -4) {
                treasureStatus = "...You're quite unlucky. It was difficult, but you managed to find " + currentTreasure+ ".";
            }
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
