import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
            u.correctlyOrder();
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
    private ArrayList<Rule> applicableRules = new ArrayList<>();
    
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
    
    public void correctlyOrder() {
        ArrayList<Integer> validPermutation = getValidPermutationUsingTopologicalSort();
        if (validPermutation != null) {
            System.out.println("Changing " + this.values + " to " + validPermutation);
            this.values = validPermutation;
        } else {
            System.out.println("No valid permutation found for " + this.values);
        }
    }
    
    public ArrayList<Integer> getValidPermutationUsingTopologicalSort() {
        if (values == null || values.isEmpty()) {
            return null;
        }
        
        // Build graph from applicable rules
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        
        // Initialize all nodes
        for (Integer value : values) {
            graph.put(value, new ArrayList<>());
            inDegree.put(value, 0);
        }
        
        // Add edges based on applicable rules
        for (Rule rule : applicableRules) {
            Integer first = rule.getFirst();
            Integer last = rule.getLast();
            
            // Only process rules that apply to this update
            if (values.contains(first) && values.contains(last)) {
                graph.get(first).add(last);
                inDegree.put(last, inDegree.get(last) + 1);
            }
        }
        
        // Kahn's algorithm for topological sorting
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        
        // Add all nodes with in-degree 0 to queue
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        
        // Process nodes
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            result.add(current);
            
            // Reduce in-degree of neighbors
            for (Integer neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // Check if all nodes were processed (no cycle)
        if (result.size() != values.size()) {
            System.out.println("Cycle detected in rules - no valid ordering exists");
            return null;
        }
        
        return result;
    }

    public Boolean checkIfRuleApplies(Rule rule){
        Boolean ruleApplies = true;
        if(!(values.contains(rule.getFirst()) && values.contains(rule.getLast()))){
            ruleApplies = false;
        }
        return ruleApplies;
    }
    
    public Boolean checkIfRuleApplies(Rule rule, ArrayList<Integer> newValues){
        Boolean ruleApplies = true;
        if(!(newValues.contains(rule.getFirst()) && newValues.contains(rule.getLast()))){
            ruleApplies = false;
        }
        return ruleApplies;
    }
    
    public Boolean checkIfRuleFollowed(Rule rule, ArrayList<Integer> newValues){
        Boolean ruleFollowed = true;
        if(!(newValues.indexOf(rule.getFirst()) < newValues.indexOf(rule.getLast()))){
            ruleFollowed = false;
        }
        return ruleFollowed; 
    }
    
    public Boolean checkIfRuleFollowed(Rule rule){
        Boolean ruleFollowed = true;
        if(!(values.indexOf(rule.getFirst()) < values.indexOf(rule.getLast()))){
            ruleFollowed = false;
        }
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