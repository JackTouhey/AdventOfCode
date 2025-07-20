
import java.util.ArrayList;
import java.util.Scanner;

public class DayEleven2024 {
    private static ArrayList<Long> stones = DataLoader.loadLineOfLongs("DataFiles\\DayElevenData.txt");
    public static void main(String[] args) {
        for(Long i : stones){
            System.out.println(i + " rule 1: " + isRuleOneApplicable(i) + " rule 2: " + isRuleTwoApplicable(i));
        }
        for(int count = 1; count <= 25; count++){
            // System.out.println();
            // System.out.println("Stones before run " + count + " : " + stones);
            int startingSize = stones.size();
            int ruleTwoCount = 0;
            for(int i = 0; i < startingSize; i++){
                if(isRuleOneApplicable(stones.get(i+ruleTwoCount))){
                    applyRuleOne(i + ruleTwoCount);
                }
                else if(isRuleTwoApplicable(stones.get(i+ruleTwoCount))){
                    applyRuleTwo(i + ruleTwoCount, stones.get(i+ruleTwoCount));
                    ruleTwoCount++;
                }
                else{
                    applyRuleThree(i + ruleTwoCount, stones.get(i+ruleTwoCount));
                }
            }
        }
        // System.out.println(stones);
        System.out.println(stones.size());
    }
    private static Boolean isRuleOneApplicable(Long i){
        return i == 0;
    }
    private static Boolean isRuleTwoApplicable(Long i){
        return String.valueOf(i).length() % 2 == 0;
    }
    private static void applyRuleOne(int index){
        // System.out.println("Applying rule one on index: " + index);
        stones.set(index, (long) 1);
        // System.out.println("Stones now: " + stones);
    }
    private static void applyRuleTwo(int index, Long input){
        // System.out.println("Applying rule two on index: " + index + " with value: " + stones.get(index));
        String value = String.valueOf(input);
        try (Scanner sc = new Scanner(value)) {
            sc.useDelimiter("");
            String firstHalf = "";
            String secondHalf = "";
            for(int i = 0; i < value.length(); i++){
                if(i < value.length()/2){
                    firstHalf += sc.next();
                }
                else{
                    secondHalf += sc.next();
                }
            }
            // System.out.println("First half: " + firstHalf + " secondHalf: " + secondHalf);
            firstHalf = firstHalf.replaceFirst("^0+(?!$)", "");
            secondHalf = secondHalf.replaceFirst("^0+(?!$)", "");
            // System.out.println("First half: " + firstHalf + " secondHalf: " + secondHalf);
            stones.set(index, Long.valueOf(firstHalf));
            stones.add(index + 1, Long.valueOf(secondHalf));
        }
        // System.out.println("Stones now: " + stones);
    }
    private static void applyRuleThree(int index, Long input){
        // System.out.println("Applying rule 3 on index: " + index + " with value: " + input);
        stones.set(index, input * 2024);
        // System.out.println("Stones now: " + stones);
    }
}
