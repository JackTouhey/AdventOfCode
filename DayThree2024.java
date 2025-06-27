import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
        Boolean currentDo = true;
        while(sc.hasNext()){
            String numOne = "";
            String numTwo = "";
            Boolean validMul = false;
            HashMap<Boolean, Boolean> doPresent = checkDo(sc);
            if(doPresent.containsKey(true)){
                if(doPresent.get(true)){
                    currentDo = true;
                }
                else{
                    currentDo = false;
                }
            }
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
            if(validMul && currentDo){
                System.out.println("Creating Mul " + numOne + " * " + numTwo);
                muls.add(new Mul(Integer.valueOf(numOne), Integer.valueOf(numTwo)));
            }
        }
        return muls;
    }
    public static HashMap<Boolean, Boolean> checkDo(Scanner sc){
        HashMap<Boolean, Boolean> doPresent = new HashMap<>();
        if(sc.hasNext("d")){
            System.out.println("In checkDo moving past: " + sc.next());
            if(sc.hasNext("o")){
                System.out.println("In checkDo moving past: " + sc.next());
                if(!checkMul(sc)){
                    String next = sc.next();
                    System.out.println("Checking do. next: " + next);
                    if(next.equals("(")){
                        if(!checkMul(sc)){
                            if(")".equals(sc.next())){
                                System.out.println("do() detected");
                                doPresent.put(true, true);
                            }
                            else{
                                doPresent.put(false, false);
                            }
                        }
                        else{
                            doPresent.put(false, false);
                        }
                    }
                    else if(next.equals("n")){
                        System.out.println("In checkDo moving past: " + sc.next());
                        if(sc.hasNext("t")){
                            System.out.println("In checkDo moving past: " + sc.next());
                            if(!checkMul(sc)){
                                if("(".equals(sc.next())){
                                    if(!checkMul(sc)){
                                        if(")".equals(sc.next())){
                                            System.out.println("dont() detected");
                                            doPresent.put(true, false);
                                        }
                                        else{
                                            doPresent.put(false, false);
                                        }
                                    }
                                    else{
                                        doPresent.put(false, false);
                                    }
                                }
                                else{
                                    doPresent.put(false, false);
                                }
                            }
                            else{
                                doPresent.put(false, false);
                            }
                        }
                        else{
                            doPresent.put(false, false);
                        }
                    }
                    else{
                        doPresent.put(false, false);
                    }
                }
                else{
                    doPresent.put(false, false);
                }
            }
            else{
                doPresent.put(false, false);
            }
        }
        else{
            doPresent.put(false, false);
        }
        return doPresent;
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