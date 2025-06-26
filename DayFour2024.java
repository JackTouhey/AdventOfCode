import java.io.File;
import java.util.Scanner;

public class DayFour2024 {
    public static void main(String[] args) {
        String[][] grid = loadData();
        System.out.println(grid);
    }
    public static String[][] loadData(){
        String[][] returnGrid = new String[0][0];
        try {
            File dataFile= new File("DataFiles\\DayFourData.txt");
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
                System.out.println("Column: " + i +" Row: " + ii + " Adding: " + next);
                grid[i][ii] = next;
            }
        }
        return grid;
    }
    public static Integer checkX(Integer y, Integer x, String[][] grid){
        Integer count = 0;
        if(x < grid[0].length -3){
            if(checkRight(y, x, grid)){
                count++;
            }
        }
        if(x > 3){
            if(checkLeft(y, x, grid)){
                count++;
            }
        }
        if(y > 3){
            if(checkUp(y, x, grid)){
                count++;
            }
        }
        if(y < grid.length -3){
            if(checkDown(y, x, grid)){
                count++;
            }
        }
        if(x < grid[0].length && y > 3){
            if(checkNE(y, x, grid)){
                count++;
            }
        }
        if(x < grid[0].length -3 && y < grid.length - 3){
            if(checkSE(y, x, grid)){
                count++;
            }
        }
        if(x > 3 && y < grid.length -3){
            if(checkSW(y, x, grid)){
                count++;
            }
        }
        if(x > 3 && y > 3){
            if(checkNW(y, x, grid)){
                count++;
            }
        }
        return count;
    }
    public static Boolean checkRight(Integer y, Integer x, String[][] grid){
        if(grid[y][x+1].equals("M") && grid[y][x+2].equals("A") && grid[y][x+3].equals("S")){
            return true;
        }
        else{
            return false;
        }
    }
    public static Boolean checkLeft(Integer y, Integer x, String[][] grid){
        if(grid[y][x-1].equals("M") && grid[y][x-2].equals("A") && grid[y][x-3].equals("S")){
            return true;
        }
        else{
            return false;
        }
    }
    public static Boolean checkDown(Integer y, Integer x, String[][] grid){
        if(grid[y+1][x].equals("M") && grid[y+2][x].equals("A") && grid[y+3][x].equals("S")){
            return true;
        }
        else{
            return false;
        }
    }
    public static Boolean checkUp(Integer y, Integer x, String[][] grid){
        if(grid[y-1][x].equals("M") && grid[y-2][x].equals("A") && grid[y-3][x].equals("S")){
            return true;
        }
        else{
            return false;
        }
    }
    public static Boolean checkNE(Integer y, Integer x, String[][] grid){
        if(grid[y-1][x+1].equals("M") && grid[y-2][x+2].equals("A") && grid[y-3][x+3].equals("S")){
            return true;
        }
        else{
            return false;
        } 
    }
    public static Boolean checkSE(Integer y, Integer x, String[][] grid){
        if(grid[y+1][x+1].equals("M") && grid[y+2][x+2].equals("A") && grid[y+3][x+3].equals("S")){
            return true;
        }
        else{
            return false;
        } 
    }
    public static Boolean checkSW(Integer y, Integer x, String[][] grid){
        if(grid[y+1][x-1].equals("M") && grid[y+2][x-2].equals("A") && grid[y+3][x-3].equals("S")){
            return true;
        }
        else{
            return false;
        } 
    }
    public static Boolean checkNW(Integer y, Integer x, String[][] grid){
        if(grid[y-1][x-1].equals("M") && grid[y-2][x-2].equals("A") && grid[y-3][x-3].equals("S")){
            return true;
        }
        else{
            return false;
        } 
    }
}
