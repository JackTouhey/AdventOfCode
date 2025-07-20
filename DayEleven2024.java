
import java.util.ArrayList;

public class DayEleven2024 {
    private static ArrayList<Long> stones = DataLoader.loadLineOfLongs("DataFiles\\DayElevenTestData.txt");
    public static void main(String[] args) {
        System.out.println(stones);
    }
    private static Boolean isRuleOneApplicable(Long i){
        return i == 0;
    }
    private static Boolean isRuleTwoApplicable(Long i){

    }
}
