import java.util.ArrayList;

public class DayTen2024 {
    private static final Integer[][] topographicMap = GridGenerator.generateIntegerGrid("DataFiles\\DayTenTestData.txt");
    private static final ArrayList<Trailhead> trailheads = findTrailheads();
    public static void main(String[] args) {
        for(Trailhead head : trailheads){
            head.printSelf();
        }
    }
    private static ArrayList<Trailhead> findTrailheads(){
        ArrayList<Trailhead> returnList = new ArrayList<>();
        for(int y = 0; y < topographicMap.length; y++){
            for(int x = 0; x < topographicMap[y].length; x++){
                if(topographicMap[y][x] == 0){
                    returnList.add(new Trailhead(new Coordinate(x, y)));
                }
            }
        }
        return returnList;
    }
    private static void populateRoutes(Trailhead head, Trail currentTrail){
        if(topographicMap[currentTrail.getFinalCoordinate().getY()][currentTrail.getFinalCoordinate().getX()] == 9){
            head.addRoute(currentTrail);
        }
        else{

        }
    }
}
class Trailhead {
    private final Coordinate start;
    ArrayList<Trail> routes = new ArrayList<>();
    public Trailhead(Coordinate startCoordinate){
        this.start = startCoordinate;
    }
    public void addRoute(Trail r){routes.add(r);}
    public Coordinate getStart(){return this.start;}
    public void printSelf(){
        System.out.println("Trailhead coordinate x: " + start.getX() + " y: " + start.getY());
    }
}
class Trail {
    ArrayList<Coordinate> route = new ArrayList<>();
    public Trail(Trailhead trailhead){
        route.add(trailhead.getStart());
    }
    public void addCoordinate(Coordinate c){
        route.add(c);
    }
    public Coordinate getFinalCoordinate(){
        return route.get(route.size()-1);
    }
}
