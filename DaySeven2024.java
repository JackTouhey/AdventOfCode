import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DaySeven2024 {
    private static ArrayList<Equation> equations = new ArrayList<>();
    public static void main(String[] args) {
        equations = loadData();
        for(Equation e : equations){
            e.printSelf();
        }
    }
    private static ArrayList<Equation> loadData(){
        ArrayList<Equation> equations = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("DataFiles\\DaySevenTestData.txt"));
            while(sc.hasNext()){
                ArrayList<Integer> values = new ArrayList<>();
                Scanner lineScanner = new Scanner(sc.nextLine());
                lineScanner.useDelimiter(":");
                Integer testValue = lineScanner.nextInt(); 
                lineScanner.useDelimiter(" ");
                lineScanner.next();
                while(lineScanner.hasNext()){
                    values.add(lineScanner.nextInt());
                }
                equations.add(new Equation(testValue, values));
                lineScanner.close();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return equations;
    }
}
class Equation{
    Integer testValue;
    ArrayList<Integer> values = new ArrayList<>();
    public Equation(Integer testValue, ArrayList<Integer> values){
        this.testValue = testValue;
        this.values = new ArrayList<>(values);
    }
    public void printSelf(){
        System.out.println(testValue + ": " + values);
    }
}
