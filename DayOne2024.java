import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

        Collections.sort(listOne, new IntegerComparator());
        Collections.sort(listTwo, new IntegerComparator());
        // Integer Distance = getDistance(listOne, listTwo);
        // System.out.println("Distance: " + Distance);
        getSimilarity(listOne, listTwo);
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
    private static void getSimilarity(ArrayList<Integer> listOne, ArrayList<Integer> listTwo){
        HashMap<Integer, Integer> mapOfListTwo = new HashMap<>();
        for(Integer i : listTwo){
            if(!mapOfListTwo.containsKey(i)){
                mapOfListTwo.put(i, 1);
            }
            else{
                mapOfListTwo.put(i, mapOfListTwo.get(i) + 1);
            }
        }
        Integer similarity = 0;
        for(Integer i : listOne){
            if(mapOfListTwo.containsKey(i)){
                similarity += mapOfListTwo.get(i) * i;
            }
        }
        System.out.println("Similarity: " + similarity);
    }
}
class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }

}