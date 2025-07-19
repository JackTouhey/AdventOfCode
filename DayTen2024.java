import java.util.ArrayList;

public class DayTen2024 {
    private static final Integer[][] topographicMap = GridGenerator.generateIntegerGrid("DataFiles\\DayTenTestData.txt");
    public static void main(String[] args) {
        printMap();
    }
    private static void printMap(){
        for(Integer[] row : topographicMap){
            for(Integer i : row){
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
class Trailhead {
    private Coordinate start;
    ArrayList<Trail> potentialRoutes = new ArrayList<>();
    public Trailhead(Coordinate startCoordinate){
        this.start = startCoordinate;
    }
    public Coordinate getStart(){return this.start;}
}
class Trail {
    ArrayList<Coordinate> route = new ArrayList<>();
    public Trail(Trailhead trailhead){
        route.add(trailhead.getStart());
    }
}
