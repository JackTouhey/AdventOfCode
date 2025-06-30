import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DayFive2024 {
    public static ArrayList<Rule> rules = new ArrayList<>();
    public static ArrayList<Update> updates = new ArrayList<>();
    public static void main(String[] args) {
        loadData();
        HashMap<Boolean, ArrayList<Update>> sortedUpdates = sortUpdates();
        ArrayList<Update> correctUpdates = sortedUpdates.get(true);
        ArrayList<Update> incorrectUpdates = sortedUpdates.get(false);
        Integer correctCount = 0;
        for(Update u : correctUpdates){
            Integer median = u.getMedian();
            System.out.println("Adding median: " + median);
            correctCount += median;
        }
        ArrayList<Update> newlyOrderedUpdates = orderIncorrectUpdates(incorrectUpdates);
        System.out.println("Correct count: " + correctCount);
    }
    public static ArrayList<Update> orderIncorrectUpdates(ArrayList<Update> incorrectUpdates){
        ArrayList<Update> orderedUpdates = new ArrayList<>();
        return orderedUpdates;
    }
    public static HashMap<Boolean, ArrayList<Update>> sortUpdates(){
        HashMap<Boolean, ArrayList<Update>> sortedUpdates = new HashMap<>();
        ArrayList<Update> correctUpdates = new ArrayList<>();
        ArrayList<Update> incorrectUpdates = new ArrayList<>();
        for(Update u : updates){    
            Boolean followsRules = true;
            for(Rule r : rules){
                if(u.checkIfRuleApplies(r)){
                    if(!(u.checkIfRuleFollowed(r))){
                        followsRules = false;
                    }
                }
            }
            if(followsRules){
                correctUpdates.add(u);
            }
            else{
                incorrectUpdates.add(u);
            }
        }
        sortedUpdates.put(true, correctUpdates);
        sortedUpdates.put(false, incorrectUpdates);
        return sortedUpdates;
    }
    public static void loadData(){
        try {
            Scanner sc = new Scanner(new File("DataFiles\\DayFiveData.txt"));
            Boolean loadingRules = true;
            while(sc.hasNext()){
                String line = sc.nextLine();
                Scanner lineScanner = new Scanner(line);
                if(loadingRules){
                    if(line.equals("")){
                        loadingRules = false;
                    }
                    else{
                        lineScanner.useDelimiter("");
                        String firstInt = "";
                        String secondInt = "";
                        while(!lineScanner.hasNext("\\|")){
                            firstInt += lineScanner.next();
                            System.out.println("firstInt: " + firstInt);
                        }
                        lineScanner.next();
                        while(lineScanner.hasNext()){
                            secondInt += lineScanner.next();
                        }
                        rules.add(new Rule(Integer.valueOf(firstInt), Integer.valueOf(secondInt)));
                        lineScanner.close();
                    }
                }
                else{
                    ArrayList<Integer> values = new ArrayList<>();
                    lineScanner.useDelimiter(",");
                    while(lineScanner.hasNextInt()){
                        values.add(lineScanner.nextInt());
                    }
                    updates.add(new Update(values));
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Loaded " + rules.size() + " rules");
        System.out.println("Loaded " + updates.size() + " updates");
    }
}
class Update{
    ArrayList<Integer> values = new ArrayList<>();
    public Update(ArrayList<Integer> values){
        this.values = values;
    }
    public Boolean checkIfRuleApplies(Rule rule){
        Boolean ruleApplies = true;
        if(!(values.contains(rule.getFirst()) && values.contains(rule.getLast()))){
            ruleApplies = false;
        }
        System.out.println("Checking update: " + values + " Against rule: " + rule.getFirst() + "|"  + rule.getLast() + " ruleApplies: " + ruleApplies);
        return ruleApplies;
    }
    public Boolean checkIfRuleFollowed(Rule rule){
        Boolean ruleFollowed = true;
        if(!(values.indexOf(rule.getFirst()) < values.indexOf(rule.getLast()))){
            ruleFollowed = false;
        }
        System.out.println("Checking if update: " + values + " follows rule: " + rule.getFirst() + "|" + rule.getLast() + " ruleFollowed: " + ruleFollowed);
        return ruleFollowed; 
    }
    public Integer getMedian(){
        Integer median;
        if(!(values.size() % 2 == 0)){
            Double middle = (double) (values.size()/2);
            median = middle.intValue();
        }
        else{
            median = values.size() / 2;
        }
        return values.get(median);
    }
    public void printSelf(){
        System.out.println("Update: " + values);
    }
}
class Rule{
    Integer first;
    Integer last;
    public Rule(Integer first, Integer last){
        this.first = first;
        this.last = last;
    }
    public Integer getFirst(){
        return first;
    }
    public Integer getLast(){
        return last;
    }
}