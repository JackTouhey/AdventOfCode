import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class DayTwo2024{
    public static void main(String[] args) {
        ArrayList<Report> reports = loadData();
        Integer count = 0;
        for(Report r : reports){
            if(r.isSafe()){
                count ++;
            }
        }
        System.out.println("Number of safe reports: " + count);
    }
    private static ArrayList<Report> loadData(){
        ArrayList<Report> reports = new ArrayList<>();
        try {
            File dataFile = new File("DayTwoData.txt");
            Scanner sc = new Scanner(dataFile);
            while(sc.hasNext()){
                String line = sc.nextLine();
                Scanner sc2 = new Scanner(line);
                ArrayList<Integer> levels = new ArrayList<>();
                while(sc2.hasNext()){
                    String nextLevel = sc2.next();
                    levels.add(Integer.valueOf(nextLevel));
                }
                sc2.close();
                Report nextReport = new Report(levels);
                reports.add(nextReport);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return reports;  
    }
}
class Report {
    ArrayList<Integer> levels = new ArrayList<>();
    HashSet<Integer> problemIndexes = new HashSet<>();
    public Report(ArrayList<Integer> levels){
        this.levels = levels;
    }
    public void printLevels(){
        System.out.println(this.levels + " : " + isSafe() + ". Problem indexes = " + problemIndexes.size());
    }
    private Boolean isAscending(){
        Boolean isAscending;
        if(levels.get(0) < levels.get(1)){
            isAscending = true;
        }
        else{
            isAscending = false;
        }
        return isAscending;
    }
    private Boolean isLinear(){
        Boolean isLinear = true;
        if(isAscending()){
            for(int i = 1; i < levels.size(); i++){
                if(levels.get(i) <= levels.get(i-1)){
                    isLinear = false;
                    problemIndexes.add(i);
                }
            }
        }
        else{
            for(int i = 1; i < levels.size(); i++){
                if(levels.get(i) >= levels.get(i-1)){
                    isLinear = false;
                    problemIndexes.add(i);
                }
            }
        }
        return isLinear;
    }
    private Boolean isGradual(){
        Boolean isGradual = true;
        for(int i = 1; i < levels.size(); i++){
            Integer difference = levels.get(i-1) - levels.get(i);
            difference = Math.abs(difference);
            if(difference < 1 || difference > 3){
                isGradual = false;
                problemIndexes.add(i);
            }
        }
        return isGradual;
    }
    public Boolean isSafe(){
        Boolean isSafe = true;
        if(!isLinear() || !isGradual()){
            isSafe = false;
        }
        return isSafe;
    }
}