import java.io.*;
import java.util.*;
public class DaySix2024 {
    public static int guardX;
    public static int guardY;
    public static String[][] grid = loadData();
    public static String guardDirection = "north";
    public static void main(String[] args) {
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[y].length; x++){
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
        System.out.println("Guard position x: " + guardX + " y: " + guardY);
        moveNorth();
        moveEast();
        moveSouth();
        moveSouth();
        moveWest();
        moveWest();
        moveWest();
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[y].length; x++){
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
        System.out.println("Guard position x: " + guardX + " y: " + guardY);
    }
    public static void moveGuard(){
        while(guardX >= 0 || guardY >= 0){
            switch (guardDirection) {
                case "north":
                    if(!(grid[guardY-1][guardX].equals("#"))){

                    }
                    else{
                        guardDirection = "east";
                    }
                    break;
                case "east":
                    if(!(grid[guardY][guardX+1].equals("#"))){

                    }
                    else{
                        guardDirection = "south";
                    }
                    break;
                case "south":
                    if(!(grid[guardY+1][guardX].equals("#"))){

                    }
                    else{
                        guardDirection = "west";
                    }
                    break;
                case "west":
                    if(!(grid[guardY][guardX-1].equals("#"))){

                    }
                    else{
                        guardDirection = "north";
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    public static void moveNorth(){
        grid[guardY - 1][guardX] = "^";
        grid[guardY][guardX] = "X";
        guardY -= 1;
    }
    public static void moveSouth(){
        grid[guardY + 1][guardX] = "^";
        grid[guardY][guardX] = "X";
        guardY += 1;
    }
    public static void moveEast(){
        grid[guardY][guardX + 1] = "^";
        grid[guardY][guardX] = "X";
        guardX += 1;
    }
    public static void moveWest(){
        grid[guardY][guardX - 1] = "^";
        grid[guardY][guardX] = "X";
        guardX -= 1;
    }
    public static String[][] loadData(){
        String[][] returnGrid = new String[0][0];
        try {
            File dataFile= new File("DataFiles\\DaySixTestData.txt");
            Scanner sc = new Scanner(dataFile);
            sc.useDelimiter("");
            String[][] grid = createArray(sc);
            Scanner sc2 = new Scanner(dataFile);
            sc2.useDelimiter("");
            returnGrid = populateArray(sc2, grid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnGrid;
    }
    public static String[][] createArray(Scanner sc){
        int height = 1;
        String rowOne = sc.nextLine();
        int width = rowOne.length();
        while(sc.hasNext()){
            height++;
            sc.nextLine();
        }
        System.out.println("Array height: " + height + " Array width: " + width);
        String[][] grid = new String[height][width];
        return grid;
    }
    public static String[][] populateArray(Scanner sc, String[][] grid){
        for(int i = 0; i < grid.length; i++){
            Scanner sc2 = new Scanner(sc.nextLine());
            for(int ii = 0; ii < grid[0].length; ii++){
                sc2.useDelimiter("");
                String next = sc2.next();
                if(next.equals("^")){
                    guardX = ii;
                    guardY = i;
                }
                // System.out.println("Column: " + i +" Row: " + ii + " Adding: " + next);
                grid[i][ii] = next;
            }
        }
        return grid;
    }
}
