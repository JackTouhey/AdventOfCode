import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwo2024{
    public static void main(String[] args) {
        ArrayList<Report> reports = loadData();
        for(Report r : reports){
            r.printLevels();
        }
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
    public Report(ArrayList<Integer> levels){
        this.levels = levels;
    }
    public void printLevels(){
        System.out.println(this.levels + " : " + isAscending());
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
}