import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DayFive2024 {
    public static ArrayList<Rule> rules = new ArrayList<>();
    public static ArrayList<Update> updates = new ArrayList<>();
    public static void main(String[] args) {
        loadData();
        // HashMap<Boolean, ArrayList<Update>> sortedUpdates = sortUpdates(updates);
        // ArrayList<Update> correctUpdates = sortedUpdates.get(true);
        // ArrayList<Update> incorrectUpdates = sortedUpdates.get(false);
        // System.out.println("Incorrect updates:");
        // for(Update u : incorrectUpdates){
        //     u.printSelf();
        // }
        // Integer correctCount = 0;
        // for(Update u : correctUpdates){
        //     Integer median = u.getMedian();
        //     System.out.println("Adding median: " + median);
        //     correctCount += median;
        // }
        // ArrayList<Update> newlyOrderedUpdates = orderIncorrectUpdates(incorrectUpdates);
        // Integer reorderedCount = 0;
        // for(Update u : newlyOrderedUpdates){
        //     u.printSelf();
        //     reorderedCount += u.getMedian();
        // }
        // System.out.println("Correct count: " + correctCount);
        // System.out.println("Reordered count: " + reorderedCount); 
    }
    private static ArrayList<Update> orderIncorrectUpdates(ArrayList<Update> incorrectUpdates){
        ArrayList<Update> orderedUpdates = new ArrayList<>();
        for(Update u : incorrectUpdates){
            u.correctlyOrder(rules);
            orderedUpdates.add(u);
        }
        return orderedUpdates;
    }
    private static HashMap<Boolean, ArrayList<Update>> sortUpdates(ArrayList<Update> updatesToBeSorted){
        HashMap<Boolean, ArrayList<Update>> sortedUpdates = new HashMap<>();
        ArrayList<Update> correctUpdates = new ArrayList<>();
        ArrayList<Update> incorrectUpdates = new ArrayList<>();
        for(Update u : updatesToBeSorted){    
            Boolean followsRules = true;
            for(Rule r : rules){
                if(u.checkIfRuleApplies(r)){
                    if(!(u.checkIfRuleFollowed(r))){
                        followsRules = false;
                    }
                }
            }
            if(followsRules){
                System.out.println("Adding update to correct updates: ");
                u.printSelf();
                correctUpdates.add(u);
            }
            else{
                System.out.println("Adding update to incorrect updates: ");
                u.printSelf();
                incorrectUpdates.add(u);
            }
        }
        sortedUpdates.put(true, correctUpdates);
        sortedUpdates.put(false, incorrectUpdates);
        return sortedUpdates;
    }
    public static void loadData(){
        try {
            Scanner sc = new Scanner(new File("DataFiles\\DayFiveTestData.txt"));
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
                            // System.out.println("firstInt: " + firstInt);
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
                    Update newUpadate = new Update(values);
                    System.out.println("Creating new update: ");
                    newUpadate.printSelf();
                    updates.add(newUpadate);
                    System.out.println("Current updates loaded: ");
                    for(Update u : updates){
                        u.printSelf();
                    }
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
    private ArrayList<Integer> values;
    public Update(ArrayList<Integer> values){
        this.values = new ArrayList<>(values);
    }
    public void switchValues(Integer first, Integer last){
        System.out.println("Values before switching " + first + " and " + last + ": " + values);
        Integer indexOne = values.indexOf(first);
        Integer indexTwo = values.indexOf(last);
        System.out.println("indexOne: " + indexOne);
        System.out.println("indexTwo: " + indexTwo);
        Collections.swap(values, indexOne, indexTwo);
        System.out.println("Values after switching " + first + " and " + last + ": " + values);
    }
    public void correctlyOrder(ArrayList<Rule> rules){
        Integer[][] permutations = getPermutations();
        for(Integer[] permutation : permutations){
            Boolean followsAllRules = true;
            ArrayList<Integer> permutationAsArrayList = new ArrayList<>(Arrays.asList(permutation));
            for(Rule rule : rules){
                if(checkIfRuleApplies(rule, permutationAsArrayList)){
                    if(!(checkIfRuleFollowed(rule, permutationAsArrayList))){
                        System.out.println("Failed permutation: " + permutationAsArrayList + " Breaks rule: " + rule.getFirst() + "|" + rule.getLast());
                        followsAllRules = false;
                    }

                }
            }
            if(followsAllRules){
                System.out.println("Changing " + this.values + " to " + permutationAsArrayList);
                this.values = permutationAsArrayList;
            }
        }
    }
    public Boolean checkIfRuleApplies(Rule rule){
        Boolean ruleApplies = true;
        if(!(values.contains(rule.getFirst()) && values.contains(rule.getLast()))){
            ruleApplies = false;
        }
        // System.out.println("Checking update: " + values + " Against rule: " + rule.getFirst() + "|"  + rule.getLast() + " ruleApplies: " + ruleApplies);
        return ruleApplies;
    }
    public Boolean checkIfRuleApplies(Rule rule, ArrayList<Integer> newValues){
        Boolean ruleApplies = true;
        if(!(newValues.contains(rule.getFirst()) && newValues.contains(rule.getLast()))){
            ruleApplies = false;
        }
        // System.out.println("Checking update: " + newValues + " Against rule: " + rule.getFirst() + "|"  + rule.getLast() + " ruleApplies: " + ruleApplies);
        return ruleApplies;
    }
    public Boolean checkIfRuleFollowed(Rule rule, ArrayList<Integer> newValues){
        Boolean ruleFollowed = true;
        if(!(newValues.indexOf(rule.getFirst()) < newValues.indexOf(rule.getLast()))){
            ruleFollowed = false;
        }
        // System.out.println("Checking if update: " + newValues + " follows rule: " + rule.getFirst() + "|" + rule.getLast() + " ruleFollowed: " + ruleFollowed);
        return ruleFollowed; 
    }
    public Boolean checkIfRuleFollowed(Rule rule){
        Boolean ruleFollowed = true;
        if(!(values.indexOf(rule.getFirst()) < values.indexOf(rule.getLast()))){
            ruleFollowed = false;
        }
        // System.out.println("Checking if update: " + values + " follows rule: " + rule.getFirst() + "|" + rule.getLast() + " ruleFollowed: " + ruleFollowed);
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
    public Integer[][] getPermutations() {
        ArrayList<Integer> input = values;
        if (input == null || input.isEmpty()) {
            return new Integer[0][0];
        }
        
        List<List<Integer>> permutations = new ArrayList<>();
        generatePermutations(input, new ArrayList<>(), new boolean[input.size()], permutations);
        
        // Convert List<List<Integer>> to 2D array
        Integer[][] result = new Integer[permutations.size()][input.size()];
        for (int i = 0; i < permutations.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                result[i][j] = permutations.get(i).get(j);
            }
        }
        
        return result;
    }
    private static void generatePermutations(ArrayList<Integer> input, 
                                           List<Integer> current, 
                                           boolean[] used, 
                                           List<List<Integer>> result) {
        // Base case: if current permutation is complete
        if (current.size() == input.size()) {
            result.add(new ArrayList<>(current)); // Add copy of current permutation
            return;
        }
        // Try adding each unused element to the current permutation
        for (int i = 0; i < input.size(); i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(input.get(i));
                
                // Recursively generate remaining permutations
                generatePermutations(input, current, used, result);
                
                // Backtrack
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
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