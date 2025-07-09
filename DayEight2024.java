import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class DayEight2024 {
    private static ArrayList<Antenna> antennae = new ArrayList<>();
    private static HashMap<Character, ArrayList<Antenna>> antennaByFrequency = new HashMap<>();
    private static String[][] grid = loadData();
    public static void main(String[] args) {
        // printGrid(grid);
        HashSet<Coordinate> antinodes = new HashSet<>();
        for(Character c : antennaByFrequency.keySet()){
            for(Antenna a : antennaByFrequency.get(c)){
                for(Antenna b : antennaByFrequency.get(c)){
                    if(!(a.equals(b))){
                        for(Coordinate newAntinode : getAntinodesFromTwoAntenna(a, b)){
                            Boolean antinodeContained = false;
                            for(Coordinate antinode : antinodes){
                                if(antinode.getX() == newAntinode.getX() && antinode.getY() == newAntinode.getY()){
                                    antinodeContained = true;
                                }
                            }
                            if(!antinodeContained){
                                antinodes.add(newAntinode);
                            }
                        }
                    }
                }
            }
        }
        int count = 0;
        for(Coordinate c : antinodes){
            if(c.getX() >= 0 && c.getX() < grid[0].length && c.getY() >= 0 && c.getY() < grid.length){
                System.out.println("Antinode coordinate: " + c.getY() + "," + c.getX());
                count++;
                if(grid[c.getY()][c.getX()].equals(".")){
                    grid[c.getY()][c.getX()] = "#";
                }
            }
        }
        printGrid(grid);
        System.out.println("Count: " + count);
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
    public static String[][] loadData(){
        String[][] returnGrid = new String[0][0];
        try {
            File dataFile= new File("DataFiles\\DayEightTestData.txt");
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
        for(int y = 0; y < grid.length; y++){
            Scanner sc2 = new Scanner(sc.nextLine());
            for(int x = 0; x < grid[0].length; x++){
                sc2.useDelimiter("");
                String next = sc2.next();
                if(!next.equals(".")){
                    Coordinate location = new Coordinate(x, y);
                    char c = next.charAt(0);
                    Antenna newAntenna = new Antenna(location, c);
                    antennae.add(newAntenna);
                    if(antennaByFrequency.containsKey(c)){
                       antennaByFrequency.get(c).add(newAntenna); 
                    }
                    else{
                        ArrayList<Antenna> newArray = new ArrayList<>();
                        newArray.add(newAntenna);
                        antennaByFrequency.put(c, newArray);
                    }
                }
                grid[y][x] = next;
            }
        }
        return grid;
    }
    private static Boolean checkIfCoordinateInGrid(int x, int y){
        return x >= 0 && x < grid[0].length && y >= 0 && y < grid.length;
    }
    private static ArrayList<Coordinate> getAntinodesFromTwoAntenna(Antenna antenna1, Antenna antenna2){
        ArrayList<Coordinate> antinodes = new ArrayList<>();
        int xDistance = antenna1.getLocation().getX() - antenna2.getLocation().getX();
        int yDistance = antenna1.getLocation().getY() - antenna2.getLocation().getY();
        int currentX = antenna1.getLocation().getX();
        int currentY = antenna1.getLocation().getY();
        while(checkIfCoordinateInGrid(currentX, currentY)){
            antinodes.add(new Coordinate(currentX, currentY));
            currentX += xDistance;
            currentY += yDistance;
        }
        currentX = antenna2.getLocation().getX();
        currentY = antenna2.getLocation().getY();
        while(checkIfCoordinateInGrid(currentX, currentY)){
            antinodes.add(new Coordinate(currentX, currentY));
            currentX -= xDistance;
            currentY -= yDistance;
        }
        return antinodes;
    }
}
class Antenna{
    Coordinate location;
    char frequency;
    public Antenna(Coordinate location, char frequency){
        this.location = location;
        this.frequency = frequency;
    }
    public Coordinate getLocation(){return this.location;}
    public char getFrequency(){return this.frequency;}
}
class Coordinate{
    int x;
    int y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
}