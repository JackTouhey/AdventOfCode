import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayNine2024 {
    private static final ArrayList<FileBlock> files = loadData();
    public static void main(String[] args) {
        ArrayList<String> generatedBlocks = generateIndividualBlocks();
        System.out.println("Generated Blocks: " + generatedBlocks); 
    }
    private static ArrayList<FileBlock> loadData(){
        ArrayList<FileBlock> returnFiles = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("DataFiles\\DayNineTestData.txt"))) {
            sc.useDelimiter("");
            int ID = 0;
            while(sc.hasNextInt()){
                int fileSize = sc.nextInt();
                int freeSpace;
                if(sc.hasNextInt()){
                    freeSpace = sc.nextInt();
                }
                else{
                    freeSpace = 0;
                }
                returnFiles.add(new FileBlock(ID, fileSize, freeSpace));
                ID++;
            }
        }
        catch(FileNotFoundException e){}
        return returnFiles;
    }
    private static ArrayList<String> generateIndividualBlocks(){
        ArrayList<String> returnList = new ArrayList<>();
        for(FileBlock fb : files){
            for(int i = 0; i < fb.getSize(); i++){
                returnList.add(String.valueOf(fb.getID()));
            }
            for(int i = 0; i < fb.getFollowingFreeSpace(); i++){
                returnList.add(".");
            }
        }
        return returnList;
    }
}
class FileBlock{
    private final int ID;
    private final int size;
    private final int followingFreeSpace;
    public FileBlock(int ID, int size, int followingFreeSpace){
        this.ID = ID;
        this.size = size;
        this.followingFreeSpace = followingFreeSpace;
    }
    public int getID(){return this.ID;}
    public int getSize(){return this.size;}
    public int getFollowingFreeSpace(){return this.followingFreeSpace;}
    public void printSelf(){
        System.out.println("ID: " + ID + " size: " + size + " following free space: " + followingFreeSpace);
    }
}