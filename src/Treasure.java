import java.util.ArrayList;
import java.util.List;
public class Treasure {
    private static List<String> treasureStorage = new ArrayList<>();
    private static String currentTreasure;

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

    public static void addTreasure(String treasure)
    {
        if(treasureStorage.contains(treasure))
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

}