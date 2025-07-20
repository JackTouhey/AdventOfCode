
import java.util.ArrayList;

public class DayEleven2024 {
    private static ArrayList<Long> stones = DataLoader.loadLineOfLongs("DataFiles\\DayElevenTestData.txt");
    public static void main(String[] args) {
        for(Long i : stones){
            System.out.println(i + " rule 1: " + isRuleOneApplicable(i) + " rule 2: " + isRuleTwoApplicable(i));
        }
    }
    private static Boolean isRuleOneApplicable(Long i){
        return i == 0;
    }
    private static Boolean isRuleTwoApplicable(Long i){
        return String.valueOf(i).length() % 2 == 0;
    }
}
