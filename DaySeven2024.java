import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class DaySeven2024 {
    private static ArrayList<Equation> equations = new ArrayList<>();
    public static void main(String[] args) {
        equations = loadData();
        int sum = 0;
        for(Equation e : equations){
            System.out.println();
            e.printSelf();
            // System.out.print("Success: " + e.testSelf());
            if(e.testSelf()){
                sum += e.getTestValue();
            }
        }
        System.out.println("Sum: " + sum);
    }
    private static ArrayList<Equation> loadData(){
        ArrayList<Equation> equations = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("DataFiles\\DaySevenData.txt"));
            while(sc.hasNext()){
                ArrayList<Double> values = new ArrayList<>();
                Scanner lineScanner = new Scanner(sc.nextLine());
                lineScanner.useDelimiter(":");
                Double testValue = lineScanner.nextDouble(); 
                lineScanner.useDelimiter(" ");
                lineScanner.next();
                while(lineScanner.hasNext()){
                    values.add(lineScanner.nextDouble());
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
    Double testValue;
    ArrayList<Double> values = new ArrayList<>();
    public Equation(Double testValue, ArrayList<Double> values){
        this.testValue = testValue;
        this.values = new ArrayList<>(values);
    }
    public void printSelf(){
        System.out.println(testValue + ": " + values);
    }
    public Double getTestValue(){return this.testValue;}
    public Boolean testSelf(){
        Boolean success = false;
        ArrayList<Double> currentResults = new ArrayList<>();
        for(int i = 1; i < values.size(); i++){
            if(currentResults.isEmpty()){
                currentResults.add(values.get(i-1) + values.get(i));
                currentResults.add(values.get(i-1) * values.get(i));
                System.out.println("Starting currentValues: " + currentResults.get(0) + ", " + currentResults.get(1));
            }
            else{
                System.out.println("Handling value: " + values.get(i));
                Integer resultsToBeRemoved = currentResults.size();
                for(int ii = 0; ii < resultsToBeRemoved; ii++){
                    Double additionValue = currentResults.get(ii) + values.get(i);
                    Double multiplicationValue = currentResults.get(ii) * values.get(i);
                    System.out.println("Adding additionValue: " + additionValue +" and mult value: " + multiplicationValue);
                    currentResults.add(additionValue);
                    currentResults.add(multiplicationValue);
                }
                for(int iii = 0; iii < resultsToBeRemoved; iii++){
                    System.out.println("Removing: " + currentResults.get(0));
                    currentResults.remove(0);
                }
            }
        }
        System.out.println("Final values: " + currentResults);
        for(Double i : currentResults){
            if(Objects.equals(i, testValue)){
                success = true;
            }
        }
        return success;
    }
}
