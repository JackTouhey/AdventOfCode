import java.io.*;
import java.util.*;
public class DaySix2024 {
    public static int guardX;
    public static int guardY;
    public static String[][] mainGrid = loadData();
    public static String guardDirection = "north";
    public static void main(String[] args) {
        // for(int y = 0; y < mainGrid.length; y++){
        //     for(int x = 0; x < mainGrid[y].length; x++){
        //         System.out.print(mainGrid[y][x]);
        //     }
        //     System.out.println();
        // }
        // System.out.println("Guard position x: " + guardX + " y: " + guardY);
        // moveGuard(mainGrid);
        // int xCount = 0;
        // for(int y = 0; y < mainGrid.length; y++){
        //     for(int x = 0; x < mainGrid[y].length; x++){
        //         System.out.print(mainGrid[y][x]);
        //         if(mainGrid[y][x].equals("X")){
        //             xCount++;
        //         }
        //     }
        //     System.out.println();
        // }
        // System.out.println("xCount: " + xCount);
        ArrayList<String[][]> subGrids = generateSubGrids(mainGrid);
        int count = 0;
        for(String[][] subGrid : subGrids){
            System.out.println("Checking grid:");
            printGrid(subGrid);
            Boolean inLoop = moveGuard(subGrid);
            System.out.println("inLoop: " + inLoop);
            if(inLoop){ 
                count++;
            }
        }
        System.out.println("Count: " + count);
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
    public static void printGrid(String[][] grid){
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[y].length; x++){
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static ArrayList<String[][]> generateSubGrids(String[][] grid){
        ArrayList<String[][]> subGrids = new ArrayList<>();
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[y].length; x++){
                String[][] newGrid = new String[grid.length][];
                for(int i = 0; i < grid.length; i++){
                    newGrid[i] = grid[i].clone();
                }
                
                if(!newGrid[y][x].equals("#") && !newGrid[y][x].equals("^")){
                    newGrid[y][x] = "O";
                    System.out.println("Generated newGrid: ");
                    printGrid(newGrid);
                    subGrids.add(newGrid);
                }
            }
        }
        return subGrids; 
    }
    private static Boolean checkIfMomentContained(HashSet<Moment> moments, Moment moment){
        for(Moment m : moments){
            if(moment.checkIfMatching(m)){
                System.out.println("Moment contained");
                return true;
            }
        }
        return false;
    }
    public static Boolean moveGuard(String[][] grid){
        Boolean inBounds = true;
        Boolean inLoop = false;
        ArrayList<int[]> encounteredObstacles = new ArrayList<>();
        int[] originalGuardLocation ={guardY, guardX};
        String originalGuardDirection = guardDirection;
        HashSet<Moment> moments = new HashSet<>();
        while(inBounds && !inLoop){
            Moment currentMoment = new Moment(guardY, guardX, guardDirection);
            if(checkIfMomentContained(moments, currentMoment)){
                inLoop = true;
            }
            else{
                moments.add(currentMoment);
                switch (guardDirection) {
                    case "north" -> {
                        if(guardY == 0){
                            grid[guardY][guardX] = "X";
                            inBounds = false;
                        }
                        else{
                            if(!(grid[guardY-1][guardX].equals("#")) && !(grid[guardY-1][guardX].equals("O"))){
                                // System.out.println("About to move north. Current position y: " + guardY + " x: " + guardX);
                                grid = moveNorth(grid);
                            }
                            else{
                                int[] obstaclePosition = {guardY, guardX};
                                encounteredObstacles.add(obstaclePosition);
                                int obstacleIndex = encounteredObstacles.size() - 1;
                                if(encounteredObstacles.size() > 7){
                                    // inLoop = checkIfLoop(encounteredObstacles, obstacleIndex);
                                }
                                printGrid(grid);
                                // System.out.println("inLoop:" + inLoop);
                                guardDirection = "east";
                            }
                        }
                    }
                    case "east" -> {
                        if(guardX == grid[0].length-1){
                            grid[guardY][guardX] = "X";
                            inBounds = false;
                        }
                        else{
                            if(!(grid[guardY][guardX+1].equals("#")) && !(grid[guardY][guardX+1].equals("O"))){
                                // System.out.println("About to move east. Current position y: " + guardY + " x: " + guardX);
                                grid = moveEast(grid);
                            }
                            else{
                                int[] obstaclePosition = {guardY, guardX};
                                encounteredObstacles.add(obstaclePosition);
                                int obstacleIndex = encounteredObstacles.size() - 1;
                                if(encounteredObstacles.size() > 7){
                                    // inLoop = checkIfLoop(encounteredObstacles, obstacleIndex);
                                }
                                printGrid(grid);
                                // System.out.println("inLoop:" + inLoop);
                                guardDirection = "south";
                            }
                        }
                    }
                    case "south" -> {
                        if(guardY == grid.length-1){
                            grid[guardY][guardX] = "X";
                            inBounds = false;
                        }
                        else{
                            if(!(grid[guardY+1][guardX].equals("#")) && !(grid[guardY+1][guardX].equals("O"))){
                                // System.out.println("About to move south. Current position y: " + guardY + " x: " + guardX);
                                grid = moveSouth(grid);
                            }
                            else{
                                int[] obstaclePosition = {guardY, guardX};
                                encounteredObstacles.add(obstaclePosition);
                                int obstacleIndex = encounteredObstacles.size() - 1;
                                if(encounteredObstacles.size() > 7){
                                    // inLoop = checkIfLoop(encounteredObstacles, obstacleIndex);
                                }
                                printGrid(grid);
                                // System.out.println("inLoop:" + inLoop);
                                guardDirection = "west";
                            }
                        }
                    }
                    case "west" -> {
                        if(guardX == 0){
                            grid[guardY][guardX] = "X";
                            inBounds = false;
                        }
                        else{
                            if(!(grid[guardY][guardX-1].equals("#")) && !(grid[guardY][guardX-1].equals("O"))){
                                // System.out.println("About to move west. Current position y: " + guardY + " x: " + guardX);
                                grid = moveWest(grid);
                            }
                            else{
                                int[] obstaclePosition = {guardY, guardX};
                                encounteredObstacles.add(obstaclePosition);
                                int obstacleIndex = encounteredObstacles.size() - 1;
                                if(encounteredObstacles.size() > 7){
                                    // inLoop = checkIfLoop(encounteredObstacles, obstacleIndex);
                                }
                                printGrid(grid);
                                // System.out.println("inLoop:" + inLoop);
                                guardDirection = "north";
                            }
                        }
                    }
                    default -> throw new AssertionError();
                }
            }
            
        }
        guardY = originalGuardLocation[0];
        guardX = originalGuardLocation[1];
        guardDirection = originalGuardDirection;
        System.out.println("Result:");
        printGrid(grid);
        if(inLoop){
            System.out.println("Guard stuck in loop");
        }
        return inLoop;
    }
    public static Boolean checkIfLoop(ArrayList<int[]> encounteredObstacles, int obstacleIndex){
        System.out.println("Obstacle index   : " + Arrays.toString(encounteredObstacles.get(obstacleIndex)));
        System.out.println("Obstacle index -4: " + Arrays.toString(encounteredObstacles.get(obstacleIndex - 4)));
        System.out.println("Obstacle index -1: " + Arrays.toString(encounteredObstacles.get(obstacleIndex-1)));
        System.out.println("Obstacle index -5: " + Arrays.toString(encounteredObstacles.get(obstacleIndex-5)));
        System.out.println("Obstacle index -2: " + Arrays.toString(encounteredObstacles.get(obstacleIndex-2)));
        System.out.println("Obstacle index -6: " + Arrays.toString(encounteredObstacles.get(obstacleIndex-6)));
        System.out.println("Obstacle index -3: " + Arrays.toString(encounteredObstacles.get(obstacleIndex-3)));
        System.out.println("Obstacle index -7: " + Arrays.toString(encounteredObstacles.get(obstacleIndex-7)));
        return (Arrays.equals(encounteredObstacles.get(obstacleIndex), encounteredObstacles.get(obstacleIndex-4)) && 
                Arrays.equals(encounteredObstacles.get(obstacleIndex-1), encounteredObstacles.get(obstacleIndex-5)) &&
                Arrays.equals(encounteredObstacles.get(obstacleIndex-2), encounteredObstacles.get(obstacleIndex-6)) &&
                Arrays.equals(encounteredObstacles.get(obstacleIndex-3), encounteredObstacles.get(obstacleIndex-7)));
    }
    public static String[][] moveNorth(String[][] grid){
        grid[guardY - 1][guardX] = "^";
        grid[guardY][guardX] = "X";
        guardY -= 1;
        return grid;
    }
    public static String[][] moveSouth(String[][] grid){
        grid[guardY + 1][guardX] = "^";
        grid[guardY][guardX] = "X";
        guardY += 1;
        return grid;
    }
    public static String[][] moveEast(String[][] grid){
        grid[guardY][guardX + 1] = "^";
        grid[guardY][guardX] = "X";
        guardX += 1;
        return grid;
    }
    public static String[][] moveWest(String[][] grid){
        grid[guardY][guardX - 1] = "^";
        grid[guardY][guardX] = "X";
        guardX -= 1;
        return grid;
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
class Moment{
    private final int guardY;
    private final int guardX;
    private final String direction;
    public Moment(int guardY, int guardX, String direction){
        this.guardY = guardY;
        this.guardX = guardX;
        this.direction = direction;
    }
    public Boolean checkIfMatching(Moment m){
        return guardY == m.getGuardY() && guardX == m.getGuardX() && direction.equals(m.getGuardDirection());
    }
    public int getGuardY(){return this.guardY;}
    public int getGuardX(){return this.guardX;}
    public String getGuardDirection(){return this.direction;}
}