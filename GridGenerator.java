import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridGenerator {
    public static Integer[][] generateIntegerGrid(String filePath){
        Integer[][] returnGrid = new Integer[0][0];
        try {
            File dataFile= new File(filePath);
            Scanner sc = new Scanner(dataFile);
            sc.useDelimiter("");
            Integer[][] grid = createIntegerArray(sc);
            Scanner sc2 = new Scanner(dataFile);
            sc2.useDelimiter("");
            returnGrid = populateIntegerArray(sc2, grid);
        } catch (FileNotFoundException e) {
        }
        return returnGrid;
    }
    private static Integer[][] createIntegerArray(Scanner sc){
        int height = 1;
        String rowOne = sc.nextLine();
        int width = rowOne.length();
        while(sc.hasNext()){
            height++;
            sc.nextLine();
        }
        Integer[][] grid = new Integer[height][width];
        return grid;
    }
    private static Integer[][] populateIntegerArray(Scanner sc, Integer[][] grid){
        for (Integer[] row : grid) {
            try (Scanner sc2 = new Scanner(sc.nextLine())) {
                for (int ii = 0; ii < row.length; ii++) {
                    sc2.useDelimiter("");
                    Integer next = sc2.nextInt();
                    row[ii] = next;
                }
            } catch (Exception e) {}
        }
        return grid;
    }
    public static String[][] generateStringGrid(String filePath){
        String[][] returnGrid = new String[0][0];
        try {
            File dataFile= new File(filePath);
            Scanner sc = new Scanner(dataFile);
            sc.useDelimiter("");
            String[][] grid = createStringArray(sc);
            Scanner sc2 = new Scanner(dataFile);
            sc2.useDelimiter("");
            returnGrid = populateStringArray(sc2, grid);
        } catch (FileNotFoundException e) {
        }
        return returnGrid;
    }
    private static String[][] createStringArray(Scanner sc){
        int height = 1;
        String rowOne = sc.nextLine();
        int width = rowOne.length();
        while(sc.hasNext()){
            height++;
            sc.nextLine();
        }
        String[][] grid = new String[height][width];
        return grid;
    }
    private static String[][] populateStringArray(Scanner sc, String[][] grid){
        for (String[] row : grid) {
            try (Scanner sc2 = new Scanner(sc.nextLine())) {
                for (int ii = 0; ii < row.length; ii++) {
                    sc2.useDelimiter("");
                    String next = sc2.next();
                    row[ii] = next;
                }
            } catch (Exception e) {}
        }
        return grid;
    }
}
