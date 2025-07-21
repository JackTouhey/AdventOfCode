
import java.util.HashSet;

public class DayTwelve2024 {
    private static final char[][] garden = DataLoader.generateCharGrid("DataFiles\\DayTwelveTestData.txt");
    public static void main(String[] args) {
        printGarden();
    }
    private static void printGarden(){
        for(char[] row : garden){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
class Region{
    private final HashSet<Coordinate> plots;
    private final char plant;
    public Region(HashSet<Coordinate> plots, char plant){
        this.plots = plots;
        this.plant = plant;
    }
}
