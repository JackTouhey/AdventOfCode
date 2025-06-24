import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThree2024 {
    ArrayList<Mul> validMuls = new ArrayList<>();
    public static void main(String[] args) {
        loadData();
    }
    public static void loadData(){
        try {
            File dataFile = new File("DataFiles\\DayThreeData.txt");
            Scanner sc = new Scanner(dataFile);
            sc.useDelimiter("");
            processData(sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void processData(Scanner sc){
        while(sc.hasNext()){
            String numOne = "";
            String numTwo = "";
            Boolean validMul = false;
            if(sc.hasNext("m")){
                System.out.println("In tree: " +  sc.next());
                if(sc.hasNext("u")){
                    System.out.println("In tree: " +  sc.next());
                    if(sc.hasNext("l")){
                        System.out.println("In tree: " +  sc.next());
                        if("(".equals(sc.next())){
                            if(sc.hasNextInt()){
                                numOne += sc.nextInt();
                                if(sc.hasNextInt()){
                                    numOne += sc.nextInt();
                                    if(sc.hasNextInt()){
                                        numOne += sc.nextInt();
                                        if(sc.hasNext(",")){
                                            System.out.println("Moving past: " +  sc.next());
                                            if(sc.hasNextInt()){
                                                numTwo += sc.nextInt();
                                                if(sc.hasNextInt()){
                                                    numTwo += sc.nextInt();
                                                    if(sc.hasNextInt()){
                                                        numTwo += sc.nextInt();
                                                        if(")".equals(sc.next())){
                                                            validMul = true;
                                                        }
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Moving past: " +  sc.next());
                                            }
                                        }
                                    }
                                    else if(sc.hasNext(",")){

                                    }
                                    else{
                                        System.out.println("Moving past: " +  sc.next());
                                    }
                                }
                                else if(sc.hasNext(",")){

                                }
                                else{
                                    System.out.println("Moving past: " +  sc.next());
                                }                            
                            }
                        }
                        else{
                            System.out.println("Moving past: " +  sc.next());
                        }
                    }
                    else{
                        System.out.println("Moving past: " +  sc.next());
                    }
                }
                else{
                    System.out.println("Moving past: " +  sc.next());
                }
            }
            else{
                System.out.println("Moving past: " +  sc.next());
            }
            if(validMul){
                System.out.println("NumOne: " + numOne + " numTwo: " + numTwo);
            }
            
        }
    }
}
class Mul{
    Integer one;
    Integer two;
    public Mul(Integer i1, Integer i2){
        this.one = i1;
        this.two = i2;
    }
}
