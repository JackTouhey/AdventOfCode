import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class DayFive2024 {
    public static ArrayList<Rule> rules = new ArrayList<>();
    public static ArrayList<Update> updates = new ArrayList<>();
    public static void main(String[] args) {
        loadData();
        for(Update u : updates){
            for(Rule r : rules){
                if(u.checkIfRuleApplies(r)){
                    u.addApplicableRule(r);
                }
            }
        }
        HashMap<Boolean, ArrayList<Update>> sortedUpdates = sortUpdates(updates);
        ArrayList<Update> correctUpdates = sortedUpdates.get(true);
        ArrayList<Update> incorrectUpdates = sortedUpdates.get(false);
        Integer correctCount = 0;
        for(Update u : correctUpdates){
            Integer median = u.getMedian();
            System.out.println("Adding correct median: " + median);
            correctCount += median;
        }
        System.out.println("About to order updates: ");
        ArrayList<Update> newlyOrderedUpdates = orderIncorrectUpdates(incorrectUpdates);
        System.out.println("Ordered updates: ");
        Integer reorderedCount = 0;
        for(Update u : newlyOrderedUpdates){
            u.printSelf();
            reorderedCount += u.getMedian();
        }
        System.out.println("Correct count: " + correctCount);
        System.out.println("Reordered count: " + reorderedCount); 
    }
    private static ArrayList<Update> orderIncorrectUpdates(ArrayList<Update> incorrectUpdates){
        ArrayList<Update> orderedUpdates = new ArrayList<>();
        for(Update u : incorrectUpdates){
            System.out.println("Attempting to order:");
            u.printSelf();
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
    private ArrayList<Rule> applicableRules;
    public Update(ArrayList<Integer> values){
        this.values = new ArrayList<>(values);
    }
    public void addApplicableRule(Rule r){
        applicableRules.add(r);
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
    public void correctlyOrder(ArrayList<Rule> rules) {
        ArrayList<Integer> validPermutation = getValidPermutation(rules);
        if (validPermutation != null) {
            System.out.println("Changing " + this.values + " to " + validPermutation);
            this.values = validPermutation;
        } else {
            System.out.println("No valid permutation found for " + this.values);
        }
    }
    public ArrayList<Integer> getValidPermutation(ArrayList<Rule> rules) {
        ArrayList<Integer> input = values;
        if (input == null || input.isEmpty()) {
            return null;
        }
        
        return generatePermutations(input, new ArrayList<>(), new boolean[input.size()], rules);
    }
    private ArrayList<Integer> generatePermutations(ArrayList<Integer> input, 
                                              ArrayList<Integer> current, 
                                              boolean[] used, 
                                              ArrayList<Rule> rules) {
        // Base case: if current permutation is complete
        if (current.size() == input.size()) {
            if (isValidPermutation(current, rules)) {
                return new ArrayList<>(current); // Return copy of valid permutation
            }
            return null; // This permutation didn't work
        }
        
        // Try adding each unused element to the current permutation
        for (int i = 0; i < input.size(); i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(input.get(i));
                
                // Recursively generate remaining permutations
                ArrayList<Integer> validPermutation = generatePermutations(input, current, used, rules);
                if (validPermutation != null) {
                    return validPermutation; // Found valid permutation, return it
                }
                
                // Backtrack
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
        
        return null; // No valid permutation found in this branch
    }
    private boolean isValidPermutation(ArrayList<Integer> permutation, ArrayList<Rule> rules) {
        for (Rule rule : rules) {
            System.out.println("Checking permutation: " + permutation + " against rule " + rule.getFirst() + "|" + rule.getLast());
            if (checkIfRuleApplies(rule, permutation)) {
                if (!(checkIfRuleFollowed(rule, permutation))) {
                    return false;
                }
            }
        }
        return true;
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