import java.util.ArrayList;

public class DayFive2024 {
    public static ArrayList<Update> updates = new ArrayList<>();
    public static ArrayList<Rule> rules = new ArrayList<>();
    public static void main(String[] args) {
        loadData();
    }
    public static void loadData(){

    }
}
class Update{
    ArrayList<Integer> values = new ArrayList<>();
    public Update(ArrayList<Integer> values){
        this.values = values;
    }
    public Boolean checkIfRuleApplies(Rule rule){
        return values.contains(rule.getFirst()) && values.contains(rule.getLast());
    }
    public Boolean checkIfRuleFollowed(Rule rule){
        return values.indexOf(rule.getFirst()) < values.indexOf(rule.getLast());
    }
    public Integer getMedian(){
        Integer median;
        if(!(values.size() % 2 == 0)){
            Double middle = (double) (values.size()/2) + 1;
            median = middle.intValue();
        }
        else{
            median = values.size() / 2;
        }
        return values.get(median);
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
