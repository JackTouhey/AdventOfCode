
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DayOne2024 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(6);
        numbers.add(1);
        numbers.add(2);
        Collections.sort(numbers, new IntegerComparator());
        System.out.println(numbers);
    }
}
class IntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }

}