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
        Boolean firstFileBlock = true;
        int freeSpaceSize = 0;
        for(int i = 0; i < inputBlock.size(); i++){
            if(inputBlock.get(i).equals(".")){
                firstFileBlock = false;
                freeSpaceSize++;
            }
            else{
                if(!firstFileBlock){
                    System.out.println("PreSwap: " + inputBlock + "i: " + i + " freeSpaceSize: " + freeSpaceSize);
                    Boolean foundFittingFileBlock = false;
                    int lastFileBlock = inputBlock.size()-1;
                    FileBlock currentFB = null;
                    while(!foundFittingFileBlock && lastFileBlock > 0){
                        while(inputBlock.get(lastFileBlock).equals(".")){
                            System.out.println("lastFileBlock: " + lastFileBlock + " returns: " + inputBlock.get(lastFileBlock));
                            lastFileBlock--;
                        }
                        System.out.println("Found file: " + inputBlock.get(lastFileBlock));
                        currentFB = getFileBlockByID(Integer.parseInt(inputBlock.get(lastFileBlock)));
                        foundFittingFileBlock = checkIfFileBlockWillFit(currentFB, freeSpaceSize);
                        if(!foundFittingFileBlock){
                            lastFileBlock -= currentFB.getSize();
                        }
                    }
                    for(int ii = 0; ii < currentFB.getSize(); ii++) {
                        Collections.swap(inputBlock, i - (freeSpaceSize - ii), lastFileBlock - ii);
                        System.out.println("MidSwap: " + inputBlock + " i " + i + " ii: " + ii + " freeSpaceSize: " + freeSpaceSize + " lastFileBlock: " + lastFileBlock);
                    }
                    freeSpaceSize -= currentFB.getSize();
                    if(freeSpaceSize > 0){
                        i -= freeSpaceSize;
                    }
                    else{
                        i += getFileBlockByID(Integer.parseInt(inputBlock.get(i))).getSize() -1;
                    }
                    System.out.println("PostSwap: " + inputBlock + " i " + i + " freeSpaceSize: " + freeSpaceSize + " lastFileBlock: " + lastFileBlock);
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