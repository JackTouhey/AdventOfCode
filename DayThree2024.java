import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThree2024 {
    ArrayList<Mul> validMuls = new ArrayList<>();
    public static void main(String[] args) {
        
    }
    public static void loadData(){
        try {
            File dataFile = new File("DataFiles\\DayThreeData.txt");
            Scanner sc = new Scanner(dataFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Mul{
    Integer one;
    Integer two;
    public Mul(Integer i1, Integer i2){
        this.one = i1;
        this.two = i2;
    }
}
