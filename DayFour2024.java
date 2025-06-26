import java.io.File;
import java.util.Scanner;

public class DayFour2024 {
    public static void main(String[] args) {
        loadData();
    }
    public static void loadData(){
        try {
            File dataFile= new File("DataFiles\\DayFourData.txt");
            Scanner sc = new Scanner(dataFile);
            sc.useDelimiter("");
            createArray(sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createArray(Scanner sc){
        int height = 1;
        String rowOne = sc.nextLine();
        int width = rowOne.length();
        while(sc.hasNext()){
            height++;
            sc.nextLine();
        }
        System.out.println("Array height: " + height + " Array width: " + width);
        String[][] grid = new String[height][width];
    }
}
