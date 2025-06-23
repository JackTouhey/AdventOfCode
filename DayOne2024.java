
import java.util.ArrayList;
import java.util.Collections;
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
        // System.out.println(listOne);
        // System.out.println(listTwo);
        Collections.sort(listOne, new IntegerComparator());
        Collections.sort(listTwo, new IntegerComparator());
        // System.out.println("List One:");
        // System.out.println(listOne);
        Integer distance = getDistance(listOne, listTwo);
        System.out.println("Distance: " + distance);
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
    private static Integer getDistance(ArrayList<Integer> listOne, ArrayList<Integer> listTwo){
        Integer distance = 0;
        for(int i =0; i < listOne.size(); i++){
            Integer difference = listTwo.get(i) - listOne.get(i);
            difference = Math.abs(difference);
            distance += difference;
            System.out.println("Distance between " + listOne.get(i) + " and " + listTwo.get(i) + " : " + difference);
        }
        return distance;
    }
}
class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }

}