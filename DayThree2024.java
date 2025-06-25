import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThree2024 {
    ArrayList<Mul> validMuls = new ArrayList<>();
    public static void main(String[] args) {
        ArrayList<Mul> muls = loadData();
        Integer count = 0;
        for(Mul m : muls){
            count += m.getSum();
        }
        System.out.println("Total count: " + count);
    }
    public static ArrayList<Mul> loadData(){
        ArrayList<Mul> muls = new ArrayList<>();
        try {
            File dataFile = new File("DataFiles\\DayThreeData.txt");
            Scanner sc = new Scanner(dataFile);
            sc.useDelimiter("");
            muls = processData(sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return muls;
    }
    public static ArrayList<Mul> processData(Scanner sc){
        ArrayList<Mul> muls = new ArrayList<>();
        while(sc.hasNext()){
            String numOne = "";
            String numTwo = "";
            Boolean validMul = false;
            if(checkMul(sc)){
                if(!checkMul(sc)){
                    if("(".equals(sc.next())){
                        if(sc.hasNextInt()){
                            numOne = getNumbers(sc);
                            if(sc.hasNext(",")){
                                sc.next();
                                if(sc.hasNextInt()){
                                    numTwo = getNumbers(sc);
                                    if(!checkMul(sc)){
                                        if(")".equals(sc.next())){
                                            validMul = true;
                                        }
                                    }
                                    else{
                                        System.out.println("Moving past: " + sc.next());
                                    }
                                }
                                else{
                                    System.out.println("Moving past: " + sc.next());
                                }
                            }
                            else{
                                System.out.println("Moving past: " + sc.next());
                            }
                        }
                        else{
                            System.out.println("Moving past: " + sc.next());
                        }
                    }
                }
                else{
                    System.out.println("Moving past: " + sc.next());
                }
            }
            else{
                System.out.println("Moving past: " + sc.next());
            }
            if(validMul){
                System.out.println("Creating Mul " + numOne + " * " + numTwo);
                muls.add(new Mul(Integer.valueOf(numOne), Integer.valueOf(numTwo)));
            }
        }
        return muls;
    }
    public static Boolean checkMul(Scanner sc){
        if(sc.hasNext("m")){
            System.out.println("Check Mul Moving past: " + sc.next());
            if(sc.hasNext("u")){
                System.out.println("Check Mul Moving past: " + sc.next());
                if(sc.hasNext("l")){
                    sc.next();
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    public static String getNumbers(Scanner sc){
        String num = "";
        for(int i = 0; i < 3; i++){
            if(sc.hasNextInt()){
                num += sc.nextInt();
            }
        }
        // System.out.println("Returning number: " + num);
        return num;
    }
}
class Mul{
    Integer one;
    Integer two;
    public Mul(Integer i1, Integer i2){
        this.one = i1;
        this.two = i2;
    }
    public Integer getSum(){
        Integer mul = one * two;
        // System.out.println("Mul of " + one + " * " + two + " = " + mul);
        return mul;
    }
}
