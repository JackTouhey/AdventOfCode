import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DayNine2024 {
    private static final ArrayList<FileBlock> files = loadData();
    public static void main(String[] args) {
        ArrayList<String> generatedBlocks = generateIndividualBlocks();
        sortWholeBlocks(generatedBlocks);
        long checkSum = 0;
        for(int i = 0; i < generatedBlocks.size(); i++){
            if(!generatedBlocks.get(i).equals(".")){
                checkSum += i * Integer.parseInt(generatedBlocks.get(i));
            }
        }
        System.out.println("checkSum: " + checkSum);
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
    private static void sortBlocks(ArrayList<String> inputBlock){
        for(int i = 0; i < inputBlock.size(); i++){
            if(inputBlock.get(i).equals(".")){
                int lastFileBlock = inputBlock.size()-1;
                while(inputBlock.get(lastFileBlock).equals(".")){
                    lastFileBlock--;
                }
                if(lastFileBlock > i){
                    Collections.swap(inputBlock, i, lastFileBlock);
                }
                // System.out.println(inputBlock);
            }
        }
    }
    private static void sortWholeBlocks(ArrayList<String> inputBlock){
        for(int i = inputBlock.size()-1; i >= 0; i--){
            if(!inputBlock.get(i).equals(".")){
                FileBlock currentBlock = getFileBlockByID(Integer.parseInt(inputBlock.get(i)));
                int blockSize = currentBlock.getSize();
                int startOfFreeSpace = 0;
                int freeSpaceIndex = 0;
                int freeSpaceSize = 0;
                while(freeSpaceSize < blockSize && freeSpaceIndex < inputBlock.size()){
                    freeSpaceSize = 0;
                    if(inputBlock.get(freeSpaceIndex).equals(".")){
                        startOfFreeSpace = freeSpaceIndex;
                        while(inputBlock.get(freeSpaceIndex).equals(".")){
                            freeSpaceIndex++;
                            freeSpaceSize++;
                        }
                    }
                }
                if(freeSpaceSize >= blockSize){
                     for(int ii = 0; ii < currentBlock.getSize(); ii++) {
                        Collections.swap(inputBlock, startOfFreeSpace + ii, i - ii);
                    }
                }
            }
        }
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
    private static Boolean checkIfFileBlockWillFit(FileBlock fb, int freeSpaceSize){
        System.out.println("Checking if " + fb.getSize() + " fits in free space: " + freeSpaceSize);
        return fb.getSize() <= freeSpaceSize;
    }
    private static FileBlock getFileBlockByID(int ID){
        for(FileBlock fb : files){
            if(fb.getID() == ID){
                return fb;
            }
        }
        return null;
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