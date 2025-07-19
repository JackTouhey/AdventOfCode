import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayTen2024 {
    private static final Integer[][] topographicMap = loadData();
    public static void main(String[] args) {
        for(Integer[] row : topographicMap){
            for(Integer i : row){
                System.out.print(i);
            }
            System.out.println();
        }
    }
    public static Integer[][] loadData(){
        Integer[][] returnGrid = new Integer[0][0];
        try {
            File dataFile= new File("DataFiles\\DayTenTestData.txt");
            Scanner sc = new Scanner(dataFile);
            sc.useDelimiter("");
            Integer[][] grid = createArray(sc);
            Scanner sc2 = new Scanner(dataFile);
            sc2.useDelimiter("");
            returnGrid = populateArray(sc2, grid);
        } catch (FileNotFoundException e) {
        }
        return returnGrid;
    }
    public static Integer[][] createArray(Scanner sc){
        int height = 1;
        String rowOne = sc.nextLine();
        int width = rowOne.length();
        while(sc.hasNext()){
            height++;
            sc.nextLine();
        }
        System.out.println("Array height: " + height + " Array width: " + width);
        Integer[][] grid = new Integer[height][width];
        return grid;
    }
    public static Integer[][] populateArray(Scanner sc, Integer[][] grid){
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
}
