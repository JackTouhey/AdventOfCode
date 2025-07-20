
import java.util.ArrayList;
import java.util.Scanner;

public class DayEleven2024 {
    private static ArrayList<Long> stones = DataLoader.loadLineOfLongs("DataFiles\\DayElevenData.txt");
    public static void main(String[] args) {
        for(Long i : stones){
            System.out.println(i + " rule 1: " + isRuleOneApplicable(i) + " rule 2: " + isRuleTwoApplicable(i));
        }
        for(int count = 0; count < 25; count++){
            int startingSize = stones.size();
            for(int i = 0; i < startingSize; i++){
                if(isRuleOneApplicable(stones.get(i))){
                    applyRuleOne(i);
                }
                else if(isRuleTwoApplicable(stones.get(i))){
                    applyRuleTwo(i, stones.get(i));
                }
                else{
                    applyRuleThree(i, stones.get(i));
                }
            }
        }
        System.out.println(stones.size());
    }
    private static Boolean isRuleOneApplicable(Long i){
        return i == 0;
    }
    private static Boolean isRuleTwoApplicable(Long i){
        return String.valueOf(i).length() % 2 == 0;
    }
    private static void applyRuleOne(int index){
        stones.set(index, (long) 1);
    }
    private static void applyRuleTwo(int index, Long input){
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
            firstHalf = firstHalf.replaceFirst("^0+(?!$)", "");
            secondHalf = secondHalf.replaceFirst("^0+(?!$)", "");
            stones.set(index, Long.valueOf(firstHalf));
            stones.add(index + 1, Long.valueOf(secondHalf));
        }
    }
    private static void applyRuleThree(int index, Long input){
        System.out.println();
        stones.set(index, input * 2024);
    }
}
