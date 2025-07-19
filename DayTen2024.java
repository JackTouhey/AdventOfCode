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
        if(topographicMap[currentTrail.getCurrentPosition().getY()][currentTrail.getCurrentPosition().getX()] == 9){
            head.addRoute(currentTrail);
        }
        else if(!checkIfNextStep(currentTrail)){
        }
        else{
            
        }
    }
    private static Boolean checkIfNextStep(Trail currentTrail){
        return checkIfNorthStep(currentTrail) || checkIfEastStep(currentTrail) || checkIfSouthStep(currentTrail) || checkIfWestStep(currentTrail);
    }
    private static Boolean checkIfNorthStep(Trail currentTrail){
        Boolean isNextStep = false;
        int currentX = currentTrail.getCurrentPosition().getX();
        int currentY = currentTrail.getCurrentPosition().getY();
        Integer nextElevation = topographicMap[currentY][currentX] + 1;
        if(currentY > 0){
            if(topographicMap[currentY - 1][currentX].equals(nextElevation)){
                isNextStep = true;
            }
        }
        return isNextStep;
    }
    private static Boolean checkIfSouthStep(Trail currentTrail){
        Boolean isNextStep = false;
        int currentX = currentTrail.getCurrentPosition().getX();
        int currentY = currentTrail.getCurrentPosition().getY();
        Integer nextElevation = topographicMap[currentY][currentX] + 1;
        if(currentY < topographicMap.length){
            if(topographicMap[currentY + 1][currentX].equals(nextElevation)){
                isNextStep = true;
            }
        }
        return isNextStep;
    }
    private static Boolean checkIfEastStep(Trail currentTrail){
        Boolean isNextStep = false;
        int currentX = currentTrail.getCurrentPosition().getX();
        int currentY = currentTrail.getCurrentPosition().getY();
        Integer nextElevation = topographicMap[currentY][currentX] + 1;
        if(currentX < topographicMap[currentY].length){
            if(topographicMap[currentY][currentX + 1].equals(nextElevation)){
                isNextStep = true;
            }
        }
        return isNextStep;
    }
    private static Boolean checkIfWestStep(Trail currentTrail){
        Boolean isNextStep = false;
        int currentX = currentTrail.getCurrentPosition().getX();
        int currentY = currentTrail.getCurrentPosition().getY();
        Integer nextElevation = topographicMap[currentY][currentX] + 1;
        if(currentX > 0){
            if(topographicMap[currentY][currentX - 1].equals(nextElevation)){
                isNextStep = true;
            }
        }
        return isNextStep;
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
    public Coordinate getCurrentPosition(){
        return route.get(route.size()-1);
    }
}
