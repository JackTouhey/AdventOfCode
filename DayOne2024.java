
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class DayOne2024 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = getInput();
        System.out.println("Sorting numbers");
        ArrayList<Integer> listOne = new ArrayList<>();
        ArrayList<Integer> listTwo = new ArrayList<>();
        Boolean odd = true;
        for (Integer i : numbers){
            if(odd){
                listOne.add(i);
                odd = false;
            }
            else{
                listTwo.add(i);
                odd = true;
            }
        }
        System.out.println("Printing list one");
        System.out.println(listOne);
    }
    private static ArrayList<Integer> getInput(){
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            Integer nextNumber = sc.nextInt();
            System.out.println("Adding " + nextNumber);
            numbers.add(nextNumber);
        }
        sc.close();
        return numbers;
    }
}
class IntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }

}